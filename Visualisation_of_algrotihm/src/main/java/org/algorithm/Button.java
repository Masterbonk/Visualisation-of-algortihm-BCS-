package org.algorithm;

import processing.core.PApplet;


class Button {
    float x_pos, y_pos;
    int x_size, y_size;
    String text;

    public PApplet sketch;
    /**
     * Button class is used to make all buttons in the program. They have their size, position and a text inside them all built in.
     *
     *
     * @param _x_pos The x_pos is the x position of the square that makes up the button. Effectively where on x axis the left side of the button is.
     * @param _y_pos The y_pos is the y position of the square that makes up the button. Effectively where on y axis the top side of the button is.
     * @param _x_size How wide the button is.
     * @param _x_pos How high the buttom is.
     */
    Button(PApplet _sketch,float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        sketch = _sketch; x_pos = _x_pos; y_pos = _y_pos; x_size = _x_size; y_size = _y_size; text = _text;
    }

    /**
     * Draw is used to draw the button at the end of every draw loop. ALso makes it change size and color if the mosue button hovers over it.
     */

    void drawing(){
        sketch.fill(162f,162f,162f);
        sketch.rect(x_pos,y_pos, x_size, y_size);

        int changeVal = 5;

        if (mouse_Over()){
            sketch.fill(0f);
            sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
            sketch.fill(255f); //Text color
            sketch.textSize((x_size-changeVal)/5);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2, (y_pos+changeVal)+(y_size-changeVal*2)/2+10);
        } else {
            sketch.fill(80f);
            sketch.rect(x_pos,y_pos, x_size, y_size);
            sketch.fill(255f);
            sketch.textSize(x_size/5);
            sketch.textAlign(sketch.CENTER);
            sketch.text(text, (x_pos)+(x_size)/2, (y_pos)+(y_size)/2+10);
        }
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
        sketch.println("Not implemented");
    }
}

class Back_Button extends Button{
    Back_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 1");
    }
}

class Pause_Button extends Button{
    boolean paused = false;

    Pause_Button(PApplet _sketch,float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){

        if (!paused) {
            text = "⏸"; //start
        } else text = "⏯"; //Pause
        paused = !paused;
        sketch.println("Not fully implemented 2");
    }
}

class Forward_Button extends Button{
    Forward_Button(PApplet _sketch,float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 3");
    }
}

class Cut_Button extends Button{
    Cut_Button(PApplet _sketch,float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 4");
    }
}

class Circle_Button extends Button{
    Circle_Button(PApplet _sketch,float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 5");
    }
}

class Line_Button extends Button{
    Line_Button(PApplet _sketch,float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch,_x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 6");
    }
}
class Flag_A_Button extends Button{
    Flag_A_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 7");
    }
}
class Flag_B_Button extends Button{
    Flag_B_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 8");
    }
}
class Weight_Button extends Button{
    Weight_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 9");
    }
}

class File_Button extends Button{
    File_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        Main.file_clicked = !Main.file_clicked;
    }
}

/**
 * Unique type knap til knapper under file, som skal gemmes væk når man ikke har klikket på file.
 */

class File_Type_Buttons extends Button{

    File_Type_Buttons(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void drawing(){
        if (Main.file_clicked){
            sketch.fill(162f);
            sketch.rect(x_pos,y_pos, x_size, y_size);

            int changeVal = 5;

            if (mouse_Over()){
                sketch.fill(0f);
                sketch.rect(x_pos+changeVal,y_pos+changeVal, x_size-changeVal*2, y_size-changeVal*2);
                sketch.fill(255f); //Text color
                sketch.textSize((x_size-changeVal)/5);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos+changeVal)+(x_size-changeVal*2)/2, (y_pos+changeVal)+(y_size-changeVal*2)/2+10);
            } else {
                sketch.fill(80f);
                sketch.rect(x_pos,y_pos, x_size, y_size);
                sketch.fill(255f);
                sketch.textSize(x_size/5);
                sketch.textAlign(sketch.CENTER);
                sketch.text(text, (x_pos)+(x_size)/2, (y_pos)+(y_size)/2+10);
            }
        }
    }

}

class Export_Button extends File_Type_Buttons{
    Export_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 9");
    }
}

class Import_Button extends File_Type_Buttons{
    Import_Button(PApplet _sketch, float _x_pos, float _y_pos, int _x_size, int _y_size, String _text){
        super(_sketch, _x_pos, _y_pos, _x_size, _y_size, _text);
    }

    void click(){
        sketch.println("Not implemented 9");
    }
}