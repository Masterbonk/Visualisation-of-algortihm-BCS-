package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Main.*;
import static processing.core.PConstants.MAX_INT;

public class Visual_AStar extends A_Star{
    //stage = Value determining what code is run per step
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

    Node former_goal_node;

    public Visual_AStar(){
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
    }

    /**
     * Every time it runs, it performs more and more operations, going through a specific flow which ends
     * with the whole graph considered and handled.
     */
    public void Main(){
        //lock run and unpause button while start and goal undefined?


        //Make the program reset if you click on any of the buttons that can be used to change stuff after it has completed
        if (!edge_update_map.isEmpty() && (Main.Ui.get_Button("forward").clicked || !Main.Ui.get_Button("pause").clicked)){
            stage = 0;
            Ui.get_Button("reset").click();
            edge_update_map = new HashMap<>();
        } else {

            //Initial stage is used to initialize the algorithm and is only used once.
            if (stage == 0 && start_node != null && goal_node != null) {
                initialize();
                stage = 1;

                //All buttons that can change the algorithm are locked down after the algorithm has started
                lock_Buttons();

            } else if (stage == 1 || stage == 2) {
                //First and second stage, used to analyse the whole graph.
                compute_Shortest_Path();

            } else if (stage == 3 && goal_node != null && prev.get(goal_node) != null) {
                //If we have a goal, we find the shortest path and highlight the first of the edges on the path
                //If we don't have a goal, we just skip to the final stage.
                former_goal_node = goal_node;
                lock_Buttons();

                shortest_path = get_Shortest_Path_Edges();
                Edge tmp_edge = shortest_path.getLast();
                shortest_path.removeLast();
                tmp_edge.color(265, -1, 75);
                Util.exchange(tmp_edge);
                stage = 4;
            } else if (stage == 3 && goal_node != null && prev.get(goal_node) == null) {
                stage = 0;
                Ui.get_Button("reset").click();

            } else if (stage == 4 && !shortest_path.isEmpty()) {
                // At this stage we have the whole path we need, so we highlight each edge one at a time.
                // When we have gone through the whole path, we move on.

                Edge tmp_edge = shortest_path.getLast();
                shortest_path.removeLast();
                tmp_edge.color(265, -1, 75);
                Util.exchange(tmp_edge);
                if (shortest_path.isEmpty()) {
                    stage = 5;
                }
            } else if (stage == 4) {
                //If there is a direct edge between goal and start,
                // we just go to the last stage, as the first edge is covered by stage 3
                stage = 5;

            } else if (stage == 3) {
                //We go to last stage which does nothing but open the chance to make changes to the graph again.
                Ui.get_Button("flag_b").locked = false;
                former_goal_node = goal_node;
            } else if (stage == 5) {
                //We go to last stage which does nothing but open the chance to make changes to the graph again.
                unlock_Buttons();

                //This allows us to detect that the goal node has been changed, meaning we will search for a new shortest path
                if (former_goal_node != goal_node) {
                    lock_Buttons();
                    stage = 0;
                    for (Edge e : colored_edges) {
                        e.color(75, -1, 75);
                    }
                    Ui.get_Button("reset").click();
                }
            }
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
                        int tentative_g_score = dist.get(u) + e.get_Weight();

                        //If it's smaller, we update it with the new shortest path and remove it.
                        if (tentative_g_score < dist.get(v)) {
                            prev.put(v, u);
                            dist.put(v, tentative_g_score);

                            //It's removed and readded to the PQ to make sure everything is balanced.
                            U.remove(v);
                            U.insert(v, tentative_g_score + (int) Util.heuristic(v,goal_node));
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


}
