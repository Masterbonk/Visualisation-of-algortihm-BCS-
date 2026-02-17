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

        a.update_G_Val(a,3);
        a.update_Rhs_Val(a,4);

        update_Vertex(); //a

        //Test that the key is added to priority queue, with the correct key
        //Test the correct rhs value is calculated
        assertEquals(5,a.get_Rhs_Val(a));

    }

    //not added to the queue
    @Test
    void update_Vertex_Locally_Consistent() {
        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);

        Edge ab = new Edge(this,a,b,3);

        a.update_G_Val(a,4);
        a.update_Rhs_Val(a,4);

        b.update_Rhs_Val(b, 5);
        b.update_G_Val(b,5);

        update_Vertex(); //a

        //Test that the key is not added to priority queue
        //Test the correct rhs value is calculated
        assertEquals(8,a.get_Rhs_Val(a));

    }

    @Test
    void update_Vertex_Calculate_Best_Rhs(){

    }

    @Test
    void calculate_Key() {

        algorithm.start = new Node(this, 0, 5);

        Node a = new Node(this, 5, 5);
        a.g = 100;
        a.rhs = 100;
        Node b = new Node(this, 5, 10);
        b.g = 8;
        b.rhs = 8;
        Node c = new Node(this, 11, 5);
        c.g = 8;
        c.rhs = 8;

        Edge ab = new BiEdge(this, a, b, 5);
        Edge bc = new BiEdge(this, b, c, 6);

        Key result = algorithm.calculate_Key(a);

        Key expected_result = new Key(a, 18, 13);

        assertEquals(expected_result, result);



    }
}