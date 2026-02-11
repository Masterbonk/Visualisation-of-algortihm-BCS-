package org.algorithm;

import processing.core.PApplet;

import static org.algorithm.Main.*;
import static processing.core.PApplet.str;


class Button {
    float x_pos, y_pos, x_size, y_size;
    String text, name;
    boolean clicked = false;
    boolean bottom;

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

    void render(){
        sketch.fill(162f,162f,162f);
        sketch.rect(x_pos,y_pos, x_size, y_size);

        int changeVal = 5;
        if (debug) {
            sketch.push();
            sketch.fill(255, 255, 255); //Text color
            sketch.textSize((x_size - changeVal) / 5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(str(clicked), (x_pos + changeVal) + (x_size - changeVal * 2) / 2f, (y_pos - 10));
            sketch.pop();
        }



        if (mouse_Over()){
            sketch.push();
            sketch.fill(0f);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(255f); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else if (clicked){

            sketch.push();
            sketch.fill(127,178,96);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(255f); //Text color
            sketch.textSize((x_size-changeVal)/5f);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
            sketch.pop();

        } else {
                sketch.push();
                sketch.fill(80f);
                sketch.rect(x_pos,y_pos, x_size, y_size);
                sketch.fill(255f);
                sketch.textSize(x_size/5f);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos)+(x_size)/2f, (y_pos)+(y_size)/2f+10);
                sketch.pop();

        }
    }


    void resize(float _x_pos, float _y_pos, float _x_size, float _y_size){
        x_pos = _x_pos;
        y_pos = _y_pos;
        x_size = _x_size;
        y_size = _y_size;

    }

    /**
     * @return is true if the mouse is currently hovering over the button.
     */

    boolean mouse_Over(){
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
    void click(){
        clicked = !clicked;
        Ui.turn_Off_All_Buttons(this);
        //sketch.println("Not implemented");
    }
}

class Back_Button extends Button{
    public Back_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented Back");
    }
}

class Pause_Button extends Button{
    boolean paused = false;

    public Pause_Button(PApplet _sketch,float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();

        if (!paused) {
            text = "⏸"; //start
        } else text = "⏯"; //Pause
        paused = !paused;
        sketch.println("Not fully implemented Pause");
    }
}

class Forward_Button extends Button{
    public Forward_Button(PApplet _sketch,float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented Forward");
    }
}

class Cut_Button extends Button{
    public Cut_Button(PApplet _sketch,float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        sketch.println("Not implemented Cut");
    }
}

class Circle_Button extends Button{
    public Circle_Button(PApplet _sketch,float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        super.click();
        //sketch.println("Not implemented 5");


    }
}

class Line_Button extends Button{
    public Line_Button(PApplet _sketch,float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click() {
        super.click();
        node_1 = null;
    }
}
class Flag_A_Button extends Button{
    public Flag_A_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        sketch.println("Not implemented Flag A");
    }
}
class Flag_B_Button extends Button{
    public Flag_B_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        sketch.println("Not implemented Flag B");
    }
}
class Weight_Button extends Button{
    public Weight_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        sketch.println("Not implemented Weight");
    }
}

class File_Button extends Button{
    public File_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        //Main.file_clicked = !Main.file_clicked;
    }
}

/**
 * Unique type knap til knapper under file, som skal gemmes væk når man ikke har klikket på file.
 */

class File_Type_Buttons extends Button{

    public File_Type_Buttons(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void render(){
        if (Ui.get_Button("file").clicked){
            sketch.fill(162f);
            sketch.rect(x_pos,y_pos, x_size, y_size);

            int changeVal = 5;

            if (mouse_Over()){
                sketch.push();
                sketch.fill(0f);
                sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
                sketch.fill(255f); //Text color
                sketch.textSize((x_size-changeVal)/5f);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2f, (y_pos+changeVal)+(y_size-changeVal*2)/2f+10);
                sketch.pop();
            } else {
                sketch.push();
                sketch.fill(80f);
                sketch.rect(x_pos,y_pos, x_size, y_size);
                sketch.fill(255f);
                sketch.textSize(x_size/5f);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos)+(x_size)/2f, (y_pos)+(y_size)/2f+10);
                sketch.pop();
            }
        }
    }

}

class Export_Button extends File_Type_Buttons{
    public Export_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        sketch.println("Not implemented Export");
    }
}

class Import_Button extends File_Type_Buttons{
    public Import_Button(PApplet _sketch, float _x_pos, float _y_pos, float _x_size, float _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){super.click();
        sketch.println("Not implemented Import");
    }
}