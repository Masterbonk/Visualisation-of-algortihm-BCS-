package org.algorithm;

public class Key implements Comparable<Key> {
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


