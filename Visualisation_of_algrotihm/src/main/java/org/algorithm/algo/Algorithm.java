package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Util.find_Min_G_Node;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public abstract class Algorithm {
    public Priority_Queue U;
    protected Node start_node;
    protected Node goal_node;

    public boolean first_run;

    //d* specific find a better way
    public boolean part_one_d_main;
    public static boolean has_been_paused;
    boolean paused_once;
    public HashSet<Node> set_of_nodes;
    public HashMap<Edge,Integer> edge_update_map;


    public Algorithm(){

        set_of_nodes = new HashSet<>();
        edge_update_map = new HashMap<>();

        start_node = null;
        goal_node = null;


        first_run = true;
        has_been_paused = true;

        part_one_d_main = false;
        paused_once = false;
    }

    public void Main() {
    }

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
     * Gets the priority queue
     * @return The priority queue
     */
    public Priority_Queue get_U(){
        return U;
    }
    void check_For_Edge_Change() {
    }

    /**
     * This removes the Node from the PQ and the set of nodes
     * @param n The node to remove
     */
    public void remove_Node(Node n){
        set_of_nodes.remove(n);
        if(U != null){
            U.remove(n);
        }
    }

    public void update_Vertex(Node _n){

    }

    /**
     * A special function not part of the original paper on D* Lite.
     * returns the shortest path that computeShortestPath() function finds.
     * @param n is the start node or goal node, that we try to get to the opposite with.
     * @return A list of nodes that are traveled over towards the goal
     */
    public ArrayList<Node> get_Shortest_Path(Node n){
        ArrayList<Node> result = new ArrayList<>();
        Node tmp = n;
        if (n.get_G_Val() != MAX_INT) {
            if (n.equals(start_node)) {
                while (!result.contains(goal_node)) {
                    Node tmp2 = find_Min_G_Node(tmp);
                    result.add(tmp);
                    tmp = tmp2;
                }
            } else {
                while (!result.contains(start_node)) {
                    Node tmp2 = find_Min_G_Node(tmp);
                    result.add(tmp);
                    tmp = tmp2;
                }
            }
            return result;
        }
        return null;
    }



    public void compute_Shortest_Path(){
    }

    public Tupple calculate_Key(Node _n){
        return null;
    }

    public void initialize(){
    }

    public void reset(){

        for(Node n: set_of_nodes){
            println("reseting node " + n.toString());
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }


    }

}











