package org.algorithm.algo;

import org.algorithm.graph.Node;

public class OldPriority_Queue_count_testing extends Priority_Queue<Tuple> {
    public static int push_counter = 0;
    public static int remove_counter = 0;
    public static int pop_counter = 0;


    public Node pop(){
        pop_counter += 1;

        return super.pop();
    }

    public void insert(Node _n, Tuple _t){
        push_counter += 1;

        super.insert(_n, _t);
    }

    public void remove(Node _n){
        remove_counter += 1;

        super.remove(_n);
    }
}
