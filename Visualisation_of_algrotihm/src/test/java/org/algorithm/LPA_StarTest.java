package org.algorithm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import static org.algorithm.Main.Ui;

import static org.algorithm.Util.find_Min_G;
import static org.algorithm.Util.heuristic;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

class LPA_StarTest {

    LPA_Star algorithm;
    PApplet sketch;


    Util util;
    int button_height = 1;

    @BeforeEach
    void setUp() {

        sketch = new PApplet();
        Main.set_of_nodes = new HashSet<>();
        Main.edge_update_map = new HashMap<>();



        algorithm = new LPA_Star();

        util = new Util(sketch,button_height);
        Main.Ui = new UI(sketch);

    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void LPA_main_base() {
        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        algorithm.start_node = S;
        algorithm.goal_node = G;

        algorithm.Main();
        assertNotEquals(MAX_INT,algorithm.goal_node.get_G_Val());
        assertNotEquals(MAX_INT,algorithm.goal_node.get_Rhs_Val());
        assertEquals(5,algorithm.goal_node.get_G_Val());

    }

    @Test
    void LPA_main_change_edge(){

        Node A = new Node(sketch, 3,2,"A");
        Node B = new Node(sketch, 1,4, "B");
        Node S = new Node(sketch, 1, 1, "S");
        Node G = new Node(sketch, 2, 5, "G");

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        algorithm.start_node = S;
        algorithm.goal_node = G;

        Main.edge_update_map.put(bg, MAX_INT);

        algorithm.Main();

        assertTrue(algorithm.get_Shortest_Path(G).contains(A));

    }



    @Test
    void initialize() {

        algorithm.start_node = new Node(sketch, 0, 0);
        algorithm.goal_node = new Node(sketch, 1, 1);

        try {
            algorithm.initialize();
        } catch (Exception e){
            fail();
        }

        assertEquals(MAX_INT,algorithm.start_node.get_G_Val());
        assertEquals(0,algorithm.start_node.get_Rhs_Val());
        assertEquals(MAX_INT,algorithm.goal_node.get_G_Val());
        assertEquals(MAX_INT,algorithm.goal_node.get_Rhs_Val());
        assertEquals(1, algorithm.U.size());

    }

    @Test
    void initialize_fail_if_no_start_and_goal_set(){

        algorithm.initialize();
        assertNull(algorithm.get_Start());
        assertNull(algorithm.get_Goal());
        assertNull(algorithm.U);
    }

    @Test
    void initialize_no_values_set_before_initialize(){
        assertNull(algorithm.U);
    }

    @Test
    void calculate_Key_base() {
        algorithm.start_node = new Node(sketch, 5, 5);
        algorithm.goal_node = new Node(sketch,0,5);

        Node a = new Node(sketch, 5, 5);
        a.update_G_Val(100);
        a.update_Rhs_Val(100);

        Tupple result = algorithm.calculate_Key(a);

        Tupple expected_result = new Tupple(105, 100);

        assertTrue(expected_result.same_Key(result));
    }

    @Test
    void calculate_Key_pick_lowest_rhs_g() {

        algorithm.start_node = new Node(sketch, 10, 5);
        algorithm.goal_node = new Node(sketch, 0, 5);

        Node a = new Node(sketch, 5, 5);
        a.update_G_Val(7);
        a.update_Rhs_Val(100);

        Tupple result = algorithm.calculate_Key(a);

        Tupple expected_result = new Tupple(12, 7);

        assertTrue(expected_result.same_Key(result));

        a.update_G_Val(100);
        a.update_Rhs_Val(7);

        result = algorithm.calculate_Key(a);

        assertTrue(expected_result.same_Key(result));
    }

    @Test
    void compute_Shortest_Path_base() {
        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        algorithm.start_node = S;
        algorithm.goal_node = G;

        algorithm.initialize();

        algorithm.compute_Shortest_Path();

        ArrayList<Node> expected_result = new ArrayList<>();
        expected_result.add(G); expected_result.add(B); expected_result.add(S);

        assertEquals(expected_result, algorithm.get_Shortest_Path(algorithm.goal_node));

    }

    @Test
    void compute_Shortest_Path_dead_end() {
        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);
        Node D = new Node(sketch, 2,3);

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        Edge gd = new BiEdge(sketch, G, B, 2);

        algorithm.start_node = S;
        algorithm.goal_node = G;

        algorithm.initialize();
        algorithm.compute_Shortest_Path();

        assertFalse(algorithm.get_Shortest_Path(algorithm.goal_node).contains(D));
    }

    // test that closest vertex is updated
    @Test
    void update_Vertex() {
        algorithm.start_node = new Node(sketch,0,0);
        algorithm.goal_node = new Node(sketch,0,0);

        Node a = new Node(sketch, 0,0);
        Node b = new Node(sketch, 2,2);

        Edge ab = new Edge(sketch,a,b,3);

        algorithm.initialize();

        a.update_Rhs_Val(MAX_INT);
        a.update_G_Val(MAX_INT);

        b.update_G_Val(2);
        b.update_Rhs_Val(2);

        algorithm.update_Vertex(a); //a

        //Test that the key is added to priority queue, with the correct key
        assertTrue(algorithm.U.contains(a));
        //Test the correct rhs value is calculated
        assertEquals(5,a.get_Rhs_Val());

    }

    //test that vertex is not updated
    @Test
    void not_Update_Vertex(){
        algorithm.start_node = new Node(sketch,0,0);
        algorithm.goal_node = new Node(sketch,0,0);

        Node a = new Node(sketch, 0,0);
        Node b = new Node(sketch, 2,2);
        Node start = new Node(sketch, 1,1);

        Edge ab = new Edge(sketch,a,b,3);
        Edge starta = new Edge(sketch,a,start,4);

        algorithm.initialize();
        a.update_G_Val(4);
        a.update_Rhs_Val(10);

        b.update_Rhs_Val(5);
        b.update_G_Val(5);

        start.update_G_Val(0);
        start.update_Rhs_Val(0);



        algorithm.update_Vertex(a);



        //Test the correct rhs value is calculated
        assertEquals(4,a.get_Rhs_Val());
        //Test that the key is not added to/in the priority queue
        assertFalse(algorithm.U.contains(a));

    }

    @Test
    void skip_Start_Update_Vertex(){
        Node a = new Node(sketch,0,0);
        Node b = new Node(sketch,5,5);
        Node c = new Node(sketch,0,10);
        Node d = new Node(sketch,2,2);

        Edge ab = new BiEdge(sketch,a,b,1);
        Edge ac = new BiEdge(sketch,a,c,1);
        Edge ad = new BiEdge(sketch,a,d,1);

        algorithm.start_node = a;
        algorithm.goal_node = new Node(sketch,25,25);

        algorithm.initialize();

        algorithm.update_Vertex(a);

        assertEquals(0,a.get_Rhs_Val());
        assertEquals(MAX_INT,a.get_G_Val());
    }



}