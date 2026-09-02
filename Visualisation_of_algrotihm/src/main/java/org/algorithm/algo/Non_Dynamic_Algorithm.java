package org.algorithm.algo;

import org.algorithm.Util;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.algorithm.Main.Ui;
import static processing.core.PApplet.println;

public abstract class Non_Dynamic_Algorithm extends Algorithm{
    public HashMap<Node, Integer> dist;
    public HashMap<Node, Node> prev;

    public Non_Dynamic_Algorithm(){
        super();
        dist = new HashMap<>();
        prev = new HashMap<>();
        U = new Integer_Priority_Queue();

        dynamic = false;
    }

    /**
     * After compute shortest path has finished, this function can be used to acquire the whole path that DIjkstra found
     * It does this by taking the prev array from the target to the source.
     * @return The shortest path from source to target.
     */
    public ArrayList<Node> get_Shortest_Path(){
        Node e = goal_node;
        ArrayList<Node> shortest_path = new ArrayList<>();

        // Goes through the previous list and collects all nodes on the way, starting with target and ending with source.
        while(e != start_node){
            shortest_path.add(e);
            e = prev.get(e);
        }

        shortest_path.add(e); //e is now source

        Collections.reverse(shortest_path);

        return shortest_path; //returns shortest path starting from source to target
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
            //println("Prev for "+e.get_Name()+" is "+prev.get(e).get_Name());

            shortest_path.add(tmp_edge);

            e = prev.get(e);
        }

        //Edge tmp_edge = Util.find_Shared_Edge(e,prev.get(e));
        //shortest_path.add(tmp_edge); //e is now source

        //Collections.reverse(shortest_path);

        return shortest_path; //returns shortest path starting from source to target
    }

    public void lock_Buttons(){
        for (String name : Ui.bottom_ui) {
            if(name.equals("reset") || name.equals("pause") || name.equals("forward")) {
               continue;
            }
            Ui.get_Button(name).lock();
        }
    }

    public void unlock_Buttons(){
        for (String name : Ui.bottom_ui) {
            Ui.get_Button(name).unlock();
        }
    }
}
