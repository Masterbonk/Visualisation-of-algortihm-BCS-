package org.algorithm.ui.buttons;

import org.algorithm.Main;
import org.algorithm.graph.edges.Edge;
import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.ui.Color_Scheme.*;

public class Cut_Button extends Button {

    public Cut_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Delete nodes or lines from the graph";

    }

    @Override
    public void click() {
        super.click();

        for (Edge e : Main.edge_array){
            Main.edge_state_set.put(e,e.get_Edge_state());
        }

    }
}
