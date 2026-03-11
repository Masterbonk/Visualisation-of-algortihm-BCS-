package org.algorithm.graph;

import org.algorithm.graph.edges.*;
import org.algorithm.ui.Color_Scheme;
import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.*;

public class Node {

    private final int x;
    private final int y;
    private int dim, g, rhs;
    private ArrayList<Edge> connected;
    PApplet sketch;
    private String name;
    int node_color;
    boolean in_PQ;

    public Node(PApplet _sketch, int _x, int _y){
        //float tempX = mouseX/zoom_level;
        //float tempY = mouseY/zoom_level;
        g = MAX_INT;
        rhs = MAX_INT;


        x = Math.round((_x-translate_x)/zoom_level);
        y = Math.round((_y-translate_y)/zoom_level);
        sketch = _sketch;
        connected = new ArrayList<>();

        set_of_nodes.add(this);
        node_array.add(this);
    }

    public Node(PApplet _sketch, int _x, int _y, String _name){
        //float tempX = mouseX/zoom_level;
        //float tempY = mouseY/zoom_level;
        name = _name;

        g = MAX_INT;
        rhs = MAX_INT;

        x = Math.round((_x-translate_x)/zoom_level);
        y = Math.round((_y-translate_y)/zoom_level);
        sketch = _sketch;
        connected = new ArrayList<>();

        set_of_nodes.add(this);
        node_array.add(this);
    }

    public int get_Rhs_Val(){
        return  rhs;
    }

    public int get_G_Val(){
        return g;
    }

    public void update_Rhs_Val(int _rhs){
        if (_rhs < 0) {
            rhs = MAX_INT;
        } else {
            rhs = _rhs;
        }
    }

    public void update_G_Val(int _g) {
        if (_g < 0) {
            g = MAX_INT;
        } else {
            g = _g;
        }
    }

    public void render(){
        if (mouse_Over() && debug || mouse_Over() && Ui.get_Button("line").clicked || node_1 == this ) {
            color(Color_Scheme.hover_node);
        } else if (mouse_Over() && Ui.get_Button("cut").clicked) {
            color(Color_Scheme.cut_node);
        }  else if (in_PQ){
            color(Color_Scheme.in_PQ_node);
        } else {
            color(Color_Scheme.node);
        }
        sketch.push();
        dim = 50;
        sketch.fill(node_color);
        sketch.circle(x,y,dim);
        sketch.pop();

        if (algorithm.get_Start() == this){ //Makes flag if startnode
            sketch.push();
            sketch.stroke(255);
            sketch.line(x,y,x,y-20);
            sketch.noStroke();
            sketch.fill(255);
            sketch.rect(x,y-20,10,10);
            sketch.pop();
        } else if (algorithm.get_Goal() == this) {
                sketch.push();
                sketch.stroke(0);
                sketch.line(x,y,x,y-20);
                sketch.noStroke();
                sketch.fill(0);
                sketch.rect(x,y-20,10,10);
                sketch.pop();
        }


        if (debug){
            sketch.push();
            sketch.fill(50,10);
            sketch.stroke(0, 0, 255);
            sketch.square(x-dim/2f,y-dim/2f,dim);
            sketch.pop();
        }
        if (Ui.get_Button("Node_display").clicked){
            sketch.push();
            sketch.fill(247,247,247);
            sketch.textSize(20);
            sketch.textAlign(LEFT,TOP);
            String tmp = "g(" + display_Infinity(g) + "), rhs(" + display_Infinity(rhs) + ")";
            sketch.text(tmp,x-sketch.textWidth(tmp)/2,(y+dim/2f)+sketch.getGraphics().textSize);
            sketch.pop();
        }
        if (Ui.get_Button("Name_display").clicked){
            sketch.push();
            sketch.fill(247,247,247);
            sketch.textSize(20);
            sketch.textAlign(LEFT,CENTER);
            sketch.text(name,x-sketch.textWidth(name)/2,y);
            sketch.pop();
        }


    }

    public String display_Infinity(int _i){
        if (_i == MAX_INT){
            return "∞";
        } else {
            return _i + "";
        }
    }

    public boolean mouse_Over(){
        //float tempX = Math.round(sketch.mouseX/zoom_level);
        //float tempY = Math.round(sketch.mouseY/zoom_level);

        float offset = dim/2f;
        if (sketch.mouseX >= (x-offset)*zoom_level+translate_x && sketch.mouseX <= (x+dim-offset)*zoom_level+translate_x &&
                sketch.mouseY >= (y-offset)*zoom_level+translate_y && sketch.mouseY <= (y+dim-offset)*zoom_level+translate_y){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (name != null) return name;
        return x+ " " +y;
    }

    public void color(int _color){
        node_color = _color;
    }

    public String getName(){
        return name;
    }

    public void change_In_PQ(boolean _valid){
        if(_valid){
            in_PQ = true;
        } else {
            in_PQ = false;
        }
    }

    public void strait_Edge_Connecting_Node(){
        if (connected.size() == 2) {
            node_array.remove(this);
            set_of_nodes.remove(this);
            if (algorithm.get_U() != null) algorithm.get_U().remove(this);

            Node other_1 = help_Get_Opposite(connected.getFirst());
            Node other_2 = help_Get_Opposite(connected.getLast());

            other_1.connected.remove(connected.getFirst());
            other_2.connected.remove(connected.getLast());


            new BiEdge(sketch, other_1, other_2, connected.getFirst().get_Weight() + connected.getLast().get_Weight());

            edge_array.remove(connected.getFirst());
            edge_array.remove(connected.getLast());

            println("Straigthened the node edges");

        } else if(connected.isEmpty()) {
            node_array.remove(this);
            set_of_nodes.remove(this);
            if (algorithm.get_U() != null) algorithm.get_U().remove(this);
            println("Lone node removed");

        }else {
            println("Only works if it has specifically 2 edges connected to it.");
        }
    }

    private Node help_Get_Opposite(Edge _e){
        if (_e.get_To() == this) {
            return _e.get_From();
        } else return _e.get_To();
    }

    public int get_X(){
        return x;
    }

    public int get_Y(){
        return y;
    }

    public ArrayList<Edge> get_Connected(){
        return connected;
    }

    public String get_Name(){return name;}

}
