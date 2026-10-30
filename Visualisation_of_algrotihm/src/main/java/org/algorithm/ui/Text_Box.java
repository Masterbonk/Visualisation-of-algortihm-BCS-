package org.algorithm.ui;

import org.algorithm.ui.buttons.Button;
import processing.core.PApplet;

import static org.algorithm.ui.Color_Scheme.*;

import static org.algorithm.Main.Ui;
import static org.algorithm.Main.button_height;
import static processing.core.PApplet.println;
import static processing.core.PApplet.reverse;

public class Text_Box {

    PApplet sketch;
    float x, y, size_x, size_y;
    String text;
    int text_size = 30;
    int stroke = 3;
    float max_text_width = 20;
    Button button;
    float alt_x, alt_y;
    float text_box_width;
    float text_box_height;
    boolean node;


    public Text_Box(PApplet _sketch, float _x, float _y, String _text){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
    }

    public Text_Box(PApplet _sketch, float _x, float _y, String _text, Button _button){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
        button = _button;
    }

    public Text_Box(PApplet _sketch, float _x, float _y, String _text, boolean _node){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
        node = _node;
    }

    public Text_Box(PApplet _sketch, float _x, float _y, String _text, int _stroke){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
        stroke = _stroke;
    }

    public Text_Box(PApplet _sketch, float _x, float _y, int _text_size, String _text){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
        text_size = _text_size;
    }

    public Text_Box(PApplet _sketch, float _x, float _y, int _text_size, int _stroke, String _text){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
        text_size = _text_size;
        stroke = _stroke;
    }



    public void render(){

        if(text.isEmpty()) return;

        sketch.push();


        //color
        sketch.fill(text_box_background);
        sketch.stroke(text_box_border);


        sketch.textSize(text_size);
        sketch.strokeWeight(stroke);
        //sketch.noStroke();

        sketch.textAlign(sketch.CENTER);
        sketch.rectMode(sketch.CENTER);



        if (button != null) {
            max_text_width = button.getX_size();
        }




        text_box_width = max_text_width;

        int lines = 1;

        if (button != null || node) {
            String[] split_text = text.split(" ");
            StringBuilder tmp = new StringBuilder();

            for (String _text : split_text){
                tmp.append(_text).append(" ");
                if (sketch.textWidth(tmp.toString()) > (max_text_width+5)) {
                    lines++;
                    tmp = new StringBuilder();
                    tmp.append(_text).append(" ");

                }
            }
        }

        if (button != null) {

             if(text_box_width < sketch.textWidth(text)) {
                text_box_height = (sketch.getGraphics().textSize * 1.15f) * lines;
            } else {
                text_box_height = sketch.getGraphics().textSize;
            }

            if (button.dropdown_control_button != null){

                if (x > sketch.width/2f) {
                    alt_x = x - button.getX_size()/2 - 10;
                } else {
                    alt_x = x + button.getX_size() * 1.5f + 10;
                }

                alt_y = y + text_box_height/2;
            }
            else if (y > sketch.height/2f) {
                alt_x = x + button.getX_size()/2;
                alt_y = y - text_box_height/2;
            } else {
                alt_x = x + button.getX_size()/2;
                alt_y = y + text_box_height;
            }
        } else if (node) {
            text_box_width = sketch.textWidth(text)/2 + 22;
            text_box_height = sketch.getGraphics().textSize * 2.5f ;
            alt_x = x;
            alt_y = y + text_box_height;
        } else {
            alt_x = x;
            alt_y = y;

            text_box_height = sketch.getGraphics().textSize + 5;
            text_box_width = sketch.textWidth(text) + 10;
        }


        sketch.rect(
                alt_x,
                alt_y + 2,
                text_box_width,
                text_box_height + 5,
                5

        );

        sketch.fill(text_box_text);

        sketch.text(
                text,
                alt_x,
                (lines > 1 ? alt_y + 5 : alt_y + 10),
                text_box_width,
                text_box_height
        );

        sketch.pop();
    }

    public void update_Text(String _text){
        text = _text;
    }

    public void update_Text_Size(int _text_size){
        text_size = _text_size;
    }

    public void update_Color(int _text_size){
        text_size = _text_size;
    }

    public void update_Stroke(int _text_size){
        text_size = _text_size;
    }

    public void update_Position(int _text_size){
        text_size = _text_size;
    }

}

