package org.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.min;
import static org.algorithm.Util.find_Shared_Edge;
import static org.algorithm.Util.heuristic;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class DStarLite {
    Node last;


    float km;
    Priority_Queue U;
    public static boolean has_been_paused = true;
    boolean first_run = true;
    boolean paused_once = false;


    DStarLite(){
        Main.set_of_nodes = new HashSet<>();
        Main.edge_update_map = new HashMap<>();

    }

    public void initialize(){
        if (Main.start_node == null && Main.goal_node == null) {
            println("start and/or goal are null");
            return;}

        for (Edge e:Main.edge_array) {
            e.color(75,75,75);
        }
        //if (start == null) throw new NullPointerException("Start not set!");
        //if (goal == null) throw new NullPointerException("Goal not set!");


        U = new Priority_Queue();

        km = 0;

        for(Node n: Main.set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }

        Main.goal_node.update_Rhs_Val(0);

        U.insert(Main.goal_node, calculate_Key(Main.goal_node));

    }

    public void D_Main(){
        if (first_run && Main.start_node != null && Main.goal_node != null){
            last = Main.start_node;
            initialize();
            compute_Shortest_Path();
            first_run = false;
        }
        while (Main.start_node != Main.goal_node){

            if (Main.start_node.get_G_Val() == MAX_INT) {
                println("No valid path to start");
                println("update map has size = "+Main.edge_update_map.size());
                check_For_Edge_Change();
                first_run = true;
                Main.Ui.get_Button("pause").click();
                break;
            }

            if (!has_been_paused && paused_once) {
                Edge e = find_Shared_Edge(Main.start_node, find_Min_G_Node(Main.start_node));
                if (e != null) e.color(0,0,150);
                Main.start_node = find_Min_G_Node(Main.start_node);
                println("Moved start to node at x: "+Main.start_node.x+" y: "+Main.start_node.y);
            }
            

            if(Main.Ui.get_Button("pause").clicked || Main.Ui.get_Button("forward").clicked && paused_once){
                has_been_paused = true;
                paused_once = false;
                Main.Ui.get_Button("forward").clicked = false;
                Main.Ui.get_Button("pause").clicked = true;

                break;
            }

            if (!Main.edge_update_map.isEmpty()){
                check_For_Edge_Change();

            }

            //This makes sure that only the right parts of the code is run, when we click forward
            //When we click forward it needs to do the check above once before it stops and breaks, this
            //statement makes sure of it.
            if (Main.Ui.get_Button("forward").clicked || !Main.Ui.get_Button("pause").clicked) {
                paused_once = true;
            }
        }
    }

    private void check_For_Edge_Change(){
        if (!Main.edge_update_map.isEmpty()) {
            km = km + heuristic(last, Main.start_node);
            last = Main.start_node;

            for (Edge e : Main.edge_update_map.keySet()) {
                if (Main.edge_update_map.get(e) != -1) { //Means that the
                    e.update_Weight(Main.edge_update_map.get(e));
                }
                update_Vertex(e.to);
                update_Vertex(e.from);
            }
            Main.edge_update_map.clear();

            compute_Shortest_Path();
        }
    }

    public void compute_Shortest_Path(){
        //println("pq 1 " + U.get_Heap());
        //println("pq to list 1 " + U.toList());
        Tupple k_old;
        Node n;
        while(U.top_Key().compareTo(calculate_Key(Main.start_node)) < 0 || Main.start_node.get_Rhs_Val() != Main.start_node.get_G_Val()){
            k_old = U.top_Key();
            n = U.pop();
            //println("pq 2 " + U.get_Heap());
            //println("pq to list 2 " + U.toList());
            //println("Popped node at x: "+n.x+" y: "+n.y);
            if(k_old.compareTo(calculate_Key(n)) < 0){
                U.insert(n, calculate_Key(n));
            } else if (n.get_G_Val() > n.get_Rhs_Val()){
                n.update_G_Val(n.get_Rhs_Val());
                for (Edge e: n.connected){
                    Node other_node = e.from;
                    if (e.from == n) other_node = e.to;
                    update_Vertex(other_node);
                }
            } else {
                n.update_G_Val(MAX_INT);
                for (Edge e: n.connected){
                    Node other_node = e.from;
                    if (e.from == n) other_node = e.to;
                    update_Vertex(other_node);
                }
                update_Vertex(n);
            }
        }
    }

    public ArrayList<Node> get_Shortest_Path(Node n){
        ArrayList<Node> result = new ArrayList<>();
        Node tmp = n;
        if (n.get_G_Val() != MAX_INT) {
            while (!result.contains(Main.goal_node)) {
                Node tmp2 = find_Min_G_Node(tmp);
                result.add(tmp);
                tmp = tmp2;
            }
            return result;
        }
        return null;
    }

    public int find_Min_G(Node _n){
        int min = MAX_INT;
        for(Edge e: _n.connected){
            Node other_node = e.from;
            if (e.from == _n) other_node = e.to;
            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.weight+other_node.get_G_Val()){
                    min = e.weight+other_node.get_G_Val();
                }
            }

        }
        return min;
    }

    public Node find_Min_G_Node(Node _n){
        int min = MAX_INT;
        Node tmp = null;
        for(Edge e: _n.connected){
            Node other_node = e.from;
            if (e.from == _n) other_node = e.to;
            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.weight+other_node.get_G_Val()){
                    min = e.weight+other_node.get_G_Val();
                    tmp = other_node;
                }
            }

        }
        return tmp;
    }

    public void update_Vertex(Node _n){
        println("Updating node at x: "+_n.x+" y: "+_n.y);
        if (_n !=Main.goal_node){
            _n.update_Rhs_Val(find_Min_G(_n));
        }

        if(U.contains(_n)){
            try{
                U.remove(_n);
            } catch (Exception e){
                println(e.getMessage());
            }
        }
        //println("pq 3 " + U.get_Heap());
        //println("pq to list 3 " + U.toList());

        if(_n.get_G_Val() != _n.get_Rhs_Val()){
            U.insert(_n, calculate_Key(_n));
            println("Added node to list at x: "+_n.x+" y: "+_n.y);
        }
        //println("pq 4 " + U.get_Heap());
        //println("pq to list 4 " + U.toList());

    }

    public Tupple calculate_Key(Node s){
        float k1, k2;

        k1 = min(s.get_G_Val(),s.get_Rhs_Val()) + heuristic(s, Main.start_node) + km;
        if(k1 < 0) k1 = MAX_INT;

        k2 = min(s.get_G_Val(),s.get_Rhs_Val());


        return new Tupple(k1, k2);
    }

    public void remove_Node(Node n){
        Main.set_of_nodes.remove(n);

        try {
            U.remove(n);
        } catch (Exception e){
            println(e.getMessage());
        }
    }

    public void set_Start(Node _n){
        Main.start_node = _n;
    }

    public void set_Goal(Node _n){
        Main.goal_node = _n;
    }

    public Node get_Start(){
        return Main.start_node;
    }

    public Node get_Goal(){
        return Main.goal_node;
    }

    public Priority_Queue get_U(){
        return U;
    }

}

