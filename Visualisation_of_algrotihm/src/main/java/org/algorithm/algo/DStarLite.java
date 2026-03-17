package org.algorithm.algo;

import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.min;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class DStarLite extends Algorithm {
    Node start;
    Node goal;
    Node last;
    float km;
    Priority_Queue U;



    DStarLite(){
        super();
    }

    public void initialize(){
        if (start == null) throw new NullPointerException("Start not set!");
        if (goal == null) throw new NullPointerException("Goal not set!");


        U = new Priority_Queue();

        km = 0;

        for(Node n: set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }

        goal.update_Rhs_Val(0);

        U.insert(goal, calculate_Key(goal));
    }

    public void D_Main(){
        last = start;
        initialize();
        compute_Shortest_Path();

        while (start != goal){
            if (start.get_G_Val() == MAX_INT) {
                println("No valid path to start");
                break;
            }

            start = find_Min_G_Node(start);


            if (!edge_update_map.isEmpty()){
                km = km + heuristic(last,start);
                last = start;

                for(Edge e: edge_update_map.keySet()){
                    e.update_Weight(edge_update_map.get(e));
                    update_Vertex(e.get_To());
                    update_Vertex(e.get_From());
                }
                edge_update_map = new HashMap<>();

                compute_Shortest_Path();
            }
        }
    }

    public void compute_Shortest_Path(){
        Tupple k_old;
        Node n;
        while(U.top_Key().compareTo(calculate_Key(start)) < 0 || start.get_Rhs_Val() != start.get_G_Val()){
            k_old = U.top_Key();
            n = U.pop();
            if(k_old.compareTo(calculate_Key(n)) < 0){
                U.insert(n, calculate_Key(n));
            } else if (n.get_G_Val() > n.get_Rhs_Val()){
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

    public ArrayList<Node> get_Shortest_Path(Node n){
        return super.get_Shortest_Path(n,true);
    }

    public int find_Min_G(Node _n){
        int min = MAX_INT;
        for(Edge e: _n.get_Connected()){
            Node other_node = e.get_From();
            if (e.get_From() == _n) other_node = e.get_To();
            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.get_Weight()+other_node.get_G_Val()){
                    min = e.get_Weight()+other_node.get_G_Val();
                }
            }

        }
        return min;
    }

    public Node find_Min_G_Node(Node _n){
        int min = MAX_INT;
        Node tmp = null;
        for(Edge e: _n.get_Connected()){
            Node other_node = e.get_From();
            if (e.get_From() == _n) other_node = e.get_To();
            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.get_Weight()+other_node.get_G_Val()){
                    min = e.get_Weight()+other_node.get_G_Val();
                    tmp = other_node;
                }
            }

        }
        return tmp;
    }

    public void update_Vertex(Node _n){
        if (_n != goal){
            _n.update_Rhs_Val(find_Min_G(_n));
        }

        if(U.contains(_n)){
            try{
                U.remove(_n);
            } catch (Exception e){
                println(e.getMessage());
            }
        }

        if(_n.get_G_Val() != _n.get_Rhs_Val()){
            U.insert(_n, calculate_Key(_n));
        }
    }

    public Tupple calculate_Key(Node s){
        float k1, k2;

        k1 = min(s.get_G_Val(),s.get_Rhs_Val()) + heuristic(s, start) + km;

        k2 = min(s.get_G_Val(),s.get_Rhs_Val());

        return new Tupple(k1, k2);
    }


    /** Calculates the heuristic between Node a & b
     *  Heuristic = distance between the two points
     * */
    public float heuristic(Node a, Node b){
        return (float) (Math.round(Math.sqrt((Math.pow(a.get_X() - b.get_X(),2)) + (Math.pow(a.get_Y() - b.get_Y(),2)))* 100.0) / 100.0);
    }

    public void remove_Node(Node n){
        set_of_nodes.remove(n);

        try {
            U.remove(n);
        } catch (Exception e){
            println(e.getMessage());
        }
    }

    public void set_Start(Node _n){
        start = _n;
    }

    public void set_Goal(Node _n){
        goal = _n;
    }

    public Node get_Start(){
        return start;
    }

    public Node get_Goal(){
        return goal;
    }

}

