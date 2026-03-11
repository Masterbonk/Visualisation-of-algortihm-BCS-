package org.algorithm.graph.edges;

import garciadelcastillo.dashedlines.DashedLines;
import org.algorithm.graph.Node;
import processing.core.PApplet;

import static org.algorithm.Main.*;

public class Heuristic_Edge extends Edge{

    DashedLines dash;
    public Heuristic_Edge(PApplet _sketch, Node _from, Node _to){
        super(_sketch, _from, _to, 0);

        dash = new DashedLines(sketch);

        super.from.get_Connected().remove(this);
        to.get_Connected().remove(this);
        edge_array.remove(this);
        edge_update_map.remove(this);
    }

    public void set_To(Node _n){
        to = _n;
    }
    public void set_From(Node _n){
        from = _n;
    }


    @Override
    public void render() {
        if(to != null && from != null){
            update_Weight(0);
            set_To(start_node);
            set_From(goal_node);
            if (Ui.get_Button("heuristic").clicked) {
                sketch.push();
                sketch.strokeWeight(2);
                dash.pattern(20, 20);
                dash.line(to.get_X(),to.get_Y(),from.get_X(),from.get_Y());
                sketch.pop();

                render_Weight();

            }
        }

    }

}
