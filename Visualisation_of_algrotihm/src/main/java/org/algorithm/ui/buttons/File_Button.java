package org.algorithm.ui.buttons;

import processing.core.PApplet;

public class File_Button extends Button {
    public File_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);

    }

    public void click(){super.click();
        //Main.file_clicked = !Main.file_clicked;
    }
}
