package org.algorithm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;


import static org.junit.jupiter.api.Assertions.*;

class Priority_QueueTest {

    Node a;
    Node b;
    Node c;

    Key a1;
    Key b1;
    Key c1;

    PApplet sketch;

    org.algorithm.Priority_Queue pq;


    /** Setup nodes & keys to use for each test
     * */
    @BeforeEach
    void setUp() {
        sketch = new PApplet();

        pq = new Priority_Queue();

        Node a = new Node(sketch, 0,0);
        Node b = new Node(sketch, 2,2);
        Node c = new Node(sketch, 3,3);


        a1 = new Key(a, 20, 5);
        b1 = new Key(b, 30, 2);
        c1 = new Key(c, 20, 2);

    }

    @AfterEach
    void tearDown() {

    }

    /** Test that the priorityqueue exist, and can be updated
     * @assert test that there is a pq
     * */
    @Test
    void insert() {


        pq.insert(b1);
        pq.insert(c1);
        pq.insert(a1);


        assertEquals(3, pq.size());
    }

    /** test that pop & insert works
     * */
    @Test
    void insert_2() {


        pq.insert(b1);
        pq.insert(c1);
        pq.insert(a1);


        assertEquals(c1, pq.pop());
        assertEquals(2, pq.size());
    }

    /** test that pop & insert works
     * */
    @Test
    void insert_3() {


        pq.insert(c1);
        pq.insert(b1);
        pq.insert(a1);


        assertEquals(c1, pq.pop());
        assertEquals(2, pq.size());
    }

    /** test that pop & insert works
     * */
    @Test
    void insert_4() {


        pq.insert(a1);
        pq.insert(b1);
        pq.insert(c1);


        assertEquals(c1, pq.pop());
        assertEquals(2, pq.size());
    }

    /** test that pop & insert works
     * */
    @Test
    void insert_5() {


        pq.insert(a1);
        pq.insert(c1);
        pq.insert(b1);


        assertEquals(c1, pq.pop());
        assertEquals(2, pq.size());
    }

    /** test that pop works
     * */
    @Test
    void popTest() {

        a1 = new Key(a, 20, 5);
        b1 = new Key(b, 30, 2);
        c1 = new Key(c, 20, 2);

        pq.insert(b1);
        pq.insert(c1);
        pq.pop();


        assertEquals(1, pq.size());
    }

    @Test
    void top_Key_base(){

        pq.insert(b1);
        pq.insert(c1);
        pq.insert(a1);


        assertEquals(c1, pq.top_Key());
    }

    /** test that pop gives null if the queue is empty when popping
     * */
    @Test
    void popTest_fails_if_empty() {

        a1 = new Key(a, 20, 5);
        b1 = new Key(b, 30, 2);
        c1 = new Key(c, 20, 2);

        pq.insert(b1);
        pq.insert(c1);
        pq.pop();
        pq.pop();
        assertNull(pq.pop());
    }

    /** Test that size work
     * */
    @Test
    void size() {

        a1 = new Key(a, 20, 5);
        b1 = new Key(b, 30, 2);
        c1 = new Key(c, 20, 2);
        pq.insert(a1);
        pq.insert(b1);
        pq.insert(c1);

        assertEquals(3, pq.size());
    }

    /** test that empty func works
     * */
    @Test
    void is_empty() {
        org.algorithm.Priority_Queue pq = new Priority_Queue();
        assertEquals(true, pq.is_empty());
    }
}