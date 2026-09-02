package org.algorithm.ui.buttons;

import processing.core.PApplet;

public class Graph_Button extends Button{
    public Graph_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "remake the original graph";

    }

    public void click(){
       //initialse graph
    }
}
