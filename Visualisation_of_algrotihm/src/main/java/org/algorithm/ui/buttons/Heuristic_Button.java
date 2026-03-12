package org.algorithm.ui.buttons;

import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.ui.Color_Scheme.text_button;

public class Heuristic_Button extends Button {
    public Heuristic_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }
    public void click(){super.click();
    }

    public boolean mouse_Over() {

        if (super.mouse_Over()) {
            sketch.push();
            sketch.fill(text_button);
            sketch.textSize(16);
            sketch.text("Shows the heuristic from \n start to goal node", x_pos, y_pos + 1.5f * button_height);
            sketch.pop();
        }
        return super.mouse_Over();
    }
}
