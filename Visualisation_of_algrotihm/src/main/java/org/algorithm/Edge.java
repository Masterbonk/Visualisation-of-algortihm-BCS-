package org.algorithm;

import processing.core.PApplet;

import static org.algorithm.Main.zoom_level;

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
        weight = _weight;
    }

    public void render(){
        sketch.line(from.x,from.y, to.x, to.y);

        sketch.push();
        sketch.fill(100);
        sketch.stroke(100);
        sketch.strokeWeight(10*zoom_level);
        sketch.textAlign(sketch.RIGHT,sketch.BOTTOM);
        sketch.rectMode(sketch.CENTER);
        sketch.rect((from.x+to.x)/2f-(weight>9?sketch.getGraphics().textSize*0.5f:sketch.getGraphics().textSize*0.25f),
                (from.y+to.y)/2f-sketch.getGraphics().textSize*0.5f, (weight>9?sketch.getGraphics().textSize:sketch.getGraphics().textSize/2)*zoom_level,(sketch.getGraphics().textSize)*zoom_level);
        sketch.fill(255);
        sketch.text(""+weight, (from.x+to.x)/2f, (from.y+to.y)/2f);
        sketch.pop();

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
