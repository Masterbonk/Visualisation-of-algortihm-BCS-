package org.algorithm;

public class Tupple implements Comparable<Tupple> {
    public float k1, k2;


    Tupple(float _k1, float _k2){
        k1 = _k1;
        k2 = _k2;
    }

    @Override
    public int compareTo(Tupple other) {
        if (this.k1 < other.k1) return -1;
        if (this.k1 > other.k1) return 1;


        if (this.k2 < other.k2) return -1;
        if (this.k2 > other.k2) return 1;

        return 0;
    }

    public boolean same_Key(Tupple other){
        return this.k1 == other.k1 && this.k2 == other.k2;
    }


    @Override
    public String toString() {
        return "[" + Util.make_digit_fit_range(Math.round(k1), 10) + ", " + Util.make_digit_fit_range(Math.round(k2),10) + "]";
    }


}


