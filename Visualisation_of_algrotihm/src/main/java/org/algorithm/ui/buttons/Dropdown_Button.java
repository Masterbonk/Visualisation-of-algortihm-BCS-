package org.algorithm.ui.buttons;

import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.ui.Color_Scheme.text_button_hover;

public class Dropdown_Button extends Button{

    public Dropdown_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text) {
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    public void click(){super.click();
    }
}
