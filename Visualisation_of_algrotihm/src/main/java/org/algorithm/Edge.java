package org.algorithm;

import processing.core.PApplet;

public class Edge {
    Node from;
    Node to;
    PApplet sketch;
    int weight;

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
        sketch.strokeWeight(10);
        sketch.textAlign(sketch.RIGHT,sketch.BOTTOM);
        sketch.rectMode(sketch.CENTER);
        sketch.rect((from.x+to.x)/2f-(weight>9?sketch.getGraphics().textSize*0.5f:sketch.getGraphics().textSize*0.25f), (from.y+to.y)/2f-sketch.getGraphics().textSize*0.5f, weight>9?sketch.getGraphics().textSize:sketch.getGraphics().textSize/2,sketch.getGraphics().textSize);
        sketch.fill(255);
        sketch.text(""+weight, (from.x+to.x)/2f, (from.y+to.y)/2f);
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
