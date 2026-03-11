package org.algorithm.algo;

import org.algorithm.graph.Node;

import java.util.ArrayList;

public abstract class Algorithm {
    public Priority_Queue U;
    protected Node start_node;
    protected Node goal_node;

    public boolean first_run = true;

    //d* specific find a better way
    public boolean part_one_d_main = false;
    public static boolean has_been_paused = true;
    boolean paused_once = false;



    public void Main() {
    }

    public Node get_Start() {
        return null;
    }

    public Node get_Goal() {
        return null;
    }

    public void set_Start(Node _n) {
    }

    public void set_Goal(Node _n) {
    }

    public Priority_Queue get_U() {
        return null;
    }

    void check_For_Edge_Change() {
    }

    public void remove_Node(Node n){
    }

    public void update_Vertex(Node _n){

    }

    public ArrayList<Node> get_Shortest_Path(Node n){
        return null;
    }

    public void compute_Shortest_Path(){
    }

    public Tupple calculate_Key(Node _n){
        return null;
    }

    public void initialize(){
    }

}











