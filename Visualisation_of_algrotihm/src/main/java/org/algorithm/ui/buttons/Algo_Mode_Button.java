package org.algorithm.ui.buttons;

import org.algorithm.Main;
import org.algorithm.algo.DStarLite;
import org.algorithm.algo.Visual_LPA;
import processing.core.PApplet;

import static org.algorithm.Main.*;
import static org.algorithm.ui.Color_Scheme.text_button;

public class Algo_Mode_Button extends Button {
    public Algo_Mode_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        clicked = true;
    }

    @Override
    public boolean mouse_Over() {

        if (super.mouse_Over()) {
            sketch.push();
            sketch.fill(text_button);
            sketch.textSize(16);
            sketch.text("Change which \n algorithm is run", x_pos, y_pos + 1.5f * button_height);
            sketch.pop();
        }
        return super.mouse_Over();
    }

    public void click(){
        super.click();


        Ui.get_Button("reset").click();


        if (!clicked) {
            text = "LPA*";
            algorithm = new Visual_LPA();

        } else {
            text = "D* Lite"; //Pause
            algorithm = new DStarLite();
            //Ui.get_Button("reset").click();
            //algorithm.set_Start(start_node);
            //algorithm.set_Goal(goal_node);
        }


        set_of_nodes.addAll(node_array);

    }
}
