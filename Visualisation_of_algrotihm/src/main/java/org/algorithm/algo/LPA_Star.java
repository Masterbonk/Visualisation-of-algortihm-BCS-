package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Util.*;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class LPA_Star extends Algorithm{



    public LPA_Star(){
        super();

    }

    public void Main(){
      initialize();
      //AHHH shit i'm temporaraly running
      //NAH BITCH YOU RUNNIN FOREVA
      compute_Shortest_Path();
      while (!Main.edge_update_map.isEmpty()){
          check_For_Edge_Change();
          compute_Shortest_Path();
      }
    }
    
    public void initialize(){
       if (start_node == null || goal_node == null) {
            println("start and/or goal are null");
            return;
       }
        println("initialize run");
       U = new Priority_Queue();

       for(Node n: Main.set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
       }

       start_node.update_Rhs_Val(0);

       U.insert(start_node, calculate_Key(start_node));
    }

    public Tupple calculate_Key(Node _n){

        float k = Math.min(_n.get_G_Val(), _n.get_Rhs_Val()) + heuristic(_n,goal_node);
        if(k < 0) k = MAX_INT;

        int v = Math.min(_n.get_G_Val(), _n.get_Rhs_Val());

        return new Tupple(k,v);
    }

    public void compute_Shortest_Path(){
        println("csp run");

        while(U.top_Key().compareTo(calculate_Key(goal_node)) < 0 || goal_node.get_Rhs_Val() != goal_node.get_G_Val() || !U.is_empty()){
            Node n = U.pop();

            if (n.get_G_Val() > n.get_Rhs_Val()){

                n.update_G_Val(n.get_Rhs_Val());

                for (Edge e: n.get_Connected()){
                    Node other_node = e.get_From();
                    if (e.get_From() == n) other_node = e.get_To();
                    update_Vertex(other_node);
                }

            } else {
                n.update_G_Val(MAX_INT);

                for (Edge e: n.get_Connected()){
                    Node other_node = e.get_From();
                    if (e.get_From() == n) other_node = e.get_To();
                    update_Vertex(other_node);
                }

                update_Vertex(n);
            }
        }
    }
    //traverse from goal to start

    /**
     * Finds the shortest path from the goal node to the goal node
     * @param n the goal node
     * @return ArrayList
     */
    public ArrayList<Node> get_Shortest_Path(Node n){
        Node tmp = n;
        if (n.get_G_Val() != MAX_INT) {
            ArrayList<Node> result = new ArrayList<>();
            while (!result.contains(start_node)) {
                Node tmp2 = find_Min_G_Node(tmp);
                result.add(tmp);
                tmp = tmp2;
            }
            return result;
        }
        return null;
    }



    public void update_Vertex(Node _n){
        if (_n != start_node){
            _n.update_Rhs_Val(find_Min_G(_n));
        }
        if (U.contains(_n)){
            U.remove(_n);
        }
        if(_n.get_G_Val() != _n.get_Rhs_Val()){
            U.insert(_n,calculate_Key(_n));
        }

    }

    public Priority_Queue get_U(){
        return U;
    }

    void check_For_Edge_Change(){
        if (!Main.edge_update_map.isEmpty()) {
            for (Edge e : Main.edge_update_map.keySet()) {
                if (Main.edge_update_map.get(e) != -1) {
                    e.update_Weight(Main.edge_update_map.get(e));
                }
                update_Vertex(e.get_To());
                update_Vertex(e.get_From());
            }
            Main.edge_update_map = new HashMap<>();
        }
    }


}
