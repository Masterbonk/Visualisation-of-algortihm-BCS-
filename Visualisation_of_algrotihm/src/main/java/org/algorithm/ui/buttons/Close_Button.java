package org.algorithm.ui.buttons;

import org.algorithm.Main;
import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.debug;
import static org.algorithm.ui.Color_Scheme.bg_button_hover;
import static processing.core.PApplet.str;

public class Close_Button extends Button {

    float text_size;

    /**
     * Button class is used to make all buttons in the program. They have their size, position and a text inside them all built in.
     *
     * @param _sketch
     * @param _x_pos  The x_pos is the x position of the square that makes up the button. Effectively where on x axis the left side of the button is.
     * @param _y_pos  The y_pos is the y position of the square that makes up the button. Effectively where on y axis the top side of the button is.
     * @param _x_size How wide the button is.
     * @param _y_size How high the button is.
     * @param _text
     */
    public Close_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text, boolean _has_tool_tip, float _text_size) {
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text, _has_tool_tip);
        text_size = _text_size;

    }

    @Override
    public void click() {
        Main.currentInput =  new ArrayList<Character>();
        Main.display_edge_weight_ui = false;
        Main.activeEdge = null;
        clicked = true;
        super.click();
    }

    public void render(){
        if (dropdown_control_button == null) {
            visual_Render_Logic();
        } else if (dropdown_control_button.clicked){
            visual_Render_Logic();
        }
    }

    public void visual_Render_Logic(){
        sketch.fill(Color_Scheme.border_button);
        sketch.rect(x_pos, y_pos, x_size, y_size);

        int changeVal = 5;

        sketch.textSize(text_size);

        //hover
        if (mouse_Over()) {
            sketch.push();
            sketch.fill(bg_button_hover);
            sketch.rect(x_pos + changeVal, y_pos + changeVal, x_size - changeVal * 2, y_size - changeVal * 2);
            sketch.fill(Color_Scheme.text_button_hover); //Text color

            if (dropdown_control_button == null) {
                //sketch.textSize((x_size - changeVal) / 5f);
            }else // sketch.textSize((x_size - changeVal)  / 6f);

            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos ),  (y_pos + y_size) - 5  );
            sketch.pop();

        } else if (clicked) {

            sketch.push();
            sketch.fill(Color_Scheme.bg_button_clicked);
            sketch.rect(x_pos + changeVal, y_pos + changeVal, x_size - changeVal * 2, y_size - changeVal * 2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color

            if (dropdown_control_button == null) {
                //sketch.textSize((x_size - changeVal) / 5f);
            }else //sketch.textSize((x_size - changeVal)  / 6f);

            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos)  ,  (y_pos + y_size) - 5  );
            sketch.pop();

        } else {
            sketch.push();
            sketch.fill(Color_Scheme.bg_button);
            sketch.rect(x_pos, y_pos, x_size, y_size);
            sketch.fill(Color_Scheme.text_button);

            if (dropdown_control_button == null) {
               // sketch.textSize(x_size / 5f);
            }else //sketch.textSize(x_size / 6f);

            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos), (y_pos + y_size) - 5 );
            sketch.pop();

        }

    }
}
