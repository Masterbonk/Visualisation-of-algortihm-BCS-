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
    public static boolean debug = true;
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


    public static UI Ui;

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
        Ui = new UI(this);

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

        Ui.render();
    }

    public void rescale(){

        if (dWidth != width || dHeight != height){
            dWidth = width;
            dHeight = height;
            //button_height = dHeight*10/144;


            for(int i = 0; i < Ui.bottom_ui.size(); i++){
                Ui.button_map.get(Ui.bottom_ui.get(i)).resize(i*(width/9f),dHeight-button_height,dWidth/9f, button_height);
            }

            for(int i = 0; i < Ui.top_ui.size(); i++){
                int a = 0;
                if (i != 0) {
                    a = 1;
                } else {
                    a = 0;
                }
                Ui.button_map.get(Ui.top_ui.get(i)).resize(0,i*button_height,dWidth/9f - (a * (dWidth/9f)/10f ), button_height);
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
    /**
     * Toggles the value in mode.txt from windowed to fullscreen and vice versa
     * Also relaunches the sketch, a closes the old sketch
     */
    void toggleAndRestart() {
        // Write new mode
        String newMode = fullscreen ? "windowed" : "fullscreen";
        saveStrings("mode.txt", new String[]{ newMode });

        // Relaunch the sketch
        relaunchSketch();

        // Exit current instance
        exit();
    }


    /**
     * Relaunches the current program by starting a new Java process running the same class
     */
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

        for(String s: Ui.button_map.keySet()){
            if(Ui.button_map.get(s).mouse_Over()){
                clicked_on_button = true;
                Ui.button_map.get(s).click();
            }
        }


        if (!clicked_on_button) {
            if (Ui.button_map.get("circle").clicked) {
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
                if (n.mouse_Over() && Ui.button_map.get("cut").clicked) {
                    node_array.remove(n);
                    println("Clicked on node at point " + n.x + ", " + n.y);
                    break;
                }

                if (n.mouse_Over() && Ui.button_map.get("line").clicked) {
                    clicked_on_node = true;
                    //If we click on node with line buttom down we need to make an edge or connect it to
                    if (node_1 == null) {
                        node_1 = n;
                    } else {
                        // second node
                        Edge new_edge = new BiEdge(this, node_1, n, 1);
                        node_1.connected.add(new_edge);
                        n.connected.add(new_edge);

                        edge_array.add(new_edge);
                        node_1 = null;
                    }
                    //add line between clicked nodes

                }
            }

        if (Ui.button_map.get("line").clicked && !clicked_on_node) {
            Node tmp = new Node(this, mouseX, mouseY);
            node_array.add(tmp);
            if (node_1 == null) {
                node_1 = tmp;
            } else {
                Edge tmp_edge = new BiEdge(this, node_1, tmp, 1);
                edge_array.add(tmp_edge);
                node_1 = null;
            }
        }

        for(String s: Ui.button_map.keySet()){
            if(Ui.button_map.get(s).mouse_Over()){
                Ui.button_map.get(s).click();
            }
        }
        if (!Ui.button_map.get("file").mouse_Over() && !Ui.button_map.get("export").mouse_Over() && !Ui.button_map.get("import").mouse_Over() && Ui.button_map.get("file").clicked){ //Lukker file menuen hvis man klikker uden for den mens den er åben.
            Ui.button_map.get("file").clicked = false;
        }



        }
    }

    /**
     * When the mouse is clicked, ie. the very moment the mouse is lifted from being pressed.
     */

    public void mouseClicked(){

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

        //bottom ui
        Ui.add_Button("back", 0, displayHeight-button_height, displayWidth/9f, button_height,"⏴", Back_Button.class, true);

        Ui.add_Button("pause", (displayWidth)/9f, displayHeight-button_height, displayWidth/9f, button_height,"⏯", Pause_Button.class, true);

        Ui.add_Button("forward", displayWidth/9f*2f, displayHeight-button_height, displayWidth/9f, button_height,"⏵", Forward_Button.class, true);

        Ui.add_Button("cut", displayWidth/9f*3f, displayHeight-button_height, displayWidth/9f, button_height,"✂", Cut_Button.class, true);

        Ui.add_Button("circle", displayWidth/9f*4f, displayHeight-button_height, displayWidth/9f, button_height,"⏺", Circle_Button.class, true);

        Ui.add_Button("line", displayWidth/9f*5f, displayHeight-button_height, displayWidth/9f, button_height,"\\", Line_Button.class, true);

        Ui.add_Button("flag_a", displayWidth/9f*6f, displayHeight-button_height, displayWidth/9f, button_height,"⚐", Flag_A_Button.class, true);

        Ui.add_Button("flag_b", displayWidth/9f*7f, displayHeight-button_height, displayWidth/9f, button_height,"⚑", Flag_B_Button.class, true);

        Ui.add_Button("weight", displayWidth/9f*8f, displayHeight-button_height, displayWidth/9f, button_height,"Weight", Weight_Button.class, true);

        //top ui
        Ui.add_Button("file", 0, 0, displayWidth/9f, button_height,"File", File_Button.class, false);

        Ui.add_Button("export", 0, button_height+button_height/10f, displayWidth/10f ,button_height-button_height/10f,"Export", Export_Button.class, false);

        Ui.add_Button("import", 0, button_height*2+button_height/10f, displayWidth/10f, button_height-button_height/10f,"Import", Import_Button.class, false);

    }

}
