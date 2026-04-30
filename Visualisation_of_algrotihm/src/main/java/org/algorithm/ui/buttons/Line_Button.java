package org.algorithm.ui.buttons;

import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;

public class Line_Button extends Button {
    public Line_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Add new edges to the Graph by clicking on nodes or anywhere on the screen";

    }




}
