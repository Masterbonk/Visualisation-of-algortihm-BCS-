package org.algorithm.ui;

import processing.core.PApplet;

import static org.algorithm.ui.Color_Scheme.*;

public class Text_Box {

    PApplet sketch;
    float x, y, size_x, size_y;
    String text;
    int text_size = 30;
    int stroke = 3;

    public Text_Box(PApplet _sketch, float _x, float _y, String _text){
        sketch = _sketch;
        x = _x;
        y = _y;
        text = _text;
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
        sketch.fill(text_box_background);
        sketch.stroke(text_box_border);


        sketch.textSize(text_size);
        sketch.strokeWeight(stroke);


        sketch.textAlign(sketch.CENTER);
        sketch.rectMode(sketch.CENTER);

        sketch.rect(
                x,
                y - sketch.getGraphics().textSize * 0.35f,
                sketch.textWidth(text) * 1.1f,
                sketch.getGraphics().textSize * 1.1f,
                5

        );

        sketch.fill(text_box_text);

        sketch.text(text, x, y);

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

    /*
    * protected void render_Tooltip(){
        if(mouse_Over()){


        sketch.push();
        //sketch.fill(hover);
        sketch.fill(hover_text);
        sketch.textSize(25);
        //sketch.textSize(16);
        sketch.textAlign(CENTER,CENTER);

        //sketch.text(tmp,x-sketch.textWidth(tmp)/2,((y+dim/2f)+sketch.getGraphics().textSize)-25f);

        sketch.push();
        sketch.fill(hover);
        //sketch.noStroke();
        sketch.stroke(hover_stroke);
        sketch.strokeWeight(5);

        float top = y_pos + 1.5f * button_height;
        float bottom = y_pos - 0.5f * button_height;

        float textbox_width = sketch.textWidth(tool_tip)*0.65f;
        float textbox_height = sketch.getGraphics().textSize;

        float v = sketch.textWidth(tool_tip) / textbox_width;
        int lines = (int) Math.ceil(v);

        if(y_pos <= sketch.displayHeight/2f) {

            if (dropdown_control_button == null) {
                //if (!clicked) sketch.rect(x_pos, top,textbox_width,textbox_height*(v+2));
                if(x_pos >= sketch.displayWidth * 0.80) {
                    if(!clicked) sketch.rect(x_pos, top,textbox_width,textbox_height*(v+2));
                } else
                    if(x_pos >= sketch.displayWidth * 0.75) {
                        sketch.rect(x_pos- textbox_width/2, top,textbox_width,textbox_height*(v+2));
                    }
                else {
                        if (!clicked) sketch.rect(x_pos, top,textbox_width,textbox_height*(v+2));
                }

            } else {

                if(x_pos <= sketch.displayWidth/2f) {
                    //if(debug) sketch.rect(x_pos, top,textbox_width*100,textbox_height*(v+2));
                    sketch.rect(x_pos + x_size, y_pos,textbox_width,textbox_height*(v+2));

                }else {
                    //if(debug) sketch.rect(x_pos, top,textbox_width*100,textbox_height*(v+2));
                    sketch.rect(x_pos - textbox_width, y_pos,textbox_width,textbox_height*(v+2));
                }
            }

        }else{
            if(x_pos >= sketch.displayWidth * 0.80) {
                sketch.rect(x_pos-textbox_width/2, bottom - ((textbox_height * lines) * 1.5f), textbox_width, textbox_height * (v + 2));
            }else {
                sketch.rect(x_pos, bottom - ((textbox_height * lines) * 1.5f), textbox_width, textbox_height * (v + 2));
            }
        }
        sketch.pop();
        if(y_pos <= sketch.displayHeight/2f){
            if (dropdown_control_button == null) {
                //if (!clicked) sketch.text(tool_tip, x_pos, top, textbox_width, textbox_height * (v + 2));
                if(x_pos >= sketch.displayWidth * 0.80) {
                    if (!clicked) sketch.text(tool_tip, x_pos, top, textbox_width, textbox_height * (v + 2));
                } else
                if(x_pos >= sketch.displayWidth * 0.75) {
                    sketch.text(tool_tip, x_pos - textbox_width/2, top, textbox_width, textbox_height * (v + 2));
                }
                else {
                    if (!clicked) sketch.text(tool_tip, x_pos, top, textbox_width, textbox_height * (v + 2));
                }
            }else {
                if(x_pos <= sketch.displayWidth/2f){
                    sketch.text(tool_tip, x_pos + x_size, y_pos, textbox_width, textbox_height * (v + 2));

                }else {sketch.text(tool_tip, x_pos - textbox_width, y_pos, textbox_width, textbox_height * (v + 2));}

            }


        }else{
//            sketch.text(tool_tip, x_pos, bottom - (textbox_height * lines) ,textbox_width,textbox_height*(v+2));
            if(x_pos >= sketch.displayWidth * 0.80) {
                sketch.text(tool_tip, x_pos-textbox_width/2, bottom - ((textbox_height * lines) * 1.5f),textbox_width,textbox_height*(v+2));
            }else {
                sketch.text(tool_tip, x_pos, bottom - ((textbox_height * lines) * 1.5f),textbox_width,textbox_height*(v+2));
            }
        }
        sketch.pop();
        }
    }
    * */
}

