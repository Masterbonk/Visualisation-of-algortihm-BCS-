package org.algorithm.algo;

import org.algorithm.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;

import static processing.core.PApplet.print;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public abstract class Priority_Queue {

    public ArrayList<Node> heap;

    //public boolean dynamic_algo = false;

    public Priority_Queue(){
        heap = new ArrayList<>();
    }

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    public void insert(Node _n, Integer _k){}

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    public void insert(Node _n, Tuple _k){
    }

    /**
     * @return and delete the top key of queue
     * */
    public Node pop(){
        if (is_empty()) return null;

        Node min = heap.getFirst();

        exch(0, heap.size() - 1);
        heap.removeLast();
        sink(0);

        return min;
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
    public void remove(Node _n) {}

    /**
     * @return the size of the queue
     * */
    public int size(){
        return heap.size();
    }

    /**
     * @return true if the queue is empty
     * */
    public boolean is_empty(){
        return heap.isEmpty();
    }

    public ArrayList<Node> toList() {
        ArrayList<Node> listOrdered = new ArrayList<>();

        //for(Node n : heap){
        //    if (n.name != null) listOrdered.add(n.name);
        //    else listOrdered.add(n.toString());
        //}
        if (!heap.isEmpty()) {
            listOrdered.add(heap.getFirst());
        }


        return listOrdered;
    }

    //swim the key up the heap
    public void swim(int i) {
        while (i > 0 && greater(parent(i), i)) {
            exch(i, parent(i));
            i = parent(i);
        }
    }



    //sink the key down the heap
    public void sink(int _i) {
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

    //using the compareTo function of key to compare elements
    public boolean greater(int _i, int _j) {
        return false;
    }

    //exchange 2 elements
    private void exch(int _i, int _j) {
        println("Switching node "+get_Heap().get(_i)+" with "+get_Heap().get(_j));

        Node temp = heap.get(_i);
        heap.set(_i, heap.get(_j));
        heap.set(_j, temp);
    }

    /**
     * Gives us the TopKey in the queue, without removing it
     * ONLY WORKS WITH DYNAMIC ALGOS
     * @return The Key object with the highest priority
     */
    public Tuple top_Key(){
        return null;
    }

    public ArrayList<Node> get_Heap(){
        return heap;
    }

    public HashMap get_Keys(){return null;}



    public void clear_Heap(){
        heap = new ArrayList<>();
    }

    public void clear_Keys(){}


}
