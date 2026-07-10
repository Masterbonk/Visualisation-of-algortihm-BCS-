package org.algorithm.algo;

import org.algorithm.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;

import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class Priority_Queue <T extends Comparable<T>>{

    private ArrayList<Node> heap;

    private HashMap<Node, T> keys;

    //public boolean dynamic_algo = false;

    public Priority_Queue(){
        heap = new ArrayList<>();
        keys = new HashMap<>();
    }

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    public void insert(Node _n, Integer _k){
        println("Adding Node "+_n.get_Name());
        heap.add(_n);
        keys.put(_n, (T) _k);
        swim(heap.size() - 1);
    }

    /**
     * insert Node _n with a Key _k into the priority queue
     * @param _n Node to be inserted
     * @param _k Key to be inserted
     * */
    public void insert(Node _n, Tuple _k){
        heap.add(_n);
        keys.put(_n, (T) _k);
        swim(heap.size() - 1);
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
     * Gives us the TopKey in the queue, without removing it
     * ONLY WORKS WITH DYNAMIC ALGOS
     * @return The Key object with the highest priority
     */
    public Tuple top_Key(){
        if(is_empty()) return new Tuple(MAX_INT,MAX_INT);
        return (Tuple)keys.get(heap.getFirst());
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

    //using the compareTo function of key to compare elements
    private boolean greater(int _i, int _j) {
        //return ((T) keys.get( heap.get(_i))).compareTo( (T) keys.get(heap.get(_j))) > 0;
        T tmp = (T) keys.get( heap.get(_i));
        if (tmp.getClass().equals(Integer.class)){
            println("Integer");
            return (Integer)keys.get(heap.get(_i)) >= (Integer) keys.get(heap.get(_j));
        } else {
            println("Tuple");
            return ((Tuple) keys.get( heap.get(_i))).compareTo( (Tuple) keys.get(heap.get(_j))) > 0;
        }
    }

    //exchange 2 elements
    private void exch(int _i, int _j) {
        Node temp = heap.get(_i);
        heap.set(_i, heap.get(_j));
        heap.set(_j, temp);
    }

    public ArrayList<Node> get_Heap(){
        return heap;
    }


    public void clear_Heap(){
        heap = new ArrayList<>();
    }

    public HashMap<Node, T> get_Keys(){return keys;}
    public void clear_Keys(){
        keys = new HashMap<>();
    }
}
