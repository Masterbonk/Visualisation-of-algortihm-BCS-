package org.algorithm;

import org.algorithm.algo.DStarLite;
import org.algorithm.algo.D_Star_Lite_benchmarking_testing;
import org.algorithm.algo.LPA_Star;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.ArrayList;

import static org.algorithm.Main.*;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

import static org.algorithm.Main.algorithm;
import static org.algorithm.Main.node_array;

class BenchmarkingTest {


    PApplet sketch = new PApplet();

    //int edge_size = 1000;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

        sketch = new PApplet();

        if (algorithm != null && algorithm.get_U() != null) {
            algorithm.get_U().clear_Heap();
            algorithm.get_U().clear_Keys();
        }

        algorithm = null;
        node_array = new ArrayList<>();
        edge_array = new ArrayList<>();

    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_D_Star(int edge_size){

        algorithm = new DStarLite();

        Util.Make_Graph(sketch, edge_size, edge_size);
        algorithm.set_Start(Main.node_array.getFirst());

        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (algorithm.get_Goal() != algorithm.get_Start()){
            algorithm.Main();
        }

    }



    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_D_Star_no_step_through(int edge_size){

        algorithm = new D_Star_Lite_benchmarking_testing();
        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));

        D_Star_Lite_benchmarking_testing tmp = (D_Star_Lite_benchmarking_testing)algorithm;
        tmp.Main(false);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_D_Star_Update_Time(int edge_size){

        algorithm = new DStarLite();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));

        algorithm.initialize();
        algorithm.compute_Shortest_Path();
        algorithm.first_run = false;

        ArrayList<Node> path = algorithm.get_Shortest_Path(algorithm.get_Start());

        int n = path.size();
        int removeCount = n / 10;

        int middle = n / 2;

        int half = removeCount / 2;

        ArrayList<Node> removables = new ArrayList<>();

        for (int i = middle-half; i <= middle+half; i++) {
            removables.add(path.get(i));
        }


        for (int i = 0; i < removables.size()-2; i++){

            algorithm.edge_update_map.put(Util.find_Shared_Edge(removables.get(i),removables.get(i+1)),MAX_INT);

        }

        while (algorithm.get_Goal() != algorithm.get_Start()){
            algorithm.Main();
        }

    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_D_Star_Update_Time_As_LPA_Star_Problem(int edge_size){

        algorithm = new D_Star_Lite_benchmarking_testing();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));

        algorithm.initialize();
        algorithm.compute_Shortest_Path();
        algorithm.first_run = false;

        ArrayList<Node> path = algorithm.get_Shortest_Path(algorithm.get_Start());

        int n = path.size();
        int removeCount = n / 10;

        int middle = n / 2;

        int half = removeCount / 2;

        ArrayList<Node> removables = new ArrayList<>();


        for (int i = middle-half; i <= middle+half; i++) {
            removables.add(path.get(i));
        }

        for (int i = 0; i < removables.size()-2; i++){

            algorithm.edge_update_map.put(Util.find_Shared_Edge(removables.get(i),removables.get(i+1)),MAX_INT);

        }

        D_Star_Lite_benchmarking_testing tmp = (D_Star_Lite_benchmarking_testing)algorithm;
        tmp.Main(false);

    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_LPA_Star(int edge_size){
        algorithm = new LPA_Star();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        algorithm.Main();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_LPA_Star_as_D_Star_Lite_Problem(int edge_size){
        algorithm = new LPA_Star();
        Util.Make_Graph(sketch, edge_size, edge_size);

        Node start = Main.node_array.getFirst();
        Node goal = Main.node_array.get((Main.node_array.size()-1)/2);

        algorithm.set_Start(start);
        algorithm.set_Goal(goal);

        //last element in path will be goal
        while (algorithm.get_Start() != algorithm.get_Goal()){
            algorithm.Main();

            Node next = Util.find_Min_G_Node(algorithm.get_Goal());
            algorithm.set_Goal(next);

        }

    }


    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_LPA_Star_Update_Time(int edge_size){

        algorithm = new LPA_Star();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));

        algorithm.initialize();
        algorithm.compute_Shortest_Path();
        algorithm.first_run = false;

        ArrayList<Node> path = algorithm.get_Shortest_Path(algorithm.get_Goal());

        int n = path.size();
        int removeCount = n / 10;

        int middle = n / 2;

        int half = removeCount / 2;

        ArrayList<Node> removables = new ArrayList<>();

        for (int i = middle-half; i <= middle+half; i++) {
            removables.add(path.get(i));
        }

        for (int i = 0; i < removables.size()-2; i++){

            algorithm.edge_update_map.put(Util.find_Shared_Edge(removables.get(i),removables.get(i+1)),MAX_INT);

        }

        algorithm.Main();

    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000})
    void test_LPA_Star_Update_Time_As_D_Star_Problem(int edge_size){

        algorithm = new LPA_Star();

        Util.Make_Graph(sketch, edge_size, edge_size);

        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));

        algorithm.initialize();
        algorithm.compute_Shortest_Path();

        ArrayList<Node> path = algorithm.get_Shortest_Path(algorithm.get_Goal());

        int n = path.size();
        int removeCount = n / 10;

        int middle = n / 2;

        int half = removeCount / 2;

        ArrayList<Node> removables = new ArrayList<>();


        for (int i = middle-half; i <= middle+half; i++) {
            removables.add(path.get(i));
        }


        for (int i = 0; i < removables.size()-2; i++){

            algorithm.edge_update_map.put(Util.find_Shared_Edge(removables.get(i),removables.get(i+1)),MAX_INT);

        }

        //last element in path will be goal
        while (algorithm.get_Start() != algorithm.get_Goal()){

            algorithm.Main();

            Node next = Util.find_Min_G_Node(algorithm.get_Goal());
            algorithm.set_Goal(next);
        }

    }

}

