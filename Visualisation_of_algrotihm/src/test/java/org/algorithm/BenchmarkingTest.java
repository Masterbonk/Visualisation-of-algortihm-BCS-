package org.algorithm;

import org.algorithm.algo.DStarLite;
import org.algorithm.algo.LPA_Star;
import org.algorithm.graph.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.algorithm;

class BenchmarkingTest {


    PApplet sketch = new PApplet();

    int edge_size = 1000;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        sketch = new PApplet();
    }

    @Test
    void test_D_Star(){
        algorithm = new DStarLite();
        Util.Make_Graph(sketch, edge_size, edge_size);
        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (algorithm.get_Goal() != algorithm.get_Start()){
            algorithm.Main();
        }
    }

    @Test
    void test_LPA_Star(){
        algorithm = new LPA_Star();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        algorithm.Main();
    }


    @Test
    void test_LPA_Star_as_D_Star_Lite_Problem(){
        algorithm = new LPA_Star();

        edge_size = 10;
        Util.Make_Graph(sketch, edge_size, edge_size);

        Node start = Main.node_array.getFirst();
        Node goal = Main.node_array.get((Main.node_array.size()-1)/2);

        algorithm.set_Start(start);
        algorithm.set_Goal(goal);
        algorithm.Main();

        ArrayList<Node> path = algorithm.get_Shortest_Path(goal);
        start = path.get(path.size()-2);
        algorithm.set_Start(start);

        while (goal != start && !path.isEmpty()){
            algorithm.Main();
            path = algorithm.get_Shortest_Path(goal);
            start = path.get(path.size()-2);
            algorithm.set_Start(start);
            if (path.get(0) == goal){
                System.out.println("break");
                break;

            }
        }

    }

    @Test
    void test_D_Star_Lite_Superior(){
        edge_size = 10;
        algorithm = new DStarLite();
        Util.Make_Graph(sketch, edge_size, edge_size);
        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (algorithm.get_Goal() != algorithm.get_Start()){
            algorithm.Main();
        }
    }
}

