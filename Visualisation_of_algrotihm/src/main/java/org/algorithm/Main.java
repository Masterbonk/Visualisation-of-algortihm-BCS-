package org.algorithm;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static HashMap<String, Button> button_map;

    ArrayList<Edge> edge_array = new ArrayList<>();
    ArrayList<Node> node_array = new ArrayList<>();

    public static boolean add_node_active = false;

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
        Make_Graph();

    }

    public void settings(){ //Setup
        fullScreen(); //Is the size of the canvas

        //frameRate(30); //Decides how many times per second the draw function is called
        button_map = new HashMap<>();
    }

    public void draw(){
        background(204); //Draws over everything on screen clearing it for the next frame

        push();
        strokeWeight(10);
        textSize(30);
        for(int i = 0; i < edge_array.size(); i++){
            edge_array.get(i).render();
        }
        pop();

        for(int i = 0; i < node_array.size(); i++){
            node_array.get(i).render();
        }

        for(String s: button_map.keySet()){
            button_map.get(s).render();
        }
    }

    public void mousePressed(){
        for(Node n: node_array){
            if(n.mouse_Over()){
                node_array.remove(n);
                println("Clicked on node at point "+n.x+", "+n.y);
                break;
            }
        }

        for(String s: button_map.keySet()){
            if(button_map.get(s).mouse_Over()){
                button_map.get(s).click();
            }
        }
        if (!button_map.get("file").mouse_Over() && !button_map.get("export").mouse_Over() && !button_map.get("import").mouse_Over() && button_map.get("file").clicked){ //Lukker file menuen hvis man klikker uden for den mens den er åben.
            button_map.get("file").clicked = false;
        }
    }

    public void mouseClicked(){
        boolean hovering_over_buttons = false;
        for(String s: button_map.keySet()){
            if(button_map.get(s).mouse_Over()){
                hovering_over_buttons = true;
            }
        }
        if (add_node_active && !hovering_over_buttons) {
            Node x = new Node(this, mouseX, mouseY);
            node_array.add(x);
        }
    }

    void Make_Graph(){

        Node x, y;
        BiEdge e;
        x = new Node(this, 400, 200);
        y = new Node(this, 200, 400);
        e = new BiEdge(this, x, y, 5);


        node_array.add(x);
        node_array.add(y);
        edge_array.add(e);

        x = new Node(this, 600, 625);

        e = new BiEdge(this, x, y, 15);
        edge_array.add(e);

        y = new Node(this, 200, 625);
        e = new BiEdge(this, x, y, 5);

        node_array.add(x);
        node_array.add(y);
        edge_array.add(e);
    }

    void Make_UI(){

        //Make bottom part of UI
        Button back = new Back_Button(this,0, displayHeight-button_height, displayWidth/9, button_height,"⏴"); //Step back
        button_map.put("back",back);
        Button pause = new Pause_Button(this, (displayWidth)/9f, displayHeight-button_height, displayWidth/9, button_height,"⏯"); //pause
        button_map.put("pause",pause);
        Button forward = new Forward_Button(this, displayWidth/9f*2f, displayHeight-button_height, displayWidth/9, button_height,"⏵"); //Step forward
        button_map.put("forward",forward);
        Button cut = new Cut_Button(this, displayWidth/9f*3f, displayHeight-button_height, displayWidth/9, button_height,"✂"); //Cut
        button_map.put("cut",cut);
        Button circle = new Circle_Button(this, displayWidth/9f*4f, displayHeight-button_height, displayWidth/9, button_height,"⏺"); //Create circle
        button_map.put("circle",circle);
        Button line = new Line_Button(this, displayWidth/9f*5f, displayHeight-button_height, displayWidth/9, button_height,"\\"); //Create line
        button_map.put("line",line);
        Button flag_a = new Flag_A_Button(this, displayWidth/9f*6f, displayHeight-button_height, displayWidth/9, button_height,"⚐"); //Set flag A
        button_map.put("flag_a",flag_a);
        Button flag_b = new Flag_B_Button(this, displayWidth/9f*7f, displayHeight-button_height, displayWidth/9, button_height,"⚑"); //Set flag B
        button_map.put("flag_b",flag_b);
        Button weight = new Weight_Button(this, displayWidth/9f*8f, displayHeight-button_height, displayWidth/9, button_height,"Weight"); //Weight
        button_map.put("weight",weight);

        //Make top left UI
        Button file = new File_Button(this, 0, 0, displayWidth/9, button_height,"File"); //File
        button_map.put("file",file);
        Button export = new Export_Button(this, 0, button_height+button_height/10, displayWidth/10 ,button_height-button_height/10,"Export"); //Export
        button_map.put("export",export);
        Button b_import = new Import_Button(this, 0, button_height*2+button_height/10, displayWidth/10, button_height-button_height/10,"Import"); //Import
        button_map.put("import",b_import);
    }
}
