package org.algorithm;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends PApplet{

    public static boolean fullscreen = true;

    /**
     * Main function starts the sketch
     * @param args
     */
    public static void main(String[] args){
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }

    int button_height = 50;
    int dWidth, dHeight;
    public static Node node_1;


    public static HashMap<String, Button> button_map;
    public static String[] bottom_ui = {"back", "pause", "forward", "cut", "circle", "line", "flag_a", "flag_b", "weight"};
    String[] top_ui = {"file", "export", "import"};


    ArrayList<Edge> edge_array = new ArrayList<>();
    ArrayList<Node> node_array = new ArrayList<>();

    /**
     * The settings are the first thing that runs, it is done before the
     * sketch truly begin and some functions is therefor unable to be run in it.
     */
    public void settings(){ //Setup

        String[] modeFile = loadStrings("mode.txt");
        fullscreen = modeFile != null && modeFile[0].equals("fullscreen");

        if (fullscreen) {
            fullScreen();
        } else {
            size(900, 600);

        }
        //fullScreen(); //Is the size of the canvas

        //frameRate(30); //Decides how many times per second the draw function is called
        button_map = new HashMap<>();
    }

    /**
     * Setup is run right after settings. It is used to setup the envioment before we truly begin drawing everything.
     * Certain functions can only be called here, like create font.
     */
    public void setup(){
        //frameRate(30); //Decides how many times per second the draw function is called
        PFont font;
        // The font must be located in the sketch's
        // "data" directory to load successfully

        surface.setResizable(true);
        surface.setLocation(0,0);

        font = createFont("Arial-Black-48", 128);
        textFont(font);


        button_height = displayHeight*10/144;
        Make_UI();
        Make_Graph();

    }


    /**
     * Draw is run repeatedly forever.
     * It begins running as soon as setup has finished.
     * It will run 60 times per second if the FPS is 60, but it might be unable to do 60 per second if too much needs to be drawn.
     */

    public void draw(){
        background(204); //Draws over everything on screen clearing it for the next frame

        push();
        strokeWeight(10);
        textSize(30);

        rescale();

        for(int i = 0; i < edge_array.size(); i++){
            edge_array.get(i).render();
            //edge_array.get(i).color(); //for animating
        }
        pop();

        for(int i = 0; i < node_array.size(); i++){
            node_array.get(i).render();
        }

        for(String s: button_map.keySet()){
            button_map.get(s).render();
        }
    }

    public void rescale(){

        if (dWidth != width || dHeight != height){
            dWidth = width;
            dHeight = height;
            //button_height = dHeight*10/144;


            for(int i = 0; i < bottom_ui.length; i++){
                button_map.get(bottom_ui[i]).resize(i*(width/9f),dHeight-button_height,dWidth/9f, button_height);
            }

            for(int i = 0; i < top_ui.length; i++){
                int a;
                if (i != 0) {
                    a = 1;
                } else {
                    a = 0;
                }
                button_map.get(top_ui[i]).resize(0,i*button_height,dWidth/9f - (a * (dWidth/9f)/10f ), button_height);
            }

        }

    }

    /**
     * Runs everytime a key is pressed.
     * Can be used with keycode to check if specific keys are clicked.
     */

    public void keyPressed(){

        if (key == CODED){
            if (keyCode == 122) {
                print("key pressed f11 ");
                toggleAndRestart();
            }
        }
    }

    void toggleAndRestart() {
        // Write new mode
        String newMode = fullscreen ? "windowed" : "fullscreen";
        saveStrings("mode.txt", new String[]{ newMode });

        // Relaunch the sketch
        relaunchSketch();

        // Exit current instance
        exit();
    }

    void relaunchSketch() {
        try {
            String javaBin = System.getProperty("java.home") +
                    java.io.File.separator + "bin" +
                    java.io.File.separator + "java";

            String classPath = System.getProperty("java.class.path");
            String className = this.getClass().getName();

            ProcessBuilder builder = new ProcessBuilder(
                    javaBin,
                    "-cp",
                    classPath,
                    className
            );

            builder.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * If the mouse is pressed. Used to check if the buttons are checked, and other similair effects started by mousepressed.
     */
    public void mousePressed(){
        boolean clicked_on_node = false;
        boolean clicked_on_button = false;

        for(String s: button_map.keySet()){
            if(button_map.get(s).mouse_Over()){
                clicked_on_button = true;
                button_map.get(s).click();
            }
        }
        if (!button_map.get("file").mouse_Over() && !button_map.get("export").mouse_Over() && !button_map.get("import").mouse_Over() && button_map.get("file").clicked){ //Lukker file menuen hvis man klikker uden for den mens den er åben.
            button_map.get("file").clicked = false;
        }

        if (!clicked_on_button) {
            if (button_map.get("circle").clicked) {
                boolean over_any_nodes = false;
                for (Node t: node_array){
                    if(t.mouse_Over()){
                        over_any_nodes = true;
                    }
                }
                if(!over_any_nodes) {
                    Node x = new Node(this, mouseX, mouseY);
                    node_array.add(x);
                }
            }

            for (Node n : node_array) {
                if (n.mouse_Over() && button_map.get("cut").clicked) {
                    clicked_on_node = true;
                    node_array.remove(n);
                    for (Edge e: n.connected){
                        Node tmp;
                        if(e.from == n){
                            tmp = e.to;
                        } else tmp = e.from;
                        tmp.connected.remove(e);
                        edge_array.remove(e);
                    }
                    println("Clicked on node at point " + n.x + ", " + n.y);
                    break;
                }

                if (n.mouse_Over() && button_map.get("line").clicked) {
                    clicked_on_node = true;
                    //If we click on node with line buttom down we need to make an edge or connect it to
                    if (node_1 == null) {
                        node_1 = n;
                    } else {
                        // second node
                        Edge new_edge = new BiEdge(this, node_1, n, 1);

                        edge_array.add(new_edge);
                        node_1 = null;
                    }
                    //add line between clicked nodes

                    break;

                }
            }


            //When we have line, and click outside a node
            if (button_map.get("line").clicked && !clicked_on_node) {
                Node tmp = new Node(this, mouseX, mouseY);
                node_array.add(tmp);
                if (node_1 == null) {
                    node_1 = tmp;
                } else {
                    Edge new_edge = new BiEdge(this, node_1, tmp, 1);
                    edge_array.add(new_edge);

                    node_1 = null;
                }
            }

            if (button_map.get("cut").clicked && !clicked_on_node) {
                for (Edge e: edge_array){
                    if (e.mouseOver()){
                        println("Edge was deleted");
                        edge_array.remove(e);
                        break;
                    }
                }
            }
        }

        for (Edge e: edge_array){
            if (e.mouseOver()){
                println("Edge was clicked.\n Start pos: ("+e.from.x+", "+e.from.y+"). End pos: ("+e.to.x+", "+e.to.y+").");
            }
        }



    }

    /**
     * When the mouse is clicked, ie. the very moment the mouse is lifted from being pressed.
     */

    public void mouseClicked(){
    }

    /**
     *  The function to make sure no buttons can be clicked simultaneously
     * */
    public static void turn_Off_All_Buttons(Button _button){
       for(int i = 0; i < bottom_ui.length; i++){
          if(button_map.get(bottom_ui[i]) != _button){
               button_map.get(bottom_ui[i]).clicked = false;
          }
       }


    }

    /**
     * Makes the base graph objects. All are added to the node and edge arrays so they are rendered.
     */

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

    /**
     * Makes the Ui elements, ie. all buttons All are added to the button arrays so they are rendered.
     */
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
        Button export = new Export_Button(this, 0, button_height+button_height/10f, displayWidth/10 ,button_height-button_height/10,"Export"); //Export
        button_map.put("export",export);
        Button b_import = new Import_Button(this, 0, button_height*2+button_height/10f, displayWidth/10, button_height-button_height/10,"Import"); //Import
        button_map.put("import",b_import);
    }

}
