package org.algorithm.ui.buttons;

import org.algorithm.Util;
import processing.core.PApplet;

public class Square_Graph_Button extends Button{
    public Square_Graph_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Make a square of 5x5";

    }

    public void click(){
        Util.delete_Graph();
        Util.Make_Graph(sketch,5,5);
    }
}
