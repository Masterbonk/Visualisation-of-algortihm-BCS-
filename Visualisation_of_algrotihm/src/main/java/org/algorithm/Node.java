package org.algorithm;

import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.*;

public class Node {

    int x, y;
    private int dim, g, rhs;
    ArrayList<Edge> connected;
    PApplet sketch;
    String name;

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
        sketch.push();
        if (mouse_Over()) {
            sketch.fill(24,204,24);
        } else sketch.fill(204, 24, 24);
        dim = 50;
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
            sketch.square(x-dim/2,y-dim/2,dim);
            sketch.pop();
        }
        if (Ui.get_Button("Node_display").clicked){
            sketch.push();
            sketch.fill(247,247,247);
            sketch.textSize(20);
            sketch.text("g(" + display_Infinity(g) + "), rhs(" + display_Infinity(rhs) + ")",(x+dim/2)-35,(y+dim/2)+20);
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

    boolean mouse_Over(){
        //float tempX = Math.round(sketch.mouseX/zoom_level);
        //float tempY = Math.round(sketch.mouseY/zoom_level);

        float offset = dim/2;
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
}
