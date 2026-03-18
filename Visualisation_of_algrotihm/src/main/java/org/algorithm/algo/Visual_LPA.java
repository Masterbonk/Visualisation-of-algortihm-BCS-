package org.algorithm.algo;

import org.algorithm.Main;
import org.algorithm.Util;
import org.algorithm.graph.edges.Edge;
import org.algorithm.graph.Node;

import java.util.ArrayList;

public class  Visual_LPA extends LPA_Star{
    public boolean part_one_d_main = true; // delete sometime soon

    public Visual_LPA(){
        super();
    }


    public void initialize(){
        super.initialize();

    }

    public void Main(){
        if (first_run && start_node != null || goal_node != null) {
            for (Edge e:Main.edge_array) {
                e.color(75,75,75);
            }
            initialize();
            first_run = false;

        }


        //while (!Main.Ui.get_Button("pause").clicked) {

            while (!edge_update_map.isEmpty()) {
                super.check_For_Edge_Change();

            }

            compute_Shortest_Path();

            ArrayList<Node> traveres_edges = super.get_Shortest_Path(goal_node);
            if(traveres_edges == null){
                return;
            }
            for (int i = 0; i < traveres_edges.size() - 1; i++) {
                Edge e = Util.find_Shared_Edge(traveres_edges.get(i), traveres_edges.get(i + 1));
                if (e != null) e.color(-1, -1, 150);
            }
            // to here
            // pause here

            //maybe should make this a function call instead of setting a bool
            //Main.Ui.get_Button("pause").clicked = true;
        //}



    }

    public void set_Goal(Node _n){
        goal_node = _n;
    }

    public void set_Start(Node _n){
        start_node = _n;
    }

    public void compute_Shortest_Path(){
        super.compute_Shortest_Path();
    }

    }
