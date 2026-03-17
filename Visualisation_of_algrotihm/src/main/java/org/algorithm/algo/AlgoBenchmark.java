package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.ui.UI;
import org.algorithm.ui.buttons.Forward_Button;
import org.algorithm.ui.buttons.Pause_Button;
import org.openjdk.jmh.annotations.*;
import processing.core.PApplet;


public class AlgoBenchmark {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @State(Scope.Thread)
    public static class BenchmarkState {

        PApplet sketch = new PApplet();

        UI Ui = new UI(sketch);



        @Setup
        public void prepare() {

            Ui.add_Button("pause", (sketch.displayWidth)/9f, sketch.displayHeight, sketch.displayWidth/9f, 0,"⏯", Pause_Button.class, true);
            Ui.add_Button("forward", sketch.displayWidth/9f*2f, sketch.displayHeight, sketch.displayWidth/9f, 0,"⏵", Forward_Button.class, true);


        }
    }

    /*
    @Benchmark
    public void test_D_Star(BenchmarkState state) {

        Algorithm d_star = new DStarLite();
        Util.Make_Graph(state.sketch, 100, 100);
        d_star.set_Start(Main.node_array.getFirst());
        d_star.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (d_star.get_Goal() != d_star.get_Start()){
            d_star.Main();
        }
    }
    */

    @Benchmark
    public void test_LPA_Star(BenchmarkState state) {

        Algorithm lpa_star = new LPA_Star();

        Util.Make_Graph(state.sketch, 100, 100);

        lpa_star.set_Start(Main.node_array.getFirst());
        lpa_star.set_Goal(Main.node_array.get((Main.node_array.size()-1)/2));
        while (lpa_star.get_Goal() != lpa_star.get_Start()){
            lpa_star.Main();
        }
    }


}
