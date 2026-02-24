package org.algorithm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;


import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static org.algorithm.Main.Ui;
import static org.algorithm.Util.heuristic;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static processing.core.PApplet.println;
import static processing.core.PConstants.MAX_INT;

class DStarLiteTest {
    DStarLite algorithm;
    PApplet sketch;


    Util util;
    int button_height = 1;

    @BeforeEach
    void setUp() {
        sketch = new PApplet();


        algorithm = new DStarLite();

        util = new Util(sketch,button_height);
        Main.Ui = new UI(sketch);
        Ui.add_Button("pause", (sketch.displayWidth)/9f, sketch.displayHeight-button_height, sketch.displayWidth/9f, button_height,"⏯", Pause_Button.class, true);
        Ui.add_Button("forward", sketch.displayWidth/9f*2f, sketch.displayHeight-button_height, sketch.displayWidth/9f, button_height,"⏵", Forward_Button.class, true);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void d_Main_base() {
        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        algorithm.start = S;
        algorithm.goal = G;
        Ui.get_Button("pause").click();
        algorithm.D_Main();

        assertEquals(0,algorithm.km);
        assertEquals(algorithm.goal,algorithm.start);
    }

    @Test
    void d_Main_timeout() {
        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);

        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        algorithm.start = S;
        algorithm.goal = G;

        algorithm.D_Main();

        assertEquals(MAX_INT, algorithm.start.get_G_Val());
    }

    @Test
    void d_main_change_edge(){

        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        algorithm.start = S;
        algorithm.goal = G;

        Main.edge_update_map.put(bg,MAX_INT);

        Ui.get_Button("pause").click();

        algorithm.D_Main();

        assertTrue(algorithm.get_Shortest_Path(S).contains(A));
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

        algorithm.start = S;
        algorithm.goal = G;

        algorithm.initialize();

        algorithm.compute_Shortest_Path();

        ArrayList<Node> expected_result = new ArrayList<>();
        expected_result.add(S); expected_result.add(B); expected_result.add(G);
        
        assertEquals(expected_result, algorithm.get_Shortest_Path(algorithm.start));

    }

    @Test
    void compute_Shortest_Path_limit_scope() {
        Node A = new Node(sketch, 3,2);
        Node B = new Node(sketch, 1,4);
        Node S = new Node(sketch, 1, 1);
        Node G = new Node(sketch, 2, 5);
        Node C = new Node(sketch, 3,-4);

        Edge sa = new BiEdge(sketch,S,A, 3);
        Edge sb = new BiEdge(sketch,S,B, 3);
        Edge bg = new BiEdge(sketch,B,G, 2);
        Edge ag = new BiEdge(sketch,A,G, 4);

        Edge bc = new BiEdge(sketch, B, C, 9);

        algorithm.start = S;
        algorithm.goal = G;

        algorithm.initialize();

        algorithm.compute_Shortest_Path();

        assertEquals(MAX_INT, C.get_G_Val());
        assertEquals(11, C.get_Rhs_Val());

        assertEquals(MAX_INT, A.get_G_Val());
        assertEquals(4, A.get_Rhs_Val());
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

        algorithm.start = S;
        algorithm.goal = G;

        algorithm.initialize();

        algorithm.compute_Shortest_Path();

        assertFalse(algorithm.get_Shortest_Path(algorithm.start).contains(D));
    }

    /***
     * This test the core functionality of the update vertex function
     * specifically updates the rhs value of a
     * @assert that the rhs value is correct
     * and that the node a is inserted into the priority queue
     */
    @Test
    void update_Vertex() {
        algorithm.start = new Node(sketch,0,0);
        algorithm.goal = new Node(sketch,0,0);
        try {
            algorithm.initialize();
        } catch (Exception e) {
            fail();
        }
        Node a = new Node(sketch, 0,0);
        Node b = new Node(sketch, 2,2);

        Edge ab = new Edge(sketch,a,b,3);

        a.rhs = MAX_INT;
        a.g = MAX_INT;

        b.update_G_Val(2);
        b.update_Rhs_Val(2);

        algorithm.update_Vertex(a); //a

        //Test that the key is added to priority queue, with the correct key
        assertTrue(algorithm.U.contains(a));
        //Test the correct rhs value is calculated
        assertEquals(5,a.get_Rhs_Val());

    }

