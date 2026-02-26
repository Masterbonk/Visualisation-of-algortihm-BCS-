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
    int r = 232, g1 = 25, b = 25;
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
            color(24,204,24);
        } else if (mouse_Over() && Ui.get_Button("cut").clicked) {
            color(160, 4, 4);
        } else if(in_PQ){
            color(238,218,18);
        } else {
            color(232,25,25);
        }
        sketch.push();
        dim = 50;
        sketch.fill(r,g1,b);
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

    boolean mouse_Over(){
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

    public void color(int _r, int _g, int _b){
        r = _r;
        g1 = _g;
        b = _b;
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

}
