package org.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.algorithm.Util.heuristic;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class LPA_Star {

    Priority_Queue U;
    Node start_node;
    Node goal_node;

    LPA_Star(){
        Main.set_of_nodes = new HashSet<>();
        Main.edge_update_map = new HashMap<>();
        start_node = null;
        goal_node = null;
    }

    public void LPA_Main(){
      initialize();
      //AHHH shit i'm temporaraly running
      // NAH BITCH YOU RUNNIN FOREVA
      while (true){
          compute_Shortest_Path();
          check_For_Edge_Change();
      }
    }
    
    public void initialize(){
       if (start_node == null && goal_node == null) {
            println("start and/or goal are null");
            return;
       }
       U = new Priority_Queue();

       for(Node n: Main.set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
       }

       start_node.update_Rhs_Val(0);

       U.insert(start_node, calculate_Key(start_node));
    }

    public Tupple calculate_Key(Node _n){
        float k = Math.min(_n.get_G_Val(), _n.get_Rhs_Val()) + heuristic(_n,start_node);
        int v = Math.min(_n.get_G_Val(), _n.get_Rhs_Val());
        return new Tupple(k,v);
    }

    public void compute_Shortest_Path(){

        while(U.top_Key().compareTo(calculate_Key(goal_node)) < 0 || goal_node.get_Rhs_Val() != goal_node.get_G_Val()){

            Node n = U.pop();

            if (n.get_G_Val() > n.get_Rhs_Val()){

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
        Node tmp = n;
        if (n.get_G_Val() != MAX_INT) {
            ArrayList<Node> result = new ArrayList<>();
            while (!result.contains(Main.goal_node)) {
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
            _n.update_Rhs_Val(find_Min_G_Node(_n).get_G_Val());
        }
        if (U.contains(_n)){
            U.remove(_n);
        }
        if(_n.get_G_Val() != _n.get_Rhs_Val()){
            U.insert(_n,calculate_Key(_n));
        }

    }

    public Node get_Start(){
        return start_node;
    }

    public Node get_Goal(){
        return goal_node;
    }

    public Priority_Queue get_U(){
        return U;
    }

    private void check_For_Edge_Change(){
        if (!Main.edge_update_map.isEmpty()) {
            for (Edge e : Main.edge_update_map.keySet()) {
                if (Main.edge_update_map.get(e) != -1) {
                    e.update_Weight(Main.edge_update_map.get(e));
                }
                update_Vertex(e.to);
                update_Vertex(e.from);
            }
            Main.edge_update_map = new HashMap<>();
        }
    }
    /**
     * @return the connected node with the lowest g value
     * */
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
}
