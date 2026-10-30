package org.algorithm.ui.buttons;

import org.algorithm.Main;
import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;

public class Flag_A_Button extends Button {
    public Flag_A_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "The first Click places the start Flag, the second Click places the goal Flag";
        super.tool_tip_box.update_Text(tool_tip);
    }

    @Override
    public void click() {
        super.click();
    }
}
