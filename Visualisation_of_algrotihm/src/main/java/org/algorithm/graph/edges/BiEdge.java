package org.algorithm.graph.edges;

import org.algorithm.graph.Node;
import processing.core.PApplet;

public class BiEdge extends Edge{

    public BiEdge(PApplet _sketch, Node _from, Node _to, int _weight){
        super(_sketch, _from, _to, _weight);
    }

    public BiEdge(PApplet _sketch, Node _from, Node _to){
        super(_sketch, _from, _to, 0);
    }

    @Override
    public void render() {
        super.render();
    }
}
