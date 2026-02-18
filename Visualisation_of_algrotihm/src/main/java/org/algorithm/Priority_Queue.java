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


    private void swim(int i) {
        while (i > 0 && greater(parent(i), i)) {
            exch(i, parent(i));
            i = parent(i);
        }
    }

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

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private boolean greater(int i, int j) {
        return heap.get(i).compareTo(heap.get(j)) > 0;
    }

    private void exch(int i, int j) {
        Key temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }



    /*
    * def _siftdown(heap, startpos, pos):
    newitem = heap[pos]
    # Follow the path to the root, moving parents down until finding a place
    # newitem fits.
    while pos > startpos:
        parentpos = (pos - 1) >> 1
        parent = heap[parentpos]
        if newitem < parent:
            heap[pos] = parent
            pos = parentpos
            continue
        break
    heap[pos] = newitem
    *
    * def _siftup(heap, pos):
    endpos = len(heap)
    startpos = pos
    newitem = heap[pos]
    # Bubble up the smaller child until hitting a leaf.
    childpos = 2*pos + 1    # leftmost child position
    while childpos < endpos:
        # Set childpos to index of smaller child.
        rightpos = childpos + 1
        if rightpos < endpos and not heap[childpos] < heap[rightpos]:
            childpos = rightpos
        # Move the smaller child up.
        heap[pos] = heap[childpos]
        pos = childpos
        childpos = 2*pos + 1
    # The leaf at pos is empty now.  Put newitem there, and bubble it up
    # to its final resting place (by sifting its parents down).
    heap[pos] = newitem
    _siftdown(heap, startpos, pos)
    * */


}
