package org.algorithm.ui.buttons;

import org.algorithm.algo.Visual_AStar;
import org.algorithm.algo.Visual_DStarLite;
import org.algorithm.algo.Visual_Dijkstra;
import org.algorithm.algo.Visual_LPA;
import org.algorithm.ui.Color_Scheme;
import org.algorithm.ui.UI;
import processing.core.PApplet;

import static org.algorithm.Main.*;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;


// Not used anymore

public class Algo_Mode_Button extends Button {

    boolean right_click = false;

    public Algo_Mode_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        clicked = true;
        super.tool_tip = "Change which algorithm is run. The program is currently using: " + text;
        super.tool_tip_box.update_Text(tool_tip);
    }

    public void click(boolean _right_click){

    }
    public boolean mouse_Over(){
        return false;
    }

    public void click(){

    }

    public void render(){

        switch (algo_state) {
            case 0:
                text = "Dijkstra";
                break;
            case 1:
                text = "A*";
                break;
            case 2:
                text = "LPA*";
                break;
            case 3:
                text = "D* Lite";
                break;
        }
        sketch.fill(Color_Scheme.border_button);
        sketch.rect(x_pos,y_pos, x_size, y_size);

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
        if (algo_state == 0){

            sketch.push();
            sketch.fill(Color_Scheme.bg_button_algo_zero);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else if (algo_state == 1) {
            sketch.push();
            sketch.fill(Color_Scheme.bg_button_algo_one);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else if (algo_state == 2){
            sketch.push();
            sketch.fill(Color_Scheme.bg_button_algo_two);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else if (algo_state == 3){
            sketch.push();
            sketch.fill(Color_Scheme.bg_button_algo_three);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();
        }

        /*

        render_Tooltip();
        */
    }

}
