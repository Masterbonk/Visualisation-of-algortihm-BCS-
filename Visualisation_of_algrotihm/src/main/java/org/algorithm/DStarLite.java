package org.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.min;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class DStarLite {
    Node start;
    Node goal;
    Node last;
    float km;
    Priority_Queue U;
    boolean has_been_paused = false;
    boolean first_run = true;


    DStarLite(){
        Main.set_of_nodes = new HashSet<>();
        Main.edge_update_map = new HashMap<>();
    }

    public void initialize(){
        if (start == null) throw new NullPointerException("Start not set!");
        if (goal == null) throw new NullPointerException("Goal not set!");


        U = new Priority_Queue();

        km = 0;

        for(Node n: Main.set_of_nodes){
            n.update_G_Val(MAX_INT);
            n.update_Rhs_Val(MAX_INT);
        }

        goal.update_Rhs_Val(0);

        U.insert(goal, calculate_Key(goal));

    }

    public void D_Main(){
        if (first_run){
            last = start;
            initialize();
            compute_Shortest_Path();
            first_run = false;
        }
        while (start != goal){
            if (start.get_G_Val() == MAX_INT) {
                println("No valid path to start");
                break;
            }
            if (!has_been_paused) start = find_Min_G_Node(start);

            if(Main.Ui.get_Button("pause").clicked){
                has_been_paused = true;
                break;
            }

            if (!Main.edge_update_map.isEmpty()){
                km = km + heuristic(last,start);
                last = start;

                for(Edge e: Main.edge_update_map.keySet()){
                    e.update_Weight(Main.edge_update_map.get(e));
                    update_Vertex(e.to);
                    update_Vertex(e.from);
                }
                Main.edge_update_map = new HashMap<>();

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
        ArrayList<Node> result = new ArrayList<>();
        Node tmp = n;
        if (n.get_G_Val() != MAX_INT) {
            while (!result.contains(goal)) {
                Node tmp2 = find_Min_G_Node(tmp);
                result.add(tmp);
                tmp = tmp2;
            }
            return result;
        }
        return null;
    }

    public int find_Min_G(Node _n){
        int min = MAX_INT;
        for(Edge e: _n.connected){
            Node other_node = e.from;
            if (e.from == _n) other_node = e.to;
            if (other_node.get_G_Val() != MAX_INT) {
                if (min > e.weight+other_node.get_G_Val()){
                    min = e.weight+other_node.get_G_Val();
                }
            }

        }
        return min;
    }

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

        k1 = min(s.g,s.rhs) + heuristic(s, start) + km;

        k2 = min(s.g,s.rhs);

        return new Tupple(k1, k2);
    }


    /** Calculates the heuristic between Node a & b
     *  Heuristic = distance between the two points
     * */
    public float heuristic(Node a, Node b){
        return (float) (Math.round(Math.sqrt((Math.pow(a.x - b.x,2)) + (Math.pow(a.y - b.y,2)))* 100.0) / 100.0);
    }

    public void remove_Node(Node n){
        Main.set_of_nodes.remove(n);

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

