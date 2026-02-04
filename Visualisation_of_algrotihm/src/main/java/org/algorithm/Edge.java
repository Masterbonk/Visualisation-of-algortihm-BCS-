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
    }


}
