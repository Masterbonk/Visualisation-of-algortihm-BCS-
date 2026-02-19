package org.algorithm;
import java.util.*;


public class Priority_Queue {

    private ArrayList<Node> heap;

    private HashMap<Node,Tupple> keys;

    Priority_Queue(){
        heap = new ArrayList<>();
        keys = new HashMap<>();
    }

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    void insert(Node _n, Tupple _k){
        heap.add(_n);
        keys.put(_n,_k);
        swim(heap.size() - 1);
    }

    /**
     * @return and delete the top key of queue
    * */
    Node pop(){
        if (is_empty()) return null;

        Node min = heap.getFirst();

        exch(0, heap.size() - 1);
        heap.removeLast();
        sink(0);

        return min;
    }

    /**
     * Gives us the TopKey in the queue, without removing it
     * @return The Key object with the highest priority
     */
    public Tupple top_Key(){
        return keys.get(heap.getFirst());
    }
    /**
     * @return true if the given node n is in the queue
     * @param _n the node to check if it is in the queue
     * */
    public boolean contains(Node _n){
        return heap.contains(_n);
    }
    /**
     * Removes the given node n from the queue
     * @param _n the given node to be removed
     * */
    public void remove(Node _n) {
        heap.remove(_n);
        keys.remove(_n);

    }

    /**
     * @return the size of the queue
     * */
    int size(){
        return heap.size();
    }

    /**
     * @return true if the queue is empty
     * */
    boolean is_empty(){
        return heap.isEmpty();
    }

    //swim the key up the heap
    private void swim(int i) {
        while (i > 0 && greater(parent(i), i)) {
            exch(i, parent(i));
            i = parent(i);
        }
    }

    //sink the key down the heap
    private void sink(int _i) {
        while (left(_i) < heap.size()) {

            int smallest = left(_i);

            if (right(_i) < heap.size() && greater(smallest, right(_i))) {
                smallest = right(_i);
            }

            if (!greater(_i, smallest)) break;

            exch(_i, smallest);
            _i = smallest;
        }
    }

    //the get "parent" node
    private int parent(int _i) {
        return (_i - 1) / 2;
    }
    //get the "left" node
    private int left(int _i) {
        return 2 * _i + 1;
    }

    //get the "right" node
    private int right(int _i) {
        return 2 * _i + 2;
    }

    //uisng the compareTo function of key to compare elements
    private boolean greater(int _i, int _j) {
        return keys.get(heap.get(_i)).compareTo(keys.get(heap.get(_j))) > 0;
    }

    //exchange 2 elements
    private void exch(int _i, int _j) {
        Node temp = heap.get(_i);
        heap.set(_i, heap.get(_j));
        heap.set(_j, temp);
    }


}
