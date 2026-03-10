package org.algorithm;

import java.util.ArrayList;

public class Visual_LPA extends LPA_Star{
    public boolean part_one_d_main = true; // delete sometime soon

    Visual_LPA(){
        super();
    }


    public void initialize(){
        super.initialize();

    }

    public void Main(){
        if (first_run && this.start_node != null && this.goal_node != null) {

            initialize();
            first_run = false;

        }



        //while (!Main.Ui.get_Button("pause").clicked) {

            while (!Main.edge_update_map.isEmpty()) {
                super.check_For_Edge_Change();

                for (Edge e:Main.edge_array) {
                    e.color(75,75,75);
                }

            }

            compute_Shortest_Path();

            ArrayList<Node> traveres_edges = super.get_Shortest_Path(this.goal_node);
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
        this.goal_node = _n;
    }

    public void set_Start(Node _n){
        this.start_node = _n;
    }

    public void compute_Shortest_Path(){
        super.compute_Shortest_Path();


    }

    public ArrayList<Node> get_Shortest_Path(Node n){
        return super.get_Shortest_Path(n);
    }


    }
