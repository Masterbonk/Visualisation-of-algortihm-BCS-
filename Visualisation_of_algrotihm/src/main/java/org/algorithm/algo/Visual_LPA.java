package org.algorithm.algo;

import org.algorithm.Egde_State;
import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;
import org.algorithm.Egde_State;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Egde_State.*;
import static org.algorithm.Main.*;
import static org.algorithm.ui.Color_Scheme.edge_final_path;
import static processing.core.PApplet.print;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;


public class  Visual_LPA extends LPA_Star{
    //Stage meanings:
    //0 = haven't run initialize yet
    // = we should not be checking for edge change while running algo
    //1,2,3,4,5 = computing shortest path
    //7 = coloring edges‰
    //8 = ensuring there is no changes to graph


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
        //println("Current stage is: " + stage);


        if (start_node == null || goal_node == null){ println("Start and or goal are null"); return;}

        //initilize stage
        if (stage == 0) {

                for (Edge e:Main.edge_array) {
                    e.set_Enum(idle);
                    edge_state_set.put(e,idle);

                }

            initialize();

            Ui.get_Button("flag_a").lock();
            Ui.get_Button("flag_a").clicked = false;

            if (first_run && Ui.get_Button("forward").clicked){
                first_run =false;
                compute_Shortest_Path();
            }
            stage = 2;

        } else if (stage == 2 || stage == 3 || stage == 4 || stage == 5) {
            lock_Buttons();
            compute_Shortest_Path();
            for (Edge e: Main.colored_edges){
                e.set_Enum(considered);
                edge_state_set.put(e,considered);

            }
        } else if (stage == 6) {
            edges_considered = super.get_Shortest_Path(goal_node);

            for (Edge e: Main.colored_edges) {
                e.set_Enum(considered);
                edge_state_set.put(e,considered);

            }

            if(edges_considered == null) {
                stage = 2;
                return; //do not remove, we now need to do pathfinding
            }


            color_Edge_On_Path();
            if (edges_considered.size() > 1) {
                stage = 7;
            } else {
                stage = 8;
            }
        } else if (stage == 7) {
            color_Edge_On_Path();
            if (edges_considered.size() == 1) {
                stage = 8;
            }
        } else if (stage == 8 && !edge_update_map.isEmpty()) {
            check_For_Edge_Change();

            for (Edge e:Main.colored_edges) {
                e.set_Enum(idle);
                edge_state_set.put(e,idle);

            }


            if (edge_update_map.isEmpty()) {
                stage = 2;
            }


        }

        //Steps forward once before stopping itself again.
        if(Main.Ui.get_Button("forward").clicked) {
            Main.Ui.get_Button("forward").clicked = false;
            Main.Ui.get_Button("pause").clicked = true;
        }
    }

    private void color_Edge_On_Path() {

        if (edges_considered == null){
            System.out.println("ABORT");
            return;
        }

        //reset to blue or grey?

        //then color green

        Edge e = Util.find_Shared_Edge(edges_considered.get(0), edges_considered.get(1)); //edges are null
        if (e != null) {
            e.set_Enum(finalpath);
            edge_state_set.put(e,finalpath);
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

        if ((U.top_Key().compareTo(calculate_Key(goal_node)) < 0 || goal_node.get_Rhs_Val() != goal_node.get_G_Val() ) &&  !U.get_Heap().isEmpty()){

            if (n == null && !U.is_empty()) {
                n = U.peak();
                highlighted_node = n;
            }

            if ((n.get_G_Val() > n.get_Rhs_Val() && stage == 2) || stage == 3){

                if (stage == 2) {

                    n.update_G_Val(n.get_Rhs_Val());
                    stage = 3;
                    checked_edges = new ArrayList<>();
                } else if (stage == 3){

                    if (checked_edges.size() != n.get_Connected().size() - 1) {

                        //color the edge blue
                        Edge e = n.get_Connected().get(checked_edges.size());
                        e.set_Enum(considered);
                        edge_state_set.put(e,considered);


                        //update neighboring vertex
                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);
                        checked_edges.add(e);

                    } else {

                        //color edge blue
                        Edge e = n.get_Connected().get(checked_edges.size());

                        e.set_Enum(considered);
                        edge_state_set.put(e,considered);


                        //update neighboring vertex
                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);

                        //set stage back to 2
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

                    if (checked_edges.size() < n.get_Connected().size() -1) {

                        //only get an edge if there is an edge to get, can try and fetch non-existent edges
                        if (!n.get_Connected().isEmpty()) {

                            Edge e;
                            if (n.get_Connected().size() == 1 && checked_edges.size() == 1) {
                                 e = n.get_Connected().getFirst();
                            } else {
                                 e = n.get_Connected().get(checked_edges.size());
                            }
                            //color edge grey
                            e.set_Enum(idle);
                            edge_state_set.put(e,idle);



                            //update neighboring vertex
                            Node other_node = e.get_From();
                            if (e.get_From() == n) other_node = e.get_To();
                            update_Vertex(other_node);
                            checked_edges.add(e);
                        }

                    } else {

                        Edge e;
                        if (checked_edges.size() == n.get_Connected().size()) {
                             e = n.get_Connected().getFirst();
                        } else {
                            e = n.get_Connected().get(checked_edges.size());
                        }
                        //color edge grey
                        e.set_Enum(idle);
                        edge_state_set.put(e,idle);



                        //update neighboring vertex
                        Node other_node = e.get_From();
                        if (e.get_From() == n) other_node = e.get_To();
                        update_Vertex(other_node);
                        checked_edges.add(e);

                        //go to stage 5
                        stage = 5;
                    }

                    n = null;

                } else if (stage == 5){

                    //reset tmp & n
                    Node tmp = n;
                    n = null;
                    U.pop();
                    update_Vertex(tmp);
                    stage = 2;
                }
            }
        } else {

            //compute shortest path done / not running
            //unlock buttons here
            unlock_Buttons();
            stage = 6;
            highlighted_node = null;
        }
    }

    void check_For_Edge_Change(){

        n = null;

        for (Edge e1 : edge_state_set.keySet()){
           if(e1.get_Edge_state() == finalpath ){
              e1.set_Enum(idle);
              edge_state_set.put(e1, idle);//unkown if should be idle or considered
           }
        }

        for (Edge e : edge_update_map.keySet()) {
            if (edge_update_map.get(e) != -1) {
                e.update_Weight(edge_update_map.get(e));

            }
            if(node_array.contains(e.get_To())) {
                update_Vertex(e.get_To());
            }
            if(node_array.contains(e.get_From())) {
                update_Vertex(e.get_From());
            }



        }

        edge_update_map = new HashMap<>();
    }

    public void remove_from_checked_edges(Edge _e){
        checked_edges.remove(_e);
    }
}
