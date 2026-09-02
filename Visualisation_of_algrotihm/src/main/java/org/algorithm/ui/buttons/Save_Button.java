package org.algorithm.ui.buttons;

import processing.core.PApplet;
import static org.algorithm.graph.IO.Export_Handler.Export;

public class Save_Button extends Button{
    public Save_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Save to new graph file";

    }

    public void click(){
        super.click();
        Export();
        this.clicked = false;
    }
}
