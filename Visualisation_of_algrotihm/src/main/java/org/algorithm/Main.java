package org.algorithm;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;

//used this code to implement zoom feature https://forum.processing.org/one/topic/zooming-in-and-zooming-out.html

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends PApplet{

    public static boolean fullscreen = true;
    public static boolean debug = false;
    /**
     * Main function starts the sketch
     * @param args
     */
    public static void main(String[] args){
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
    }

    public int button_height = 50;
    public static int dWidth, dHeight;
    public static Node node_1;

    //zoom functionality
    public static float zoom_level = 1f;
    final float zoom_increase = 0.1f;

    //The start and end node for the graph
    public static Node start_node;
    public static Node end_node;


    //Pan functionality
    public static int translate_x = 0;
    public static int translate_y = 0;
    public int mouse_x_start_of_pan = -1;
    public int mouse_y_start_of_pan = -1;


    Util util;


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
        util = new Util(this,button_height);

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

        Util.Make_UI(this, button_height);
        Make_Graph();

    }


    /**
     * Draw is run repeatedly forever.
     * It begins running as soon as setup has finished.
     * It will run 60 times per second if the FPS is 60, but it might be unable to do 60 per second if too much needs to be drawn.
     */

    public void draw(){
        background(204); //Draws over everything on screen clearing it for the next frame

        zoom();

        pushMatrix();
        translate(translate_x,translate_y);
        //line formatting
        push();
        strokeWeight(10);
        textSize(30);
        scale(zoom_level);
        for(int i = 0; i < edge_array.size(); i++){
            edge_array.get(i).render();
            //edge_array.get(i).color(); //for animating
        }
        pop();


        push();
        scale(zoom_level);
        for(int i = 0; i < node_array.size(); i++){
            node_array.get(i).render();
        }
        pop();
        popMatrix();


        Ui.render();
        rescale();
    }

    public void rescale(){

        if (dWidth != width || dHeight != height){
            dWidth = width;
            dHeight = height;
            //button_height = dHeight*10/144;


            for(int i = 0; i < Ui.bottom_ui.size(); i++){
                Ui.get_Map().get(Ui.bottom_ui.get(i)).resize(i*(width/9f),dHeight-button_height,dWidth/9f, button_height);
            }

            for(int i = 0; i < Ui.top_ui.size(); i++){
                int a;
                if (i != 0) {
                    a = 1;
                } else {
                    a = 0;
                }
                Ui.get_Map().get(Ui.top_ui.get(i)).resize(0,i*button_height,dWidth/9f - (a * (dWidth/9f)/10f ), button_height);
            }

        }

    }

    /** This function ensures that the zoom level updates
     * and that there is min & max zoom
     * */
    public void zoom(){
        if(keyPressed) {
            if (key == '+' && zoom_level < 3) {
                zoom_level += zoom_increase;
            } else if (key == '-' && zoom_level > 0) {
                zoom_level -= zoom_increase;
            }
        }
    }

    /** Zoom functionality works when scrolling the mouse wheel (on an actual mouse)
     * */
    public void mouseWheel(processing.event.MouseEvent _mouse_event){
        float wheel_number = _mouse_event.getCount();

        if(zoom_level < 3 && wheel_number < 0) {
                zoom_level += zoom_increase;
        }
        if(zoom_level > 0 && wheel_number > 0) {
            zoom_level -= zoom_increase;
        }
    }


    /**
     * Runs everytime a key is pressed.
     * Can be used with keycode to check if specific keys are clicked.
     */
    public void keyPressed(){

        if (key == CODED){
            if (keyCode == 122) {
                print("key pressed f11");
                toggleAndRestart();
            }
        }

        if (key == 'p'){
            //print("key pressed p");
            debug = !debug;
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

        if(mouseButton == LEFT) {
            for (String s : Ui.get_Map().keySet()) {
                if (Ui.get_Button(s).mouse_Over()) {
                    clicked_on_button = true;
                    Ui.get_Button(s).click();
                }
            }


            if (!clicked_on_button) {
                if (Ui.get_Button("circle").clicked) {
                    boolean over_any_nodes = false;
                    for (Node t : node_array) {
                        if (t.mouse_Over()) {
                            over_any_nodes = true;
                        }
                    }
                    if (!over_any_nodes) {
                        Node x = new Node(this, mouseX, mouseY);
                        node_array.add(x);
                    }
                }

                for (Node n : node_array) {
                    if (Ui.get_Button("cut").clicked && n.mouse_Over()) {
                        clicked_on_node = true;
                        node_array.remove(n);
                        if (start_node == n) start_node = null;
                        if (end_node == n) end_node = null;
                        for (Edge e : n.connected) {
                            Node tmp;
                            if (e.from == n) {
                                tmp = e.to;
                            } else tmp = e.from;
                            tmp.connected.remove(e);
                            edge_array.remove(e);
                        }
                        println("Clicked on node at point " + n.x + ", " + n.y);
                        break;
                    } else if (Ui.get_Button("line").clicked && n.mouse_Over()) {
                        clicked_on_node = true;
                        //If we click on node with line buttom down we need to make an edge or connect it to
                        if (node_1 == null) {
                            node_1 = n;
                        } else if (n != node_1) {
                            boolean stop = false;
                            for (Edge e : node_1.connected) {
                                if (e.to == n) {
                                    stop = true;
                                    break;
                                }
                            }
                            if (!stop) {
                                // second node
                                Edge new_edge = new BiEdge(this, node_1, n, 1);

                                edge_array.add(new_edge);
                                node_1 = null;
                            }
                        }
                        //add line between clicked nodes

                        break;

                    } else if(Ui.get_Button("flag_a").clicked && end_node != n && n.mouse_Over()){
                        clicked_on_node = true;
                        start_node = n;
                    } else if(Ui.get_Button("flag_b").clicked && start_node != n && n.mouse_Over()){
                        clicked_on_node = true;
                        end_node = n;
                    }
                }

                if (!clicked_on_node && Ui.get_Button("flag_a").clicked) start_node = null;
                if (!clicked_on_node && Ui.get_Button("flag_b").clicked) end_node = null;


                //When we have line, and click outside a node
                if (Ui.get_Button("line").clicked && !clicked_on_node) {
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

                if (Ui.get_Button("cut").clicked && !clicked_on_node) {
                    for (Edge e : edge_array) {
                        if (e.mouseOver()) {
                            println("Edge was deleted");
                            edge_array.remove(e);
                            break;
                        }
                    }
                }
            }

            if (!Ui.get_Button("file").mouse_Over() && !Ui.get_Button("export").mouse_Over() && !Ui.get_Button("import").mouse_Over() && Ui.get_Button("file").clicked) { //Lukker file menuen hvis man klikker uden for den mens den er åben.
                Ui.get_Button("file").clicked = false;
            }
        } else if (mouseButton == RIGHT){

        }
    }

    public void mouseDragged(){
        if(mouseButton == RIGHT) {
            boolean is_over_ui = false;
            for (String s : Ui.get_Map().keySet()) {
                if (Ui.get_Button(s).mouse_Over()) {
                    is_over_ui = true;
                    break;
                }
            }
            if (!is_over_ui) {
                if (mouse_x_start_of_pan == -1 || mouse_y_start_of_pan == -1) {
                    mouse_x_start_of_pan = mouseX;
                    mouse_y_start_of_pan = mouseY;
                } else {


                    translate_x += mouseX - mouse_x_start_of_pan;
                    translate_y += mouseY - mouse_y_start_of_pan;
                    //println("Translate_x = " + translate_x + ". mouseX = " + mouseX + ". mouse_x_start_of_pan = " + mouse_x_start_of_pan);
                    //println("Translate_y = " + translate_y + ". mouseY = " + mouseY + ". mouse_y_start_of_pan = " + mouse_y_start_of_pan);

                    mouse_x_start_of_pan = mouseX;
                    mouse_y_start_of_pan = mouseY;
                }
            }
        }
    }

    /**
     * When the mouse is clicked, ie. the very moment the mouse is lifted from being pressed.
     */

    public void mouseReleased(){
        if (mouseButton == RIGHT){
            mouse_x_start_of_pan = -1;
            mouse_y_start_of_pan = -1;
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



}
