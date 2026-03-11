package org.algorithm.ui.buttons;

import org.algorithm.algo.DStarLite;
import org.algorithm.algo.Visual_LPA;
import processing.core.PApplet;

import static org.algorithm.Main.*;

public class Algo_Mode_Button extends Button {
    public Algo_Mode_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        clicked = true;
    }

    public void click(){
        super.click();
        if (!clicked) {
            text = "LPA*";
            algorithm = new Visual_LPA();
            algorithm.set_Start(start_node);
            algorithm.set_Goal(goal_node);
        } else {
            text = "D* Lite"; //Pause
            algorithm = new DStarLite();
            //Ui.get_Button("reset").click();
            //algorithm.set_Start(start_node);
            //algorithm.set_Goal(goal_node);
        }
    }
}
