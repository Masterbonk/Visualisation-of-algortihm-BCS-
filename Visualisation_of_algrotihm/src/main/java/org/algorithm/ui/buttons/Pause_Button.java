package org.algorithm.ui.buttons;

import org.algorithm.algo.Algorithm;
import processing.core.PApplet;

public class Pause_Button extends Button {

    public Pause_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
        clicked = true;
    }

    public void click(){
        if (!clicked) {
            text = "⏸"; //start
        } else text = "⏯"; //Pause

        Algorithm.has_been_paused = !Algorithm.has_been_paused;

        super.click();


    }
}
