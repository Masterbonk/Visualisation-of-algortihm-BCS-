package org.algorithm.ui.buttons;

import processing.core.PApplet;

public class PQ_Highlight_Node_Button extends Button{
    public PQ_Highlight_Node_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text) {
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        clicked = true;
        tool_tip = "Determines if the top node of the Priority Queue will be highlighted or not";
        super.tool_tip_box.update_Text(tool_tip);
    }
//            Ui.get_Button("node_highlight").clicked = true;
    @Override
    public void click() {
        super.click();
    }
}
