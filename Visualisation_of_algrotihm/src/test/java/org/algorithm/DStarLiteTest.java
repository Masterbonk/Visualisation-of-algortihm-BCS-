package org.algorithm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DStarLiteTest extends PApplet {
    DStarLite algorithm;

    @BeforeEach
    void setUp() {
        String[] processingArgs = {"Main"};
        Main main = new Main();
        PApplet.runSketch(processingArgs, main);
        algorithm = new DStarLite();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void d_Main() {

    }

    @Test
    void priority_Queue(){

        PriorityQueue<Key> pq = new PriorityQueue<>();

        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);
        Node c = new Node(this, 3,3);


        Key a1 = new Key(a, 20, 5);
        Key b1 = new Key(b, 30, 2);
        Key c1 = new Key(c, 20, 2);

        pq.add(a1);
        pq.add(b1);
        pq.add(c1);

        assertEquals(c1, pq.peek());
    }

    @Test
    void compute_Shortest_Path() {

    }

    //added to the queue
    @Test
    void update_Vertex() {
        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);

        Edge ab = new Edge(this,a,b,3);

        a.update_G_Val(3);
        a.update_Rhs_Val(4);

        update_Vertex(); //a

        //Test that the key is added to priority queue, with the correct key
        //Test the correct rhs value is calculated
        assertEquals(5,a.get_Rhs_Val());

    }

    //not added to the queue
    @Test
    void update_Vertex_Locally_Consistent() {
        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);

        Edge ab = new Edge(this,a,b,3);

        a.update_G_Val(4);
        a.update_Rhs_Val(4);

        b.update_Rhs_Val(5);
        b.update_G_Val(5);

        update_Vertex(); //a

        //Test that the key is not added to priority queue
        //Test the correct rhs value is calculated
        assertEquals(8,a.get_Rhs_Val());

    }

    @Test
    void update_Vertex_Calculate_Best_Rhs(){

    }

    @Test
    void calculate_Key_base_function_test() {

        algorithm.start = new Node(this, 0, 5);

        Node a = new Node(this, 5, 5);
        a.g = 100;
        a.rhs = 100;

        Key result = algorithm.calculate_Key(a);

        Key expected_result = new Key(a, 105, 100);

        assertEquals(expected_result, result);
    }

    @Test
    void calculate_Key_pick_lowest_rhs_g() {

        algorithm.start = new Node(this, 0, 5);

        Node a = new Node(this, 5, 5);
        a.g = 7;
        a.rhs = 100;

        Key result = algorithm.calculate_Key(a);

        Key expected_result = new Key(a, 12, 7);

        assertEquals(expected_result, result);

        a.g = 100;
        a.rhs = 7;

        result = algorithm.calculate_Key(a);

        assertEquals(expected_result, result);
    }

    @Test
    void calculate_Key_uses_km() {

        algorithm.start = new Node(this, 0, 5);

        algorithm.km = 100;

        Node a = new Node(this, 5, 5);
        a.g = 5;
        a.rhs = 5;

        Key result = algorithm.calculate_Key(a);

        Key expected_result = new Key(a, 110, 5);

        assertEquals(expected_result, result);

    }

    @Test
    void heuristic_base(){
        algorithm.start = new Node(this, 0, 5);

        Node a = new Node(this, 5, 5);

        float result = algorithm.h(algorithm.start,a);

        float expected_result = 5f;

        assertEquals(expected_result, result);
    }

    @Test
    void heuristic_always_positive(){
        algorithm.start = new Node(this, 0, 5);

        Node a = new Node(this, 5, 5);

        float result = algorithm.h(a, algorithm.start);

        float expected_result = 5f;

        assertEquals(expected_result, result);

        result = algorithm.h(algorithm.start, a);

        assertEquals(expected_result, result);

        Node b = new Node (this, 0, -5);
        result = algorithm.h(algorithm.start, b);
        assertEquals(expected_result, result);

        b = new Node (this, -5, 0);
        result = algorithm.h(algorithm.start, b);
        assertEquals(expected_result, result);

    }

    @Test
    void heuristic_hypotenuse_cal(){
        algorithm.start = new Node(this, 0, 0);

        Node a = new Node(this, 5, 5);

        float result = algorithm.h(algorithm.start, a);

        float expected_result = 7.07f;

        assertEquals(expected_result, result);
    }

    @Test
    void initialize_base(){
        algorithm.start = new Node(this, 0, 0);
        algorithm.goal = new Node(this, 1, 1);

        algorithm.initialize();
        assertEquals(0,algorithm.km);
        assertEquals(MAX_INT,algorithm.start.get_G_Val());
        assertEquals(MAX_INT,algorithm.start.get_Rhs_Val());
        assertEquals(MAX_INT,algorithm.goal.get_G_Val());
        assertEquals(0,algorithm.goal.get_Rhs_Val());
        assertEquals(1, algorithm.U.size());
    }

    @Test
    void initialize_fail_if_no_start_and_goal_set(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            algorithm.initialize();
        });

        String expectedMessage = "Start not set!";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);


        algorithm.start = new Node(this, 0, 0);
        expectedMessage = "Goal not set!";
        actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);

    }

    @Test
    void initialize_no_values_set_before_initialize(){
        assertNull(algorithm.U);
    }
}