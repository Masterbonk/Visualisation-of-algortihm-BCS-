package org.algorithm.algo;

import org.algorithm.Util;

public class Tuple implements Comparable<Tuple> {
    public float k1, k2;


    public Tuple(float _k1, float _k2){
        k1 = _k1;
        k2 = _k2;
    }

    @Override
    public int compareTo(Tuple other) {
        if (this.k1 < other.k1) return -1;
        if (this.k1 > other.k1) return 1;


        if (this.k2 < other.k2) return -1;
        if (this.k2 > other.k2) return 1;

        return 0;
    }

    public boolean same_Key(Tuple other){
        return this.k1 == other.k1 && this.k2 == other.k2;
    }


    @Override
    public String toString() {
        return "[" + Util.make_digit_fit_range(Math.round(k1), 10) + ", " + Util.make_digit_fit_range(Math.round(k2),10) + "]";
    }

    public String[] toStrings() {
        return new String[]{ Util.make_digit_fit_range(Math.round(k1), 10),  Util.make_digit_fit_range(Math.round(k2),10) };
    }


}


