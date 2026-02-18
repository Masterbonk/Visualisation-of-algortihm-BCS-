package org.algorithm;

import java.util.Collection;

import static processing.core.PApplet.println;

public class DStarLite {
    Node start;
    Node goal;
    float km;
    Collection U; //Queue placeholder type


    DStarLite(){

    }

    public void initialize(){

    }

    public void D_Main(){

    }

    public void compute_Shortest_Path(){

    }

    public void update_Vertex(Node n){


    }

    public Key calculate_Key(Node s){
        return null;
    }


    /** Calculates the heuristic between Node a & b
     *  Heuristic = distance between the two points
     * */
    public float heuristic(Node a, Node b){
        return (float) Math.sqrt((Math.pow(a.x - b.x,2)) + (Math.pow(a.y - b.y,2)));
    }

}

class Key implements Comparable<Key> {
    public Node n;
    public float k1, k2;


    Key(Node _n, float _k1, float _k2){
        n = _n;
        k1 = _k1;
        k2 = _k2;
    }

    @Override
    public int compareTo(Key other) {
        if (this.k1 < other.k1) return -1;
        if (this.k1 > other.k1) return 1;


        if (this.k2 < other.k2) return -1;
        if (this.k2 > other.k2) return 1;

        return 0;
    }


}


