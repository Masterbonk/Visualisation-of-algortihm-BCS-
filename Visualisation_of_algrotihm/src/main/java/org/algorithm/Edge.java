package org.algorithm;

import processing.core.PApplet;

import static org.algorithm.Main.*;


public class Edge {
    Node from;
    Node to;
    PApplet sketch;
    int weight;
    float x = 0, y = 0;

    public Edge(PApplet _sketch, Node _from, Node _to, int _weight){
        sketch = _sketch;
        from = _from;
        to = _to;

        update_Weight(_weight);

        from.connected.add(this);
        to.connected.add(this);

    }

    public void update_Weight(int _i){
        float tmp = algorithm.heuristic(to, from);
        if (_i < tmp){
            weight = (int)Math.ceil(tmp);
        } else weight = _i;
    }

    public void render(){

        sketch.push();
        if (Ui.get_Button("cut").clicked){
            if(mouseOver()){
                sketch.stroke(150,0,0);
            }
        }
        sketch.line(from.x,from.y, to.x, to.y);
        sketch.pop();

        if (Ui.get_Button("Edge_display").clicked) {
            sketch.push();
            sketch.fill(100);
            sketch.stroke(100);
            sketch.strokeWeight(10);
            sketch.textAlign(sketch.RIGHT, sketch.BOTTOM);
            sketch.rectMode(sketch.CENTER);
            sketch.rect((from.x + to.x) / 2f - (weight > 9 ? sketch.getGraphics().textSize * 0.5f : sketch.getGraphics().textSize * 0.25f),
                    (from.y + to.y) / 2f - sketch.getGraphics().textSize * 0.5f, (weight > 9 ? sketch.getGraphics().textSize : sketch.getGraphics().textSize / 2), (sketch.getGraphics().textSize));
            sketch.fill(255);
            sketch.text("" + weight, ((from.x + to.x) / 2f), ((from.y + to.y) / 2f));
            sketch.pop();
        }

    }

    public void color(){
        sketch.push();
        sketch.stroke(237, 194, 109);
        sketch.line(from.x,from.y, from.x+x, from.y+y);
        if (x>(to.x-from.x)) {
            x += (to.x - from.x) / 60f;
            y += (to.y - from.y) / 60f;
        }
        sketch.pop();

    }


    /**
     * This mouseOver detects if the mouse is over an edge.
     * It starts by making a box around the mouse.
     * The edge then splits itself up into smaller parts of the line
     * that are all as long as the mouses box size.
     * Then we check if the start of each line part to see if the box is intercepting that specific point
     * If so we know that there is overlap and the mouse is over that edge.
     * It should be noted that we know the mouse will be registered,
     * because it cannot intercept without hitting one of these points.
     *
     * Got the base code from Java.awt.geom.Rectangle2D;
     * @return true if the mouse is hovering over the edge.
     */
    public boolean mouseOver() {
        //The box around the mouse
        int box_size = 10; int box_x = sketch.mouseX-box_size/2; int box_y = sketch.mouseY-box_size/2;

        //The start and end points of the edge
        double x1_tmp = from.x*zoom_level+translate_x; double y1_tmp = from.y*zoom_level+translate_y; double x2_tmp = to.x*zoom_level+translate_x; double y2_tmp = to.y*zoom_level+translate_y;

        //The 2 outcodes that we save. Each one is a binary value that tells us if the point was over,
        // under, to the left or right of the box
        int out1, out2;

        //out2 is given whether we currently intercept the end of the edge, if 0000 we know it hit.
        if ((out2 = outcode(x2_tmp, y2_tmp, box_x, box_y, box_size)) == 0) {
            return true;
        }
        //While loop continues until out1 is told that we hit the box around the mouse with the point
        // on the edge we are currently looking at.
        while ((out1 = outcode(x1_tmp, y1_tmp, box_x, box_y, box_size)) != 0) {

            //If the binary & operator does not result in a 0, we know that the mouse box will never
            //intercept the rest of the unchecked edge. This is because the points in the edge we are
            //currently looking at (out1) tells us where the mouse is in relation to that point, and
            // out2 tells us where it is in relation to it. Now if both say that the point is down
            //to the right of them, then we know the connection between them will never intercept the
            //point.
            if ((out1 & out2) != 0) {
                return false;
            }

            //Moves the x closer to the point, trying to see if we intercept the box.
            if ((out1 & (1/*OUT_LEFT*/ | 4/*OUT_RIGHT*/)) != 0) {
                double x = box_x;
                if ((out1 & 4/*OUT_RIGHT*/) != 0) {
                    x += box_size;
                }
                y1_tmp = y1_tmp + (x - x1_tmp) * (y2_tmp - y1_tmp) / (x2_tmp - x1_tmp);
                x1_tmp = x;

                //Moves the y closer to the point, trying to see if we intercept the box.
            } else {
                double y = box_y;
                if ((out1 & 8/*OUT_BOTTOM*/) != 0) {
                    y += box_size;
                }
                x1_tmp = x1_tmp + (y - y1_tmp) * (x2_tmp - x1_tmp) / (y2_tmp - y1_tmp);
                y1_tmp = y;
            }
        }
        return true;
    }


    /**
     * This function takes a point and a box, and then tells us where the point is in relation to the box.
     * Got the base code from Java.awt.geom.Rectangle2D;
     * @param x the points x value
     * @param y the points y value
     * @param box_x the boxes x value
     * @param box_y the boxes y value
     * @param box_size the boxes size
     * @return a binary operator that tells us where the point is in relation to the box. If it's 0000 we know the point is inside the box. If it's 1111 we know the box has no size.
     */
    public int outcode(double x, double y, int box_x, int box_y, int box_size) {
        int out = 0;
        //Marks 1 and 4 in binary (0101) if the box does not have a size.
        if (box_size <= 0) {
            out |= 1/*OUT_LEFT*/ | 4/*OUT_RIGHT*/;

            //Makes the binary (0001) if the point is to the left of the box
        } else if (x < box_x) {
            out |= 1/*OUT_LEFT*/;

            //Makes the binary (0100) if the point is to the right of the box
        } else if (x > box_x + box_size) {
            out |= 4/*OUT_RIGHT*/;
        }

        //Marks 2 and 8 in binary (1010) if the box does not have a size.
        if (box_size <= 0) {
            out |= 2/*OUT_TOP*/ | 8/*OUT_BOTTOM*/;

            //Makes the binary (0010) if the point is above the box
        } else if (y < box_y) {
            out |=  2/*OUT_TOP*/;

            //Makes the binary (1000) if the point is below the box
        } else if (y > box_y + box_size) {
            out |= 8/*OUT_BOTTOM*/;
        }
        //returns the complete binary, if the point is inside the box, we have 0000 as a value, else it's outside of it.
        return out;
    }
}

class BiEdge extends Edge{

    public BiEdge(PApplet _sketch, Node _from, Node _to, int _weight){
        super(_sketch, _from, _to, _weight);
    }

    @Override
    public void render() {
        super.render();
    }
}
