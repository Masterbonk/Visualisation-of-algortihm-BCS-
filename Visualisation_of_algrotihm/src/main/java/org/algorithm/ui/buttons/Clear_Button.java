package org.algorithm.ui.buttons;

import processing.core.PApplet;

import static org.algorithm.Util.delete_Graph;

public class Clear_Button extends Button {
    public Clear_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }
    public void click(){
        delete_Graph();
    }
}
