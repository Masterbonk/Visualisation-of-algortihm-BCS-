package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.edges.Edge;

import java.util.HashMap;

import static org.algorithm.Util.find_Min_G_Node;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

public class D_Star_Lite_benchmarking_testing extends DStarLite {

    public void Main(boolean _step_through){
        last = start_node;
        initialize();
        compute_Shortest_Path();

        if (_step_through) {
            while (start_node != goal_node) {
                if (start_node.get_G_Val() == MAX_INT) {
                    println("No valid path to start");
                    break;
                }

                start_node = find_Min_G_Node(start_node);


                if (!edge_update_map.isEmpty()) {
                    km = km + heuristic(last, start_node);
                    last = start_node;

                    for (Edge e : edge_update_map.keySet()) {
                        e.update_Weight(edge_update_map.get(e));
                        update_Vertex(e.get_To());
                        update_Vertex(e.get_From());
                    }
                    edge_update_map = new HashMap<>();

                    compute_Shortest_Path();
                }
            }
        } else{
            do {
                if (start_node.get_G_Val() == MAX_INT) {
                    println("No valid path to start");
                    break;
                }

                if (!edge_update_map.isEmpty()) {
                    km = km + heuristic(last, start_node);
                    last = start_node;

                    for (Edge e : edge_update_map.keySet()) {
                        e.update_Weight(edge_update_map.get(e));
                        update_Vertex(e.get_To());
                        update_Vertex(e.get_From());
                    }
                    edge_update_map = new HashMap<>();

                    compute_Shortest_Path();
                }
            } while (false);
        }

    }

    @Override
    public void Main(){
        Main(true);
    }

    public void fake_Main(){
        last = start_node;
        initialize();
        compute_Shortest_Path();

        while (start_node != goal_node){

            start_node = find_Min_G_Node(start_node);


            if (!edge_update_map.isEmpty()){
                km = km + heuristic(last,start_node);
                last = start_node;

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

    public void fake_Main_That_Flips_Every_Step(){
        last = start_node;
        initialize();
        compute_Shortest_Path();

        boolean flip = true;

        while (start_node != goal_node){
            if (start_node.get_G_Val() == MAX_INT) {
                println("No valid path to start");
                break;
            }

            start_node = find_Min_G_Node(start_node);


            if (flip) {
                Util.find_Shared_Edge(Main.node_array.get(((Main.node_array.size()-1)/2)-1), Main.node_array.get(((Main.node_array.size()-1)/2))).update_Weight(MAX_INT);
                edge_update_map.put(Util.find_Shared_Edge(Main.node_array.get(((Main.node_array.size()-1)/2)-1), Main.node_array.get(((Main.node_array.size()-1)/2))), MAX_INT);

                flip = false;
            } else {
                Util.find_Shared_Edge(Main.node_array.get(((Main.node_array.size()-1)/2)-1), Main.node_array.get(((Main.node_array.size()-1)/2))).update_Weight(100);
                edge_update_map.put(Util.find_Shared_Edge(Main.node_array.get(((Main.node_array.size()-1)/2)-1), Main.node_array.get(((Main.node_array.size()-1)/2))), 100);
                flip = true;
            }

            if (!edge_update_map.isEmpty()){
                km = km + heuristic(last,start_node);
                last = start_node;

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
}
