package org.algorithm.algo;

import org.algorithm.Util;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.HashMap;

import static org.algorithm.Main.node_array;
import static processing.core.PConstants.MAX_INT;

public class A_Star extends Non_Dynamic_Algorithm{

    public A_Star(){
        super();
    }

    /**
     * Main runs both initialize and compute_Shortest_Path(), effectively creating the whole graph
     */
    public void Main(){
        initialize();
        compute_Shortest_Path();
    }

    /**
     * Sets up the initial state for the algorithm to begin working.
     * Including:
     * Adding all nodes to the lists with the max values they can have
     * Making the source node into the start value by giving it a distance to
     * travel into heuristic between start and goal).
     */
    public void initialize(){
        for (Node v: node_array) {
            if (v != start_node) {
                dist.put(v, MAX_INT);
                prev.put(v, null);
                U.insert(v, MAX_INT);
            }
        }
        dist.put(start_node, 0);
        //Saving it as int instead of as float could create problems later, not sure.
        U.insert(start_node, (int) Util.heuristic(start_node,goal_node));
    }

    /**
     * Computes the shortest path by checking all nodes in the order of how close the travel time
     * to the node is from the source. It also uses heuristic to make it even more directed.
     */
    public void compute_Shortest_Path() {

        //Runs so long as we still haven't considered all nodes
        while(!U.is_empty()){

            //Pulls out the closest node to source in the PQ
            Node u = U.pop();

            //Look at all edges connected to our node u
            for (Edge e: u.get_Connected()){
                //Gets the other node connected to edge e
                Node v = u.help_Get_Opposite(e);

                //v is the other node on the edge connected to our current focus, u

                //We get the distance to travel to the node u, and the weight of the edge as the
                // total alternative distance to travel to v
                int tentative_g_score = dist.get(u) + e.get_Weight();

                //If it's smaller, we update it with the new shortest path and remove it.
                if (tentative_g_score < dist.get(v)){
                    prev.put(v, u);
                    dist.put(v, tentative_g_score);

                    //It's removed and readded to the PQ to make sure everything is balanced.
                    U.remove(v);
                    //Note how we add the heuristic, and thereby prioritises the nodes
                    // closest to the goal
                    U.insert(v, tentative_g_score + (int) Util.heuristic(v,goal_node));
                }
            }

            if(u==goal_node){
                //If we look at all nodes connected to the goal, we know that we have found the shortest path
                break;
            }
        }
    }
}
