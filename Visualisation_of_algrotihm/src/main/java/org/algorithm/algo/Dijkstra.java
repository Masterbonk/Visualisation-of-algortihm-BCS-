package org.algorithm.algo;


import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;

import static org.algorithm.Main.node_array;
import static processing.core.PConstants.MAX_INT;

//Source: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
public class Dijkstra {
    public HashMap<Node, Integer> dist;
    public HashMap<Node, Node> prev;
    public Dijkstra_Priority_Queue Q;

    public Node source;
    public Node target;



    public Dijkstra(Node _source, Node _target){
        source = _source;
        target = _target;

        dist = new HashMap<>();
        prev = new HashMap<>();
        Q = new Dijkstra_Priority_Queue();

    }

    public void Main(){
        initialize();
        compute_Shortest_Path();
    }

    public void initialize(){
        for (Node v: node_array) {
            if (v != source) {
                dist.put(v, MAX_INT);
                prev.put(v, null);
                Q.insert(v, MAX_INT);
            }
        }
        dist.put(source, 0);
        Q.insert(source, 0);
    }


    public void compute_Shortest_Path(){
        while(!Q.is_empty()){
            Node u = Q.pop();

            for (Edge e: u.get_Connected()){
                //Gets the other node connected to edge e
                Node v = e.get_From();
                if (v == u) v = e.get_To();

                int alt = dist.get(u) + e.get_Weight();

                if (alt < dist.get(v)){
                    prev.put(v, u);
                    dist.put(v, alt);
                    Q.remove(v);
                    Q.insert(v, alt);
                }
            }
        }
    }

    public ArrayList<Node> get_Shortest_Path(){
        return null;
    }



}
