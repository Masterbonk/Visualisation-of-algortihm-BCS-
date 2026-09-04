package org.algorithm.ui.buttons;

import processing.core.PApplet;

import static org.algorithm.Main.*;
import static org.algorithm.ui.Color_Scheme.text_button;
import static org.algorithm.ui.Color_Scheme.text_button_hover;

public class Node_Button extends Button {
    public Node_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Tool tip";
    }

    public void render(){

        super.render();

        switch (algo_state) {
            case 0:
                Ui.get_Button("Node_display").text = "Dist";
                Ui.get_Button("Node_display").tool_tip = "Display the dist and prev values of the nodes";
                break;
            case 1:
                Ui.get_Button("Node_display").text = "F()";
                Ui.get_Button("Node_display").tool_tip = "Display the dist and prev values of the nodes";
                break;
            case 2:
                Ui.get_Button("Node_display").text = "Rhs & g";
                Ui.get_Button("Node_display").tool_tip = "Display the G & RHS values of the nodes";
                break;
            case 3:
                Ui.get_Button("Node_display").text = "Rhs & g";
                Ui.get_Button("Node_display").tool_tip = "Display the G & RHS values of the nodes";
                break;
        }

    }

}
