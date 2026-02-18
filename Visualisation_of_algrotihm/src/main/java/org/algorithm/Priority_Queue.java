package org.algorithm;
import java.util.*;


public class Priority_Queue {

    ArrayList<Key> heap;

    Priority_Queue(){
        heap = new ArrayList<>();
    }

    /**
     * insert Key _k into the priority queue
     * @param _k Key to be inserted
     * */
    void insert(Key _k){
        heap.add(_k);                 // add to end
        swim(heap.size() - 1);
    }

    /**
     * @return and delete the top key of queue
    * */
    Key pop(){
        if (is_empty()) return null;

        Key min = heap.getFirst();

        exch(0, heap.size() - 1);
        heap.removeLast();
        sink(0);

        return min;
    }

    /**
     * Gives us the TopKey in the queue, without removing it
     * @return The Key object with the highest priority
     */
    public Key top_Key(){
        return heap.getFirst();
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
    private void sink(int i) {
        while (left(i) < heap.size()) {

            int smallest = left(i);

            if (right(i) < heap.size() && greater(smallest, right(i))) {
                smallest = right(i);
            }

            if (!greater(i, smallest)) break;

            exch(i, smallest);
            i = smallest;
        }
    }

    //the get "parent" node
    private int parent(int i) {
        return (i - 1) / 2;
    }
    //get the "left" node
    private int left(int i) {
        return 2 * i + 1;
    }

    //get the "right" node
    private int right(int i) {
        return 2 * i + 2;
    }

    //uisng the compareTo function of key to compare elements
    private boolean greater(int i, int j) {
        return heap.get(i).compareTo(heap.get(j)) > 0;
    }

    //exchange 2 elements
    private void exch(int i, int j) {
        Key temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


}
