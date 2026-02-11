package org.algorithm;

import processing.core.PApplet;

import java.util.ArrayList;

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
        sketch.fill(204, 24, 24);
        dim = 50;
        sketch.circle(x,y,dim);

        sketch.pop();
    }

    boolean mouse_Over(){
        //float tempX = Math.round(sketch.mouseX/zoom_level);
        //float tempY = Math.round(sketch.mouseY/zoom_level);
        float offset = dim/2;
        if (sketch.mouseY >= x-offset && sketch.mouseX <= x+dim-offset &&
                sketch.mouseX >= y-offset && sketch.mouseY <= y+dim-offset){
            return true;
        } else {
            return false;
        }
    }

}
