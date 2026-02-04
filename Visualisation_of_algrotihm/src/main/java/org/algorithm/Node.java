package org.algorithm;

import processing.core.PApplet;

public class Node {

    int x, y;
    int dim;
    Edge[] connected;
    PApplet sketch;

    public Node(PApplet _sketch, int _x, int _y){
        x = _x;
        y = _y;
        sketch = _sketch;
    }

    public void render(){
        sketch.push();
        sketch.fill(204, 24, 24);
        dim = 50;
        sketch.circle(x,y,dim);

        sketch.pop();
    }

    boolean mouse_Over(){
        float offset = dim/2;
        if (sketch.mouseX >= x-offset && sketch.mouseX <= x+dim-offset &&
                sketch.mouseY >= y-offset && sketch.mouseY <= y+dim-offset){
            return true;
        } else {
            return false;
        }
    }

}
