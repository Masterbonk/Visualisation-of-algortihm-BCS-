package org.algorithm;

import processing.core.PApplet;

public class Color_Scheme {
    PApplet sketch;

    public static int bg;
    public static int bg_button;
    public static int text_button;
    public static int debug_text_button;
    public static int bg_button_hover;
    public static int text_button_hover;
    public static int text_button_clicked;
    public static int bg_button_clicked;
    public static  int border_button;
    public static int line;
    public static int node;
    Color_Scheme(PApplet _sketch){
        sketch = _sketch;
    }
    //bla
    public void changeColors(boolean pink){
        if (pink){
            bg = sketch.color(226,177,224);
            //make colors pink
            update_Button_Colors(true);
        } else {
            //default colors
            bg = sketch.color(204);
            update_Button_Colors(false);

            //buttons





        }
    }

    private void update_Button_Colors(boolean pink){
        if (pink){
            //debug
            debug_text_button = sketch.color(255, 255, 255);

            //hover
            text_button_hover = sketch.color(201,116,322);
            bg_button_hover = sketch.color(90,29,109);

            //clicked
            text_button_clicked = sketch.color(255,255,255);
            bg_button_clicked = sketch.color(144,211,139);

            //normal 56,28,54
            border_button = sketch.color(140,85,147);
            bg_button = sketch.color(145,50, 129);
            text_button = sketch.color(252,164,237);

        } else{
            //debug
            debug_text_button = sketch.color(255, 255, 255);

            //hover
            text_button_hover = sketch.color(255f);
            bg_button_hover = sketch.color(0f);

            //clicked
            text_button_clicked = sketch.color(255f);
            bg_button_clicked = sketch.color(127,178,96);

            //normal
            border_button = sketch.color(162f,162f,162f);
            bg_button = sketch.color(80f);
            text_button = sketch.color(255f);

        }
    }
}
