package org.algorithm;

import processing.core.PApplet;

import static org.algorithm.Main.Ui;


public class Util {
    PApplet sketch;
    int button_height;
    Util(PApplet _sketch, int _button_height){
        sketch = _sketch;
        button_height = _button_height;
    }


    /**
     * Makes the Ui elements, ie. all buttons All are added to the button arrays so they are rendered.
     */
    public static void Make_UI(PApplet _sketch, int _button_height){

        //bottom ui
        Ui.add_Button("back", 0, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏴", Back_Button.class, true);

        Ui.add_Button("pause", (_sketch.displayWidth)/9f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏯", Pause_Button.class, true);

        Ui.add_Button("forward", _sketch.displayWidth/9f*2f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏵", Forward_Button.class, true);

        Ui.add_Button("cut", _sketch.displayWidth/9f*3f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"✂", Cut_Button.class, true);

        Ui.add_Button("circle", _sketch.displayWidth/9f*4f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⏺", Circle_Button.class, true);

        Ui.add_Button("line", _sketch.displayWidth/9f*5f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"\\", Line_Button.class, true);

        Ui.add_Button("flag_a", _sketch.displayWidth/9f*6f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⚑", Flag_A_Button.class, true);

        Ui.add_Button("flag_b", _sketch.displayWidth/9f*7f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"⚐", Flag_B_Button.class, true);

        Ui.add_Button("weight", _sketch.displayWidth/9f*8f, _sketch.displayHeight-_button_height, _sketch.displayWidth/9f, _button_height,"Weight", Weight_Button.class, true);

        //top ui
        Ui.add_Button("file", 0, 0, _sketch.displayWidth/9f, _button_height,"File", File_Button.class, false);

        Ui.add_Button("export", 0, _button_height+_button_height/10f, _sketch.displayWidth/10f ,_button_height-_button_height/10f,"Export", Export_Button.class, false);

        Ui.add_Button("import", 0, _button_height*2+_button_height/10f, _sketch.displayWidth/10f, _button_height-_button_height/10f,"Import", Import_Button.class, false);

        Ui.add_Button("Node_display",1000, 100, 100, _button_height,"Display N", Node_Botton.class, false);

        Ui.add_Button("Edge_display",1000, 100, 100, _button_height,"Display E", Edge_Button.class, false);

    }
}
