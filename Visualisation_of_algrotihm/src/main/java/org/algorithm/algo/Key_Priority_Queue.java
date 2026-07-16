package org.algorithm.algo;

import org.algorithm.graph.Node;

import java.util.HashMap;

import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class Key_Priority_Queue extends Priority_Queue{
    private HashMap<Node, Tuple> keys;

    public Key_Priority_Queue(){
        keys = new HashMap<>();
    }

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    public void insert(Node _n, Tuple _k){
        heap.add(_n);
        keys.put(_n, _k);
        swim(heap.size() - 1);
    }

    //using the compareTo function of key to compare elements
    public boolean greater(int _i, int _j) {
        return (keys.get( heap.get(_i))).compareTo(keys.get(heap.get(_j))) > 0;
    }

    public HashMap<Node, Tuple> get_Keys(){return keys;}

    public void clear_Keys(){
        keys = new HashMap<>();
    }

    /**
     * Gives us the TopKey in the queue, without removing it
     * ONLY WORKS WITH DYNAMIC ALGOS
     * @return The Key object with the highest priority
     */
    public Tuple top_Key(){
        if(is_empty()) return new Tuple(MAX_INT,MAX_INT);
        return keys.get(heap.getFirst());
    }

    /**
     * Removes the given node n from the queue
     * @param _n the given node to be removed
     * */
    public void remove(Node _n) {
        heap.remove(_n);
        keys.remove(_n);
    }

}
