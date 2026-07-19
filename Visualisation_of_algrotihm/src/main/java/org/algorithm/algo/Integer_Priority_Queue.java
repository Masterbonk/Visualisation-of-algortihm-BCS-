package org.algorithm.algo;

import org.algorithm.graph.Node;

import java.util.HashMap;

import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class Integer_Priority_Queue extends Priority_Queue{
    private HashMap<Node, Integer> keys;

    public Integer_Priority_Queue() {
        keys = new HashMap<>();
    }

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    public void insert(Node _n, Integer _k){
        heap.add(_n);
        keys.put(_n, _k);
        swim(heap.size() - 1);

    }

    /**
     * Removes the given node n from the queue
     * @param _n the given node to be removed
     * */
    public void remove(Node _n) {
        Node highest = heap.getFirst();
        exch(heap.indexOf(_n), 0); //Exchange with top
        pop();
        keys.remove(_n);
        swim(heap.indexOf(highest));
    }

    //using the compareTo function of key to compare elements
    //i is parent, while j is child, ie. is parent greater than child
    public boolean greater(int _i, int _j) {

        if (keys.get(heap.get(_i)) == MAX_INT && keys.get(heap.get(_j))!= MAX_INT){
            return true;
        }
        return keys.get(heap.get(_i)) > keys.get(heap.get(_j));
        //return (keys.get(heap.get(_i))).compareTo(keys.get(heap.get(_j))) > 0;
    }

    public HashMap<Node, Integer> get_Keys(){return keys;}

    public void clear_Keys(){
        keys = new HashMap<>();
    }


}
