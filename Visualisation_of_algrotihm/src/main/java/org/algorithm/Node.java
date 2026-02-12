package org.algorithm;

import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.*;

public class Node {

    int x, y;
    int dim, g, rhs;
    ArrayList<Edge> connected;
    PApplet sketch;

    public Node(PApplet _sketch, int _x, int _y){
        //float tempX = mouseX/zoom_level;
        //float tempY = mouseY/zoom_level;
        x = Math.round((_x-translate_x)/zoom_level);
        y = Math.round((_y-translate_y)/zoom_level);
        sketch = _sketch;
        connected = new ArrayList<>();
    }

    public int get_Rhs_Val(Node n){
        return  n.rhs;
    }

    public int get_G_Val(Node n){
        return n.g;
    }

    public void update_Rhs_Val(Node n, int _rhs){
        n.rhs = _rhs;
    }

    public void update_G_Val(Node n, int _g) {
        n.g = _g;
    }

    public void render(){
        sketch.push();
        if (mouse_Over()) {
            sketch.fill(24,204,24);
        } else sketch.fill(204, 24, 24);
        dim = 50;
        sketch.circle(x,y,dim);

        sketch.pop();

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
            sketch.text("g(" + g + "), rhs(" + rhs + ")",(x+dim/2)-35,(y+dim/2)+20);
            sketch.pop();
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

}
