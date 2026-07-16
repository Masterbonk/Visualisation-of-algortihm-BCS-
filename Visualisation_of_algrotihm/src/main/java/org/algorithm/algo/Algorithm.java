package org.algorithm.algo;

import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Algorithm {
    protected Node start_node;
    protected Node goal_node;
    public HashMap<Edge,Integer> edge_update_map;
    public static boolean has_been_paused;

    public HashSet<Node> set_of_nodes;

    public boolean first_run;

    public Priority_Queue U;

    public boolean part_one_d_main;

    public boolean dynamic = true;

    public Node highlighted_node;



    /**
     * Sets the goal node.
     * @param _n The node that becomes goal
     */
    public void set_Goal(Node _n) {
        goal_node = _n;
    }


    /**
     * Sets the start node.
     * @param _n The node that becomes start
     */
    public void set_Start(Node _n){
        start_node = _n;
    }


    /**
     * Gets the start node
     * @return The start node
     */
    public Node get_Start(){
        return start_node;
    }

    /**
     * Gets the goal node
     * @return The goal node
     */
    public Node get_Goal(){
        return goal_node;
    }

    /**
     * This removes the Node from the PQ and the set of nodes
     * @param n The node to remove
     */
    public void remove_Node(Node n){
    }

    public void compute_Shortest_Path(){
    }

    public Tuple calculate_Key(Node _n){
        return null;
    }

    public void initialize(){
    }

    public void Main() {
    }

    public void update_Vertex(Node _n){

    }

    /**
     * Gets the priority queue
     * @return The priority queue
     */
    public Priority_Queue get_U(){
        return U;
    }


    public ArrayList<Node> get_Shortest_Path(Node n){
        return null;
    }
}
