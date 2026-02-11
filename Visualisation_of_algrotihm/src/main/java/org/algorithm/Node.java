package org.algorithm;

import processing.core.PApplet;

import java.util.ArrayList;

import static org.algorithm.Main.debug;

import static org.algorithm.Main.zoom_level;

public class Node {

    int x, y;
    int dim;
    ArrayList<Edge> connected;
    PApplet sketch;

    public Node(PApplet _sketch, int _x, int _y){
        //float tempX = mouseX/zoom_level;
        //float tempY = mouseY/zoom_level;
        x = Math.round(_x/zoom_level);
        y = Math.round(_y/zoom_level);
        sketch = _sketch;
        connected = new ArrayList<>();
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
    }

    boolean mouse_Over(){
        //float tempX = Math.round(sketch.mouseX/zoom_level);
        //float tempY = Math.round(sketch.mouseY/zoom_level);
        float offset = dim/2;
        if (sketch.mouseX >= (x-offset)*zoom_level && sketch.mouseX <= (x+dim-offset)*zoom_level &&
                sketch.mouseY >= (y-offset)*zoom_level && sketch.mouseY <= (y+dim-offset)*zoom_level){
            return true;
        } else {
            return false;
        }
    }

}
