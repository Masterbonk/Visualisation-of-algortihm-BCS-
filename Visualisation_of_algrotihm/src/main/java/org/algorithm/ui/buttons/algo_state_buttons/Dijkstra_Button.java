package org.algorithm.ui.buttons.algo_state_buttons;

import org.algorithm.Main;
import org.algorithm.algo.Visual_Dijkstra;
import org.algorithm.ui.buttons.Button;
import processing.core.PApplet;

import static org.algorithm.Main.algorithm;

public class Dijkstra_Button extends Button {
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
    public Dijkstra_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text) {
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Change which algorithm is run. The program is currently using";
        clicked = true;
    }

    @Override
    public void click() {
        Main.algo_state = 0;
        Main.Ui.get_Button("reset").click();
        if (!clicked) {
            Main.Ui.get_Button("A*").clicked = false;
            Main.Ui.get_Button("LPA*").clicked = false;
            Main.Ui.get_Button("D* Lite").clicked = false;
        }
        algorithm = new Visual_Dijkstra();

        super.click();
    }

}
