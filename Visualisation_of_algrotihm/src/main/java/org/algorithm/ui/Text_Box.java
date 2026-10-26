package org.algorithm.ui;

import org.algorithm.ui.buttons.Button;
import processing.core.PApplet;

import static org.algorithm.Main.Ui;
import static org.algorithm.Main.button_height;
import static processing.core.PApplet.println;

public class Text_Box {

    PApplet sketch;
    float x, y, size_x, size_y;
    String text;
    int text_size = 30;
    int stroke = 5;
    int max_text_width = 20;
    Button button;
    float alt_x, alt_y;

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

        sketch.push();

        //color
        sketch.fill(0);
        sketch.stroke(100);

        sketch.textSize(text_size);
        sketch.strokeWeight(stroke);

        double lines = 1;

        float text_box_width = sketch.textWidth(text) + 10;


        if (max_text_width > 0) {

            println("'" + text + "' text width: " +sketch.textWidth(text));

            lines = sketch.textWidth(text) / ((sketch.textWidth(text) / text.length()) * max_text_width);
            lines = Math.floor(lines);
            if (lines <= 0) lines = 1;

            if (sketch.textWidth(text) > (sketch.textWidth(text) / text.length()) * max_text_width + 10) {
                text_box_width = (sketch.textWidth(text) / text.length()) * max_text_width + 10;
            }

            println("text box width: " + text_box_width);

            println("line count: " + lines);
        }

        float text_box_height = sketch.getGraphics().textSize * (float)lines;
        println("text box height: " + text_box_height);


        sketch.textAlign(sketch.CENTER);
        //sketch.rectMode(sketch.CENTER);




        if (Ui.top_ui.contains((button.name)) /*top*/){

            // add to y to adjust down
            println("top");

            if (button.getX_pos() < sketch.width/2f /*left*/){
                // add to x to adjust right
                println("left");
            } else {
                //subtract from x to adjust left
                println("right");
            }

        } else {
            println("bottom");

            alt_y = y - button_height/2;
            //subtract form 'y' to adjust up

        }

        sketch.rect(
                x,
                alt_y - sketch.getGraphics().textSize,
                text_box_width,
                text_box_height,
                5

        );

        sketch.fill(255);

        sketch.text(
                text,
                x,
                alt_y - sketch.getGraphics().textSize,
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

