package org.algorithm.ui.buttons;

import org.algorithm.Main;
import org.algorithm.graph.edges.Edge;
import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;

public class Weight_Button extends Button {
    public Weight_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Click on an edge and change its weight";

    }

    public void click(){
        super.click();

        for (Edge e : Main.edge_array){
            Main.edge_state_set.put(e,e.get_Edge_state());
        }

    }


}
