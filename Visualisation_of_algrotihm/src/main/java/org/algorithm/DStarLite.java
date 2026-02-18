package org.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.min;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class DStarLite {
    Node start;
    Node goal;
    float km;
    Priority_Queue U;


    DStarLite(){
        Main.set_of_nodes = new HashSet<>();
    }

    public void initialize() throws  Exception{
        if (start == null) throw new NullPointerException("Start not set!");
        if (goal == null) throw new NullPointerException("Goal not set!");


        U = new Priority_Queue();

        km = 0;

        for(Node n: Main.set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }

        goal.update_Rhs_Val(0);

        U.insert(calculate_Key(goal));

    }

    public void D_Main(){

    }

    public void compute_Shortest_Path(){

    }

    public void update_Vertex(Node n){
        if (n != goal){
            int min = MAX_INT;
            for(Edge e: n.connected){
                Node other_node = e.from;
                if (e.from == n) other_node = e.to;
                if (other_node.get_G_Val() != MAX_INT) {
                    if (min > e.weight+other_node.get_G_Val()){
                        min = e.weight+other_node.get_G_Val();
                    }
                }

            }
            n.update_Rhs_Val(min);
        }

        if(U.contains(n)){
            try{
                U.remove(n);
            } catch (Exception e){
                println(e.getMessage());
            }
        }

        if(n.get_G_Val() != n.get_Rhs_Val()){
            U.insert(calculate_Key(n));
        }
    }

    public Key calculate_Key(Node s){
        float k1, k2;

        k1 = min(s.g,s.rhs) + heuristic(s, start) + km;

        k2 = min(s.g,s.rhs);

        return new Key(s, k1, k2);
    }


    /** Calculates the heuristic between Node a & b
     *  Heuristic = distance between the two points
     * */
    public float heuristic(Node a, Node b){
        return (float) (Math.round(Math.sqrt((Math.pow(a.x - b.x,2)) + (Math.pow(a.y - b.y,2)))* 100.0) / 100.0);
    }

    public void remove_Node(Node n){
        Main.set_of_nodes.remove(n);

        try {
            U.remove(n);
        } catch (Exception e){
            println(e.getMessage());
        }
    }

}

