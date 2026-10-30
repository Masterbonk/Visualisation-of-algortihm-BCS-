package org.algorithm.ui.buttons;

import org.algorithm.Main;
import org.algorithm.algo.*;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;
import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import static org.algorithm.Egde_State.idle;
import static org.algorithm.Main.*;

public class Reset_Button extends Button {
    public Reset_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Reset the algorithm";
        super.tool_tip_box.update_Text(tool_tip);
    }

    public void click(){
        super.click();
        zoom_level = 1f;
        println("Clicked reset");
        switch (algo_state) {
            case 0:
                algorithm = new Visual_Dijkstra();
                break;
            case 1:
                algorithm = new Visual_AStar();
                break;
            case 2:
                algorithm = new Visual_LPA();
                algorithm.initialize();
                break;
            case 3:
                println("d* reset");
                if (!Ui.get_Button("pause").clicked) Main.Ui.get_Button("pause").click();

                algorithm = new Visual_DStarLite();

                algorithm.first_run = true;


                algorithm.set_Start(initial_start_node);
                algorithm.set_Goal(initial_goal_node);


                algorithm.initialize();

                break;
        }

        Ui.unlock_All_Buttons();


        algorithm.set_Start(initial_start_node);
        algorithm.set_Goal(initial_goal_node);


        for (Edge e: Main.edge_array) {
            e.set_Enum(idle);
        }
        for (Node n : algorithm.set_of_nodes){
            n.color(Color_Scheme.node_idle);
            n.change_In_PQ(false);
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
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
