package org.algorithm;

import kotlin.Pair;

public class DStarLite {

    DStarLite(){

    }

    public void D_Main(){

    }

    public void compute_Shortest_Path(){

    }

    public void update_Vertex(){

    }

    public Key calculate_Key(){
        return null;
    }

}

class Key{
    public Node n;
    public Pair<Float, Float> key_value_pair;

    Key(Node _n, float k1, float k2){
        n = _n;
        key_value_pair = new Pair<>(k1, k2);
    }
}


