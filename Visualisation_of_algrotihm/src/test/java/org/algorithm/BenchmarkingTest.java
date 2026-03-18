package org.algorithm;

import org.algorithm.algo.DStarLite;
import org.algorithm.algo.D_Star_Lite_benchmarking_testing;
import org.algorithm.algo.LPA_Star;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.algorithm;
import static org.algorithm.Main.node_array;

class BenchmarkingTest {


    PApplet sketch = new PApplet();

    int edge_size = 1000;

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
    void test_D_Star_no_step_through(){
        algorithm = new D_Star_Lite_benchmarking_testing();
        Util.Make_Graph(sketch, edge_size, edge_size);
        algorithm.set_Start(Main.node_array.getFirst());
        algorithm.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        D_Star_Lite_benchmarking_testing tmp = (D_Star_Lite_benchmarking_testing)algorithm;
        tmp.Main(false);
    }
}

