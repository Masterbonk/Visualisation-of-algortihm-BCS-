package org.algorithm.ui.buttons;

import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import static org.algorithm.Main.button_height;
import static org.algorithm.Main.debug;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;
import static processing.core.PApplet.str;

public class PQueue_Button extends Button{
    public PQueue_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text) {
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    public void click(){
        super.click();
    }

    public void visual_Render_Logic(){
        sketch.fill(Color_Scheme.border_button);
        sketch.rect(x_pos, y_pos, x_size, y_size);

        int changeVal = 5;
        if (debug) {
            sketch.push();
            sketch.fill(Color_Scheme.debug_text_button); //Text color
            sketch.textSize((x_size - changeVal) / 5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(str(clicked), (x_pos + changeVal) + (x_size - changeVal * 2) / 2f, (y_pos - 10));
            sketch.pop();
        }


        //hover
        if (mouse_Over()) {
            sketch.push();
            sketch.fill(Color_Scheme.bg_button_hover);
            sketch.rect(x_pos + changeVal, y_pos + changeVal, x_size - changeVal * 2, y_size - changeVal * 2);
            sketch.fill(Color_Scheme.text_button_hover); //Text color

            if (dropdown_control_button == null) {
                sketch.textSize((x_size - changeVal) / 5.5f);
            }else sketch.textSize((x_size - changeVal)  / 6.5f);

            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos + changeVal) + (x_size - changeVal * 2) / 2f, (y_pos + changeVal) + (y_size - changeVal * 2) / 2f + 10);
            sketch.pop();

        } else if (clicked) {

            sketch.push();
            sketch.fill(Color_Scheme.bg_button_clicked);
            sketch.rect(x_pos + changeVal, y_pos + changeVal, x_size - changeVal * 2, y_size - changeVal * 2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color

            if (dropdown_control_button == null) {
                sketch.textSize((x_size - changeVal) / 5.5f);
            }else sketch.textSize((x_size - changeVal)  / 6.5f);

            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos + changeVal) + (x_size - changeVal * 2) / 2f, (y_pos + changeVal) + (y_size - changeVal * 2) / 2f + 10);
            sketch.pop();

        } else {
            sketch.push();
            sketch.fill(Color_Scheme.bg_button);
            sketch.rect(x_pos, y_pos, x_size, y_size);
            sketch.fill(Color_Scheme.text_button);

            if (dropdown_control_button == null) {
                sketch.textSize(x_size / 5.5f);
            }else sketch.textSize(x_size / 6.5f);

            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos) + (x_size) / 2f, (y_pos) + (y_size) / 2f + 10);
            sketch.pop();

        }
    }

    public boolean mouse_Over() {

        if (super.mouse_Over()) {
            sketch.push();
            sketch.fill(text_button_hover);
            sketch.textSize(16);
            sketch.text("Shows the Priority Queue live \n while the algorithm is running", x_pos, y_pos + 1.5f * button_height);
            sketch.pop();
        }
        return super.mouse_Over();
    }
}