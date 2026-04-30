package org.algorithm.ui.buttons;

import org.algorithm.Main;
import processing.core.PApplet;

import static org.algorithm.Main.algorithm;
import static org.algorithm.Util.delete_Graph;

public class Import_Button extends Button{
    public Import_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
        super.tool_tip = "Import OSM file";

    }

    public void click(){
        super.click();

        if(!Main.Ui.get_Button("pause").clicked){
            Main.Ui.get_Button("pause").click();
        }
        algorithm.set_Goal(null);
        algorithm.set_Start(null);

        Main.initial_start_node = null;
        Main.initial_goal_node = null;

        delete_Graph();
        Main.zoom_level = 1f;
        //calls the function file_Selected in main
        Main.importing = true;
        sketch.selectInput("Select a file to process:", "file_Selected");
        clicked = false;
    }
}