    /***
     * This test the core functionality of the update vertex function
     * specifically updates the rhs value of a
     * @assert that the rhs value is correct
     * and that the node a is NOT inserted into the priority queue
     */
    @Test
    void update_Vertex_Locally_Consistent() {
        algorithm.start = new Node(sketch,0,0);
        algorithm.goal = new Node(sketch,0,0);
        try {
            algorithm.initialize();
        } catch (Exception e) {
            fail();
        }
        Node a = new Node(sketch, 0,0);
        Node b = new Node(sketch, 2,2);
        Node start = new Node(sketch, 1,1);

        Edge ab = new Edge(sketch,a,b,3);
        Edge starta = new Edge(sketch,a,start,4);

        a.update_G_Val(4);
        a.update_Rhs_Val(10);

        b.update_Rhs_Val(5);
        b.update_G_Val(5);

        start.update_G_Val(0);
        start.update_Rhs_Val(0);

        algorithm.update_Vertex(a);

        //Test that the key is not added to/in the priority queue
        assertFalse(algorithm.U.contains(a));
        //Test the correct rhs value is calculated
        assertEquals(4,a.get_Rhs_Val());

    }

    /***
     * This test the functionality of the update vertex function on a bigger graph
     * with more edges and nodes with different values
     * @assert that the rhs value is correct
     * and that the node a is inserted into the priority queue
     */
    @Test
    void update_Vertex_Calculate_Best_Rhs(){
        algorithm.start = new Node(sketch,0,0);
        algorithm.goal = new Node(sketch,0,0);

        Node start = new Node(sketch,1,1);
        Node a = new Node(sketch,1,1);
        Node b = new Node(sketch,1,1);
        Node c = new Node(sketch,1,1);
        Node d = new Node(sketch,1,1);
        Node e = new Node(sketch,1,1);
        Node g = new Node(sketch,1,1);

        try {
            algorithm.initialize();
        } catch (Exception exc) {
            assertTrue(false);
        }
        start.rhs = 0;
        start.g = 0;

        e.rhs = 3;
        e.g = 3;

        g.rhs = 2;
        g.g = 2;

        Edge startE = new BiEdge(sketch,start,e,3);
        Edge startG = new BiEdge(sketch,start,g,2);
        Edge startA = new BiEdge(sketch,start,a,10);

        Edge ga = new BiEdge(sketch, g,a,5);
        Edge ea = new BiEdge(sketch,e,a,3);

        Edge ab = new BiEdge(sketch,a,b,3);
        Edge ad = new BiEdge(sketch,a,d,10);
        Edge ac = new BiEdge(sketch,a,c,1);

        algorithm.update_Vertex(a); 

        //Test that the key is added to priority queue
        //Test the correct rhs value is calculated
        assertEquals(6,a.get_Rhs_Val());
    }

    /** This test checks the base functionality of the calculate_Key function.
     * It checks whether the calculations are able to find the heuristic value of 5,
     * alongside the g and rhs values
     * @assert That the key is the expected one
     */
    @Test
    void calculate_Key_base() {

        algorithm.start = new Node(sketch, 0, 5);

        Node a = new Node(sketch, 5, 5);
        a.g = 100;
        a.rhs = 100;

        Tupple result = algorithm.calculate_Key(a);

        Tupple expected_result = new Tupple(105, 100);

        assertTrue(expected_result.same_Key(result));
    }

    /**
     * Checks that calculate key always picks the lowest of g and rhs
     *
     * @assert that the key is built on the lower value of g or rhs in it's calculations.
     */
    @Test
    void calculate_Key_pick_lowest_rhs_g() {

        algorithm.start = new Node(sketch, 0, 5);

        Node a = new Node(sketch, 5, 5);
        a.g = 7;
        a.rhs = 100;

        Tupple result = algorithm.calculate_Key(a);

        Tupple expected_result = new Tupple(12, 7);

        assertTrue(expected_result.same_Key(result));

        a.g = 100;
        a.rhs = 7;

        result = algorithm.calculate_Key(a);

        assertTrue(expected_result.same_Key(result));
    }

    /**
     * Checks that the calculate key uses the km value when calculating
     *
     * @assert That the expected result builds on the algorithm km value which is artificially put at 100
     */

    @Test
    void calculate_Key_uses_km() {

        algorithm.start = new Node(sketch, 0, 5);

        algorithm.km = 100;

        Node a = new Node(sketch, 5, 5);
        a.g = 5;
        a.rhs = 5;

        Tupple result = algorithm.calculate_Key(a);

        Tupple expected_result = new Tupple(110, 5);

        assertTrue(expected_result.same_Key(result));

    }

    /**
     * Checks that the heuristic function is able to perform it's base functionality.
     * This means that it can take two nodes and find the length between the two nodes.
     *
     * @assert That the distance between a node at 0,5 and 5,5 is 5.
     */
    @Test
    void heuristic_base(){
        algorithm.start = new Node(sketch, 0, 5);

        Node a = new Node(sketch, 5, 5);

        float result = heuristic(algorithm.start,a);

        float expected_result = 5f;

        assertEquals(expected_result, result);
    }

