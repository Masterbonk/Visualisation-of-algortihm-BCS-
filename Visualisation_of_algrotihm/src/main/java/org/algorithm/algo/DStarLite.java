package org.algorithm.algo;

import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.min;
import static org.algorithm.Util.find_Min_G;
import static org.algorithm.Util.find_Min_G_Node;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class DStarLite extends Algorithm {
    Node last;
    float km;

    DStarLite(){
        super();
    }

    public void initialize(){
        if (start_node == null) throw new NullPointerException("start not set!");
        if (goal_node == null) throw new NullPointerException("goal not set!");

        U = new Priority_Queue();

        km = 0;

        for(Node n: set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }

        goal_node.update_Rhs_Val(0);

        U.insert(goal_node, calculate_Key(goal_node));
    }

    public void D_Main(){
        last = start_node;
        initialize();
        compute_Shortest_Path();

        while (start_node != goal_node){
            if (start_node.get_G_Val() == MAX_INT) {
                println("No valid path to start");
                break;
            }

            start_node = find_Min_G_Node(start_node);


            if (!edge_update_map.isEmpty()){
                km = km + heuristic(last,start_node);
                last = start_node;

                for(Edge e: edge_update_map.keySet()){
                    e.update_Weight(edge_update_map.get(e));
                    update_Vertex(e.get_To());
                    update_Vertex(e.get_From());
                }
                edge_update_map = new HashMap<>();

                compute_Shortest_Path();
            }
        }
    }

    public void compute_Shortest_Path(){
        Tupple k_old;
        Node n;
        while(U.top_Key().compareTo(calculate_Key(start_node)) < 0 || start_node.get_Rhs_Val() != start_node.get_G_Val()){
            k_old = U.top_Key();
            n = U.pop();
            if(k_old.compareTo(calculate_Key(n)) < 0){
                U.insert(n, calculate_Key(n));
            } else if (n.get_G_Val() > n.get_Rhs_Val()){
                n.update_G_Val(n.get_Rhs_Val());
                for (Edge e: n.get_Connected()){
                    Node other_node = e.get_From();
                    if (e.get_From() == n) other_node = e.get_To();
                    update_Vertex(other_node);
                }
            } else {
                n.update_G_Val(MAX_INT);
                for (Edge e: n.get_Connected()){
                    Node other_node = e.get_From();
                    if (e.get_From() == n) other_node = e.get_To();
                    update_Vertex(other_node);
                }
                update_Vertex(n);
            }
        }
    }


    public void update_Vertex(Node _n){
        if (_n != goal_node){
            _n.update_Rhs_Val(find_Min_G(_n));
        }

        if(U.contains(_n)){
            U.remove(_n);
        }

        if(_n.get_G_Val() != _n.get_Rhs_Val()){
            U.insert(_n, calculate_Key(_n));
        }
    }

    public Tupple calculate_Key(Node s){
        float k1, k2;

        k1 = min(s.get_G_Val(),s.get_Rhs_Val()) + heuristic(s, start_node) + km;

        k2 = min(s.get_G_Val(),s.get_Rhs_Val());

        return new Tupple(k1, k2);
    }


    /** Calculates the heuristic between Node a & b
     *  Heuristic = distance between the two points
     * */
    public float heuristic(Node a, Node b){
        return (float) (Math.round(Math.sqrt((Math.pow(a.get_X() - b.get_X(),2)) + (Math.pow(a.get_Y() - b.get_Y(),2)))* 100.0) / 100.0);
    }
}

