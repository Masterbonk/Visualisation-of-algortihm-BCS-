package org.algorithm.ui.buttons;

import org.algorithm.Main;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;
import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import static org.algorithm.Main.*;

public class Reset_Button extends Button {
    public Reset_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    public void click(){
        super.click();
        algorithm.first_run = true;
        if(algorithm.get_Start() != null && algorithm.get_Goal() != null || start_node != null && goal_node != null) {
            algorithm.set_Start(initial_start_node);
            algorithm.set_Goal(initial_goal_node);
            algorithm.initialize();

        }


        for (Edge e: Main.edge_array) {
            e.color(75,75,75);
        }
        for (Node n : set_of_nodes){
            n.color(Color_Scheme.node);
            n.change_In_PQ(false);
        }
        if (algorithm != null && algorithm.get_U() != null) {
            algorithm.get_U().clear_Heap();
            algorithm.get_U().clear_Keys();
        }
        if (!Ui.get_Button("pause").clicked){
            Ui.get_Button("pause").click();
        }
        clicked = false;

    }
}
