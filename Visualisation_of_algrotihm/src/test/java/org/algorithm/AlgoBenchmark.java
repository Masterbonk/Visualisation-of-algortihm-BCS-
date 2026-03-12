package org.algorithm;

import org.algorithm.algo.Algorithm;
import org.algorithm.algo.DStarLite;
import org.algorithm.algo.LPA_Star;
import org.algorithm.graph.Node;
import org.openjdk.jmh.annotations.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class AlgoBenchmark {


    @State(Scope.Thread)
    public static class BenchmarkState {

        PApplet sketch = new PApplet();

        @Setup
        public void prepare() {
            Util.Make_Graph(sketch, 100, 100);

        }
    }

    @Benchmark
    public void test_D_Star(BenchmarkState state) {
        Algorithm d_star = new DStarLite();
        d_star.set_Start(Main.node_array.getFirst());
        d_star.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (d_star.get_Goal() != d_star.get_Start()){
            d_star.Main();
        }
    }

    @Benchmark
    public void test_LPA_Star(BenchmarkState state) {
        Algorithm lpa_star = new LPA_Star();
        lpa_star.set_Start(Main.node_array.getFirst());
        lpa_star.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (lpa_star.get_Goal() != lpa_star.get_Start()){
            lpa_star.Main();
        }
    }


}
