package org.algorithm.ui.buttons;

import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import static org.algorithm.Main.*;


public abstract class Button {
    float x_pos, y_pos, x_size, y_size;
    public String text, name;
    public boolean clicked = false;
    public boolean bottom;

    public PApplet sketch;
    /**
     * Button class is used to make all buttons in the program. They have their size, position and a text inside them all built in.
     *
     *
     * @param _x_pos The x_pos is the x position of the square that makes up the button. Effectively where on x axis the left side of the button is.
     * @param _y_pos The y_pos is the y position of the square that makes up the button. Effectively where on y axis the top side of the button is.
     * @param _x_size How wide the button is.
     * @param _y_size How high the button is.
     */
    public Button(PApplet _sketch,float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        sketch = _sketch; x_pos = _x_pos; y_pos = _y_pos; x_size = _x_size; y_size = _y_size; text = _text;
    }

    /**
     * Draw is used to draw the button at the end of every draw loop. ALso makes it change size and color
     * if the mouse button hovers over it, or clicks it
     */

    public void render(){
        sketch.fill(Color_Scheme.border_button);
        sketch.rect(x_pos,y_pos, x_size, y_size);

        int changeVal = 5;
        if (debug) {
            sketch.push();
            sketch.fill(Color_Scheme.debug_text_button); //Text color
            sketch.textSize((x_size - changeVal) / 5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(str(clicked), (x_pos + changeVal) + (x_size - changeVal * 2) / 2f, (y_pos - 10));
            sketch.pop();
        }


        //hover
        if (mouse_Over()){
            sketch.push();
            sketch.fill(Color_Scheme.bg_button_hover);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(Color_Scheme.text_button_hover); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else if (clicked){

            sketch.push();
            sketch.fill(Color_Scheme.bg_button_clicked);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(Color_Scheme.text_button_clicked); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else {
                sketch.push();
                sketch.fill(Color_Scheme.bg_button);
                sketch.rect(x_pos,y_pos, x_size, y_size);
                sketch.fill(Color_Scheme.text_button);
                sketch.textSize(x_size/5f);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos)+(x_size)/2f, (y_pos)+(y_size)/2f+10);
                sketch.pop();

        }
    }


    public void resize(float _x_pos, float _y_pos, float _x_size, float _y_size){
        x_pos = _x_pos;
        y_pos = _y_pos;
        x_size = _x_size;
        y_size = _y_size;

    }

    /**
     * @return is true if the mouse is currently hovering over the button.
     */

    public boolean mouse_Over(){
        if (sketch.mouseX >= x_pos && sketch.mouseX <= x_pos+x_size &&
                sketch.mouseY >= y_pos && sketch.mouseY <= y_pos+y_size){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Click is extended to all subclass of buttons and is used to activate the functionality of the button once it's clicked.
     */
    public void click(){
        clicked = !clicked;
        node_1 = null;
        Ui.turn_Off_All_Buttons(this);
        //sketch.println("Not implemented");
    }

}

