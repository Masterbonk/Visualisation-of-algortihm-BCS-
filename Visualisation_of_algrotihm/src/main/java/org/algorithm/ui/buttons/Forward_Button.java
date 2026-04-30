package org.algorithm.ui.buttons;

import org.algorithm.algo.Algorithm;
import processing.core.PApplet;

import static org.algorithm.Main.*;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;

public class Forward_Button extends Button {
    public Forward_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Go through the algorithm one step at the time";

    }

    public void click(){
        clicked = true;
        Ui.get_Button("pause").clicked = false;
        Algorithm.has_been_paused = false;
        node_1 = null;

    }


}
