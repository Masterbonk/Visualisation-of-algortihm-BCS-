package org.algorithm;

import org.algorithm.algo.DStarLite;
import org.algorithm.algo.LPA_Star;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import static org.algorithm.Main.algorithm;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

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
    void test_LPA_Star_Change_Time(){
        algorithm = new LPA_Star();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));



        algorithm.Main();
    }

    @Test
    void test_D_Star_Update_Time(){

        algorithm = new DStarLite();

        Util.Make_Graph(sketch, 100, 100);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));

        algorithm.initialize();
        algorithm.compute_Shortest_Path();
        algorithm.first_run = false;

        ArrayList<Node> path = algorithm.get_Shortest_Path(algorithm.get_Start());


        int n = path.size();
        int removeCount = n / 100;

        int middle = n / 2;

        int half = removeCount / 2;

        HashSet<Node> removables = new HashSet<>();


        if (removeCount % 2 == 1) {
            removables.add(path.get(middle));
        }

        for (int i = 1; i <= half; i++) {
            int idx = middle - i;
            if (idx >= 0) {
                removables.add(path.get(idx));
            }
        }

        for (int i = 1; i <= half; i++) {
            int idx = middle + i;
            if (idx < n) {
                removables.add(path.get(idx));
            }
        }


        for(Node node : removables){
            for (Edge e : node.get_Connected()){
                algorithm.edge_update_map.put(e,MAX_INT);
            }

        }

        //algorithm.set_of_nodes.removeAll(removables);


        while (algorithm.get_Goal() != algorithm.get_Start()){
            algorithm.Main();
        }


    }
}