    /**
     * Checks that the heuristic function always give a positive number back,
     * even if you take nodes in weird orders or place them in negative positions.
     *
     * @assert That we always get a positive value back if we have the following call
     * h([0,5],[5,5])
     */
    @Test
    void heuristic_always_positive(){
        algorithm.start = new Node(sketch, 0, 5);

        Node a = new Node(sketch, 5, 5);

        float result = heuristic(a, algorithm.start);

        float expected_result = 5f;

        assertEquals(expected_result, result);
    }

    /**
     * Checks that the heuristic function always give a positive number back,
     * even if you take nodes in weird orders or place them in negative positions.
     *
     * @assert That we always get a positive value back if we have the following call
     * h([5,5],[0,5])
     */
    @Test
    void heuristic_always_positive2(){
        algorithm.start = new Node(sketch, 0, 5);

        Node a = new Node(sketch, 5, 5);

        float expected_result = 5f;

        float result = heuristic(algorithm.start, a);

        assertEquals(expected_result, result);
    }

    /**
     * Checks that the heuristic function always give a positive number back,
     * even if you take nodes in weird orders or place them in negative positions.
     *
     * @assert That we always get a positive value back if we have the following call
     * h([0,5],[0,-5])
     */
    @Test
    void heuristic_always_positive3(){
        algorithm.start = new Node(sketch, 0, 5);

        Node b = new Node (sketch, 0, -5);
        float result = heuristic(algorithm.start, b);
        float expected_result = 10;
        assertEquals(expected_result, result);

        expected_result = 5;
        b = new Node (sketch, -5, 5);
        result = heuristic(algorithm.start, b);
        assertEquals(expected_result, result);
    }

    /**
     * Checks that the heuristic function always give a positive number back,
     * even if you take nodes in weird orders or place them in negative positions.
     *
     * @assert That we always get a positive value back if we have the following call
     * h([0,5],[-5,0])
     */

    @Test
    void heuristic_always_positive4(){
        algorithm.start = new Node(sketch, 0, 5);

        float expected_result = 5;
        Node b = new Node (sketch, -5, 5);
        float result = heuristic(algorithm.start, b);
        assertEquals(expected_result, result);
    }

    /**
     * Checks that it can calculate the right value within 2 decimals,
     * specifically when it has to calculate in regards to angles where the value won't be a solid one.
     *
     * @assert That two points a (0,0) and b (5,5) will result in a heuristic value of 7.07
     */

    @Test
    void heuristic_hypotenuse_cal(){
        algorithm.start = new Node(sketch, 0, 0);

        Node a = new Node(sketch, 5, 5);

        float result = heuristic(algorithm.start, a);

        float expected_result = 7.07f;

        assertEquals(expected_result, result);
    }

    /**
     * The base initialize test, that the function sets up the envioment for the algorithm to run.
     *
     * Before it does so, it specifies the start and goal node.
     * @assert That km = 0, that start.g = MAX_INT, that start.rhs = MAX_INT, that goal.g = MAX_INT,
     * that goal.rhs = 0 and that the size of the queue is 1.
     */
    @Test
    void initialize_base(){
        algorithm.start = new Node(sketch, 0, 0);
        algorithm.goal = new Node(sketch, 1, 1);

        try {
            algorithm.initialize();
        } catch (Exception e){
            assertTrue(false);
        }
        assertEquals(0,algorithm.km);
        assertEquals(MAX_INT,algorithm.start.get_G_Val());
        assertEquals(MAX_INT,algorithm.start.get_Rhs_Val());
        assertEquals(MAX_INT,algorithm.goal.get_G_Val());
        assertEquals(0,algorithm.goal.get_Rhs_Val());
        assertEquals(1, algorithm.U.size());
    }

    /**
     * Checks that the initialize code fails if the start and goal is null.
     *
     * @assert checks first if we get the start null exception, then the goal null exception.
     */
    @Test
    void initialize_fail_if_no_start_and_goal_set(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            algorithm.initialize();
        });

        String expectedMessage = "Start not set!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);


        algorithm.start = new Node(sketch, 0, 0);
        expectedMessage = "Goal not set!";
        exception = assertThrows(NullPointerException.class, () -> {
            algorithm.initialize();
        });
        actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

    }



    /**
     * Checks that our queue is null before we call initialize.
     */
    @Test
    void initialize_no_values_set_before_initialize(){
        assertNull(algorithm.U);
    }
}