package org.algorithm;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends PApplet{

    public static void main(String[] args){
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }

    float x = 10;

    int button_height = 50;

    Button[] button_array;

    public static boolean file_clicked = false;

    public void setup(){
        //frameRate(30); //Decides how many times per second the draw function is called
        PFont font;
        // The font must be located in the sketch's
        // "data" directory to load successfully

        font = createFont("Arial-Black-48", 128);
        textFont(font);
        button_height = displayHeight*10/144;
        Make_UI();

    }

    public void settings(){ //Setup
        fullScreen(); //Is the size of the canvas

        //frameRate(30); //Decides how many times per second the draw function is called
        button_array = new Button[12];
    }

    public void draw(){
        background(204); //Draws over everything on screen clearing it for the next frame

        for(int i = 0; i < button_array.length; i++){
            button_array[i].drawing();
        }
    }

    public void mousePressed(){

        for(int i = 0; i < button_array.length; i++){
            if(button_array[i].mouse_Over()){
                button_array[i].click();
            }
        }
        if (!button_array[9].mouse_Over() && !button_array[10].mouse_Over() && !button_array[11].mouse_Over() && file_clicked){ //Lukker file menuen hvis man klikker uden for den mens den er åben.
            file_clicked = false;
        }
    }

    void Make_UI(){

        //Make bottom part of UI
        Button back = new Back_Button(this,0, displayHeight-button_height, displayWidth/9, button_height,"⏴"); //Step back
        button_array[0] = back;
        Button pause = new Pause_Button(this, (displayWidth)/9f, displayHeight-button_height, displayWidth/9, button_height,"⏯"); //pause
        button_array[1] = pause;
        Button forward = new Forward_Button(this, displayWidth/9f*2f, displayHeight-button_height, displayWidth/9, button_height,"⏵"); //Step forward
        button_array[2] = forward;
        Button cut = new Cut_Button(this, displayWidth/9f*3f, displayHeight-button_height, displayWidth/9, button_height,"✂"); //Cut
        button_array[3] = cut;
        Button circle = new Circle_Button(this, displayWidth/9f*4f, displayHeight-button_height, displayWidth/9, button_height,"⏺"); //Create circle
        button_array[4] = circle;
        Button line = new Line_Button(this, displayWidth/9f*5f, displayHeight-button_height, displayWidth/9, button_height,"\\"); //Create line
        button_array[5] = line;
        Button flag_a = new Flag_A_Button(this, displayWidth/9f*6f, displayHeight-button_height, displayWidth/9, button_height,"⚐"); //Set flag A
        button_array[6] = flag_a;
        Button flag_b = new Flag_B_Button(this, displayWidth/9f*7f, displayHeight-button_height, displayWidth/9, button_height,"⚑"); //Set flag B
        button_array[7] = flag_b;
        Button weight = new Weight_Button(this, displayWidth/9f*8f, displayHeight-button_height, displayWidth/9, button_height,"Weight"); //Weight
        button_array[8] = weight;

        //Make top left UI
        Button file = new File_Button(this, 0, 0, displayWidth/9, button_height,"File"); //File
        button_array[9] = file;
        Button export = new Export_Button(this, 0, button_height+button_height/10, displayWidth/10 ,button_height-button_height/10,"Export"); //Export
        button_array[10] = export;
        Button b_import = new Import_Button(this, 0, button_height*2+button_height/10, displayWidth/10, button_height-button_height/10,"Import"); //Import
        button_array[11] = b_import;
    }
}
