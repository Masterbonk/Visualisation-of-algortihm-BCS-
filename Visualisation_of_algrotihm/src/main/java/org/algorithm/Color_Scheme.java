package org.algorithm;

import processing.core.PApplet;

public class Color_Scheme {
    PApplet sketch;

    public static int bg;
    public static int bg_button;
    public static int border_button;
    public static int text;
    public static int text_button;
    public static int bg_button_hover;
    public static int text_button_hover;
    public static int line;
    public static int node;
    Color_Scheme(PApplet _sketch){
        sketch = _sketch;
    }

    public void changeColors(boolean pink){
        if (pink){
            //make colors pink
        } else {
            //default colors
            bg = sketch.color(204);
            /*bg_button = sketch.color()
            border_button;
            text;*/
            text_button = sketch.color(255, 255, 255);
            text_button_hover = sketch.color(255f);
            bg_button_hover = sketch.color(0f);
            /*line;
            node;*/
        }
    }
}
