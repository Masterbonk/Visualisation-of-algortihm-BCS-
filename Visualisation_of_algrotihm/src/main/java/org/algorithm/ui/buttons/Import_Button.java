package org.algorithm.ui.buttons;

import org.algorithm.Main;
import processing.core.PApplet;

import static org.algorithm.Util.delete_Graph;

public class Import_Button extends File_Type_Buttons{
    public Import_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    public void click(){
        super.click();

        delete_Graph();
        Main.zoom_level = 1f;
        //calls the function file_Selected in main
        sketch.selectInput("Select a file to process:", "file_Selected");
    }
}
