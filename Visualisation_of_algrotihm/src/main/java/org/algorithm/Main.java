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

    int dWidth, dHeight;

    Button[] bottom_button_array;
    Button[] top_button_array;

    public static boolean file_clicked = false;



    public void setup(){

        surface.setResizable(true);
        surface.setLocation(0,0);
        //frameRate(30); //Decides how many times per second the draw function is called
        PFont font;
        // The font must be located in the sketch's
        // "data" directory to load successfully

        font = createFont("Arial-Black-48", 128);
        textFont(font);
        dWidth = width;
        dHeight = height;
        button_height = dHeight*10/144;
        Make_UI();



    }

    public void settings(){ //Setup

        size(displayWidth/2, displayHeight/2);
        dWidth = width;
        dHeight = height;
        //fullScreen(); //Is the size of the canvas

        //frameRate(30); //Decides how many times per second the draw function is called
        bottom_button_array = new Button[9];
        top_button_array = new Button[3];
    }

    public void draw(){
        background(204); //Draws over everything on screen clearing it for the next frame

        rescale();

        for (Button button : bottom_button_array) {
            button.drawing();
        }
        for (Button button : top_button_array) {
            button.drawing();
        }
    }

    public void rescale(){

        if (dWidth != width || dHeight != height){
            dWidth = width;
            dHeight = height;
            //button_height = dHeight*10/144;


            for(int i = 0; i < bottom_button_array.length; i++){
                bottom_button_array[i].resize(i*(width/9f),dHeight-button_height,dWidth/9f, button_height);
            }
            
            for(int i = 0; i < top_button_array.length; i++){
                int a = 0;
                if (i != 0) {
                    a = 1;
                } else {
                    a = 0;
                }
                top_button_array[i].resize(0,i*button_height,dWidth/9f - (a * (dWidth/9f)/10f ), button_height);
            }

        }

    }

    public void keyPressed(){

        if (key == CODED){
            if (keyCode == 122) {
                fullScreen();
            }
        }
    }

    public void mousePressed(){

        for (Button value : bottom_button_array) {
            if (value.mouse_Over()) {
                value.click();
            }
        }

        for (Button button : top_button_array) {
            if (button.mouse_Over()) {
                button.click();
            }
        }
        if (!top_button_array[0].mouse_Over() && !top_button_array[1].mouse_Over() && !top_button_array[2].mouse_Over() && file_clicked){ //Lukker file menuen hvis man klikker uden for den mens den er åben.
            file_clicked = false;
        }
    }

    void Make_UI(){

        //Make bottom part of UI
        Button back = new Back_Button(this,0, height-button_height, dWidth/9, button_height,"⏴"); //Step back
        bottom_button_array[0] = back;
        Button pause = new Pause_Button(this, (dWidth)/9f, height-button_height, dWidth/9, button_height,"⏯"); //pause
        bottom_button_array[1] = pause;
        Button forward = new Forward_Button(this, dWidth/9f*2f, height-button_height, dWidth/9, button_height,"⏵"); //Step forward
        bottom_button_array[2] = forward;
        Button cut = new Cut_Button(this, dWidth/9f*3f, height-button_height, dWidth/9, button_height,"✂"); //Cut
        bottom_button_array[3] = cut;
        Button circle = new Circle_Button(this, dWidth/9f*4f, height-button_height, dWidth/9, button_height,"⏺"); //Create circle
        bottom_button_array[4] = circle;
        Button line = new Line_Button(this, dWidth/9f*5f, height-button_height, dWidth/9, button_height,"\\"); //Create line
        bottom_button_array[5] = line;
        Button flag_a = new Flag_A_Button(this, dWidth/9f*6f, height-button_height, dWidth/9, button_height,"⚐"); //Set flag A
        bottom_button_array[6] = flag_a;
        Button flag_b = new Flag_B_Button(this, dWidth/9f*7f, height-button_height, dWidth/9, button_height,"⚑"); //Set flag B
        bottom_button_array[7] = flag_b;
        Button weight = new Weight_Button(this, dWidth/9f*8f, height-button_height, dWidth/9, button_height,"Weight"); //Weight
        bottom_button_array[8] = weight;

        //Make top left UI
        Button file = new File_Button(this, 0, 0, dWidth/9, button_height,"File"); //File
        top_button_array[0] = file;
        Button export = new Export_Button(this, 0, button_height+button_height/10, dWidth/10 ,button_height-button_height/10,"Export"); //Export
        top_button_array[1] = export;
        Button b_import = new Import_Button(this, 0, button_height*2+button_height/10, dWidth/10, button_height-button_height/10,"Import"); //Import
        top_button_array[2] = b_import;


    }
}
