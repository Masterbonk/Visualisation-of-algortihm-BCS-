package org.algorithm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;


import static org.junit.jupiter.api.Assertions.*;

class Priority_QueueTest extends PApplet {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {

        org.algorithm.Priority_Queue pq = new Priority_Queue();

        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);
        Node c = new Node(this, 3,3);


        Key a1 = new Key(a, 20, 5);
        Key b1 = new Key(b, 30, 2);
        Key c1 = new Key(c, 20, 2);

        pq.insert(b1);
        pq.insert(c1);
        pq.insert(a1);


        assertEquals(3, pq.size());
    }

    @Test
    void insert_2() {

        org.algorithm.Priority_Queue pq = new Priority_Queue();

        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);
        Node c = new Node(this, 3,3);


        Key a1 = new Key(a, 20, 5);
        Key b1 = new Key(b, 30, 2);
        Key c1 = new Key(c, 20, 2);

        pq.insert(b1);
        pq.insert(c1);
        pq.insert(a1);


        assertEquals(c1, pq.pop());
    }

    @Test
    void pop() {

        org.algorithm.Priority_Queue pq = new Priority_Queue();

        Node a = new Node(this, 0,0);
        Node b = new Node(this, 2,2);
        Node c = new Node(this, 3,3);


        Key a1 = new Key(a, 20, 5);
        Key b1 = new Key(b, 30, 2);
        Key c1 = new Key(c, 20, 2);

        pq.insert(b1);
        pq.insert(c1);
        pq.pop();


        assertEquals(1, pq.size());
    }

    @Test
    void size() {
    }

    @Test
    void is_empty() {
    }
}