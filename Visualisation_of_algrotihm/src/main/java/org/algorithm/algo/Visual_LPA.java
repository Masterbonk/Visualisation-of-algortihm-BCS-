package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Main.*;
import static processing.core.PApplet.print;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class  Visual_LPA extends LPA_Star{
    //Stage meanings:
    //0 = haven't run initialize yet

    private Node n = null;
    private ArrayList<Edge> checked_edges;


    public Visual_LPA(){
        super();
        stage = 0;
        checked_edges = new ArrayList<>();
        set_of_nodes = new HashSet<>();
        edge_update_map = new HashMap<>();
        start_node = null;
        goal_node = null;

        stage = 0;
        edges_considered = new ArrayList<>();
        colored_edges = new HashSet<>();
    }


    public void initialize(){
        for(Node n: node_array){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }
        super.initialize();
        n = null;
    }

    public void Main(){
        println("Current stage is: "+stage);
        if (start_node == null || goal_node == null){ println("Start and or goal are null"); return;}

        if (stage == 0) {
            /*for (Edge e:Main.edge_array) {
                e.color(75,75,75);
            }
             */
            initialize();
            first_run = false;
            stage = 1;
            Ui.get_Button("flag_b").locked = true;
            Ui.get_Button("flag_b").clicked = false;
        } else if (stage == 1 && !edge_update_map.isEmpty()){
            check_For_Edge_Change();

            /*for (Edge e:Main.colored_edges) {
                e.color(75,75,75);
            }
             */

            if (edge_update_map.isEmpty()) stage = 2;
        } else if (stage == 1) {
            stage = 2;
        } else if (stage == 2 || stage == 3 || stage == 4 || stage == 5) {
            println("Computing shortest path");
            compute_Shortest_Path();
            for (Edge e: Main.colored_edges){
                e.color(75, -1, 150);
            }
        } else if (stage == 6) {
            edges_considered = super.get_Shortest_Path(goal_node);


            for (Edge e: Main.colored_edges){
                e.color(75, -1, 150);
            }


            if(edges_considered == null){
                println("Entering stage 1");
                stage = 1;
                return;
            }
            color_Edge_On_Path();
            if (edges_considered.size() > 1){
                stage = 7;
            } else {
                stage = 8;
            }
        } else if (stage == 7) {
            color_Edge_On_Path();
            if (edges_considered.size() == 1){
                stage = 8;
            }
        } else if (stage == 8 && !edge_update_map.isEmpty()) {
            check_For_Edge_Change();

            for (Edge e:Main.colored_edges) {
                e.color(75,-1,-1);
            }


            if (edge_update_map.isEmpty()) {
                stage = 2;
            }
        }

        //Steps forward once before stopping itself again.
        if(Main.Ui.get_Button("forward").clicked){
            Main.Ui.get_Button("forward").clicked = false;
            Main.Ui.get_Button("pause").clicked = true;
        }
    }

    private void color_Edge_On_Path() {
        Edge e = Util.find_Shared_Edge(edges_considered.get(0), edges_considered.get(1));
        if (e != null) {
            e.color(265,-1,75);
            Main.colored_edges.add(e);
            Util.exchange(e);
        }
        edges_considered.removeFirst();
    }

    public void set_Goal(Node _n){
        goal_node = _n;
    }

    public void set_Start(Node _n){
        start_node = _n;
    }

    public void compute_Shortest_Path(){

        if ((U.top_Key().compareTo(calculate_Key(goal_node)) < 0 || goal_node.get_Rhs_Val() != goal_node.get_G_Val() ) && !U.get_Heap().isEmpty()){
            println("Running pathfinding");
            if (n == null) {
                n = U.get_Heap().getFirst();
                highlighted_node = n;
            }

            if ((n.get_G_Val() > n.get_Rhs_Val() && stage == 2) || stage == 3){

                if (stage == 2) {
                    n.update_G_Val(n.get_Rhs_Val());
                    stage = 3;
                    checked_edges = new ArrayList<>();
                } else if (stage == 3){
                    if (checked_edges.size() != n.get_Connected().size() - 1) {
                        Edge e = n.get_Connected().get(checked_edges.size());
                        e.color(-1, -1, 150);
                        Main.colored_edges.add(e);
                        Util.exchange(e);

                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);
                        checked_edges.add(e);
                    } else {
                        Edge e = n.get_Connected().get(checked_edges.size());
                        e.color(-1, -1, 150);
                        Main.colored_edges.add(e);
                        Util.exchange(e);

                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);
                        stage = 2;
                        n = null;
                        U.pop();
                    }
                }
            } else if (stage == 2 || stage == 4 || stage == 5){
                if (stage == 2) {
                    n.update_G_Val(MAX_INT);
                    stage = 4;
                    checked_edges = new ArrayList<>();
                } else if (stage == 4) {
                    if (checked_edges.size() != n.get_Connected().size() - 1) {
                        Edge e = n.get_Connected().get(checked_edges.size());

                        e.color(-1, -1, 75);
                        Main.colored_edges.remove(e);
                        Util.exchange(e);

                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);
                        checked_edges.add(e);

                    } else {
                        Edge e = n.get_Connected().get(checked_edges.size());
                        e.color(-1, -1, 75);
                        Main.colored_edges.remove(e);
                        Util.exchange(e);
                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);
                        stage = 5;
                    }

                } else if (stage == 5){
                    Node tmp = n;
                    n = null;
                    U.pop();
                    update_Vertex(tmp);
                    stage = 2;
                }
            }
        } else {
            stage = 6;
            highlighted_node = null;
        }
    }

    void check_For_Edge_Change(){
        for (Edge e : edge_update_map.keySet()) {
            if (edge_update_map.get(e) != -1) {
                e.update_Weight(edge_update_map.get(e));
            }
            update_Vertex(e.get_To());
            update_Vertex(e.get_From());

            edge_update_map.remove(e);

            break;
        }
    }
}
