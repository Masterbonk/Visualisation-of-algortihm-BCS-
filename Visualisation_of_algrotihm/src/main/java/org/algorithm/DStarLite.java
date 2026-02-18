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
        return (float) (Math.round(Math.sqrt((Math.pow(a.x - b.x,2)) + (Math.pow(a.y - b.y,2)))* 100.0) / 100.0);
    }

}

