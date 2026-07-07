package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Main.Ui;

public class Visual_Dijkstra extends Dijkstra{
    int stage; //Value determining what code is run per step
    //Stage meaning:
    //0 = haven't run initialize yet
    //1 = Have to begin checking a new node
    //2 = Considering a node, one edge at a time
    //3 = All nodes have been considered and so we are done running compute_shortest_path
    //4 = Means that we have the shortest path and is showing one step of it at a time
    //5 = We are now completely done with the algorithm

    Node u;
    ArrayList<Edge> edges_considered;

    //Eventually collects the shortest path in edges, which are then colored one at a time.
    ArrayList<Edge> shortest_path;

    //All edges that have been considered are added to this set.
    HashSet<Edge> checked;


    public Visual_Dijkstra(){
        super();
        set_of_nodes = new HashSet<>();
        edge_update_map = new HashMap<>();
        start_node = null;
        goal_node = null;

        stage = 0;
        edges_considered = new ArrayList<>();
        u = null;
        shortest_path = new ArrayList<>();
        checked = new HashSet<>();

        //Lock the heuristic button since it won't work with the Dijkstra algorithm.
        Ui.get_Button("heuristic").locked = true;

    }

    /**
     * Every time it runs, it performs more and more operations, going through a specific flow which ends
     * with the whole graph considered and handled.
     */
    public void Main(){
        //Initial stage is used to initialize the algorithm and is only used once.
        if (stage == 0){
            initialize();
            stage = 1;

            //All buttons that can change the algorithm are locked down after the algorithm has started
            lock_Buttons();

        } else if (stage == 1 || stage == 2){
            //First and second stage, used to analyse the whole graph.

            compute_Shortest_Path();
        } else if (stage == 3 && goal_node != null){
            //If we have a goal, we find the shortest path and highlight the first of the edges on the path
            //If we don't have a goal, we just skip to the final stage.

            shortest_path = get_Shortest_Path_Edges();
            Edge tmp_edge = shortest_path.getLast();
            shortest_path.removeLast();
            tmp_edge.color(265,-1,75);
            Util.exchange(tmp_edge);
            stage = 4;
        }else if (stage == 4){
            // At this stage we have the whole path we need, so we highlight each edge one at a time.
            // When we have gone through the whole path, we move on.

            Edge tmp_edge = shortest_path.getLast();
            shortest_path.removeLast();
            tmp_edge.color(265,-1,75);
            Util.exchange(tmp_edge);
            if (shortest_path.isEmpty()){
                stage = 5;
            }
        } else if (stage == 5 || stage == 3){
            //We go to last stage which does nothing but open the chance to make changes to the graph again.
            stage = 5;
            unlock_Buttons();
        }

        //Steps forward once before stopping itself again.
        if(Main.Ui.get_Button("forward").clicked){
            Main.Ui.get_Button("forward").clicked = false;
            Main.Ui.get_Button("pause").clicked = true;
        }
    }

    public void compute_Shortest_Path() {
        boolean next_node = false;

        //Runs so long as we still haven't considered all nodes
        if (!U.is_empty()){

            //Pulls out the closest node to source in the PQ
            if (u == null) {
                u = U.pop();
                highlighted_node = u;
            }

            Edge e;

            //Look at all edges connected to our node u
            if (edges_considered.size() != u.get_Connected().size()){
                stage = 2;
                //Gets the edge that has not yet been covered
                ArrayList<Edge> tmp = new ArrayList<>(u.get_Connected());
                tmp.removeAll(edges_considered);
                if (!tmp.isEmpty()) {
                    e = tmp.getFirst();

                    while (checked.contains(e)) {
                        edges_considered.add(e);
                        tmp.removeFirst();
                        if (!tmp.isEmpty()) {
                            e = tmp.getFirst();
                        } else {
                            //This will create a problem when the last edge removed from node is already considered
                            //This is because the user will see the program "Stutter", not sure how to fix.
                            //WILL FIX LATER
                            break;
                        }
                    }
                    if (e != null) {
                        checked.add(e);
                        edges_considered.add(e);
                        e.color(-1, -1, 150);
                        Main.colored_edges.add(e);
                        Util.exchange(e);

                        //Gets the other node connected to edge e
                        Node v = u.help_Get_Opposite(e);

                        //v is the other node on the edge connected to our current focus, u

                        //We get the distance to travel to the node u, and the weight of the edge as the
                        // total alternative distance to travel to v
                        int alt = dist.get(u) + e.get_Weight();

                        //If it's smaller, we update it with the new shortest path and remove it.
                        if (alt < dist.get(v)) {
                            prev.put(v, u);
                            dist.put(v, alt);

                            //It's removed and readded to the PQ to make sure everything is balanced.
                            U.remove(v);
                            U.insert(v, alt);
                        }
                    } else {
                        //Current edge is null
                        next_node = true;
                    }
                } else {
                    //All edges has been covered
                    next_node = true;
                }
            } else {
                //Size is same, need to prepare for next iteration
                next_node = true;
            }
        } else {
            //U is now empty, so we move to last stage, finished stage.
            stage = 3;
            highlighted_node = null;
        }

        if (next_node){
            stage = 1;
            edges_considered = new ArrayList<>();
            highlighted_node = U.get_Heap().getFirst();
            if (u!=goal_node) {
                u = null;
            } else{
                u = null;
                stage = 3;
                highlighted_node = null;
            }
        }
    }

    /**
     * After compute shortest path has finished, this function can be used to acquire the whole path that DIjkstra found
     * It does this by taking the prev array from the goal to the start.
     * @return The shortest path from target to start in edges.
     */
    public ArrayList<Edge> get_Shortest_Path_Edges(){
        Node e = goal_node;
        ArrayList<Edge> shortest_path = new ArrayList<>();

        // Goes through the previous list and collects all nodes on the way, starting with target and ending with source.
        while(e != start_node){
            Edge tmp_edge = Util.find_Shared_Edge(e,prev.get(e));

            shortest_path.add(tmp_edge);

            e = prev.get(e);
        }

        //Edge tmp_edge = Util.find_Shared_Edge(e,prev.get(e));
        //shortest_path.add(tmp_edge); //e is now source

        //Collections.reverse(shortest_path);

        return shortest_path; //returns shortest path starting from source to target
    }


    public void lock_Buttons(){
        Ui.get_Button("cut").locked = true;
        Ui.get_Button("circle").locked = true;
        Ui.get_Button("line").locked = true;
        Ui.get_Button("flag_a").locked = true;
        Ui.get_Button("flag_b").locked = true;
        Ui.get_Button("weight").locked = true;
    }

    public void unlock_Buttons(){
        Ui.get_Button("cut").locked = false;
        Ui.get_Button("circle").locked = false;
        Ui.get_Button("line").locked = false;
        Ui.get_Button("flag_a").locked = false;
        Ui.get_Button("flag_b").locked = false;
        Ui.get_Button("weight").locked = false;
    }
}
