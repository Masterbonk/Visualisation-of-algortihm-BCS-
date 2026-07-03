package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Main.node_array;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class Visual_Dijkstra extends Dijkstra{
    int stage; //Value determining what code is run per step
    //Stage meaning:
    //0 = haven't run initialize yet
    //1 = Have to begin checking a new node
    //2 = Considering a node, one edge at a time
    //3 = All nodes have been considered and so we are done running compute_shortest_path

    Node u;
    ArrayList<Edge> edges_considered;


    public Visual_Dijkstra(){
        super();
        set_of_nodes = new HashSet<>();
        edge_update_map = new HashMap<>();
        start_node = null;
        goal_node = null;

        stage = 0;
        edges_considered = new ArrayList<>();
        u = null;

        //println("Visual Dijkstra called");
    }

    public void Main(){
        if (stage == 0){
            initialize();
            stage = 1;

            //println("Visual Dijkstra initialize called");

        } else if (stage == 1 || stage == 2){
            compute_Shortest_Path();
        }
        //println("Visual Dijkstra Main called");

        //Steps forward once before stopping itself again.
        if(Main.Ui.get_Button("forward").clicked){
            Main.Ui.get_Button("forward").clicked = false;
            Main.Ui.get_Button("pause").clicked = true;
        }
    }

    public void compute_Shortest_Path() {
        //Runs so long as we still haven't considered all nodes
        if (!U.is_empty()){

            //Pulls out the closest node to source in the PQ
            if (u == null) {
                u = U.pop();
            }

            Edge e;

            //Look at all edges connected to our node u
            if (edges_considered.size() != u.get_Connected().size()){
                stage = 2;
                //Gets the edge that has not yet been covered
                ArrayList<Edge> tmp = new ArrayList<Edge>(u.get_Connected());
                tmp.removeAll(edges_considered);
                e = tmp.getFirst();
                edges_considered.add(e);
                e.color(-1,-1,150);
                Main.colored_edges.add(e);
                Util.exchange(e);

                //Gets the other node connected to edge e
                Node v = u.help_Get_Opposite(e);

                //v is the other node on the edge connected to our current focus, u

                //We get the distance to travel to the node u, and the weight of the edge as the
                // total alternative distance to travel to v
                int alt = dist.get(u) + e.get_Weight();

                //If it's smaller, we update it with the new shortest path and remove it.
                if (alt < dist.get(v)){
                    prev.put(v, u);
                    dist.put(v, alt);

                    //It's removed and readded to the PQ to make sure everything is balanced.
                    U.remove(v);
                    U.insert(v, alt);
                }
            } else {
                //Size is same, need to prepare for next iteration
                stage = 1;
                edges_considered = new ArrayList<>();
                u = null;
            }
        } else {
            //U is now empty, so we move to last stage, finished stage.
            stage = 3;
        }
        //println("Visual Dijkstra Compute shortest path called");
    }
}
