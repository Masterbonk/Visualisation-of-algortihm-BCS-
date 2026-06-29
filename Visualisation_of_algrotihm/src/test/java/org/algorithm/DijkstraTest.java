package org.algorithm;

import org.algorithm.algo.Dijkstra;
import org.algorithm.graph.Node;
import org.algorithm.graph.edges.Edge;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;
import static processing.core.PConstants.MAX_INT;

class DijkstraTest {

    Dijkstra algo;

    Node source;
    Node target;
    PApplet sketch;

    @BeforeEach
    void setUp() {

        sketch = new PApplet();

        source = new Node(sketch, 0,0, true);
        target = new Node(sketch, 100, 100, true);
        //Edges & nodes connecting them
        Node A = new Node(sketch, 50, 75, true);
        Node B = new Node (sketch, 25, 75, true);
        Edge SA = new Edge(sketch, source, A, 0, true);
        Edge AT = new Edge(sketch, A, target, 0, true);
        Edge SB = new Edge(sketch, source, B, 0, true);
        Edge BT = new Edge(sketch, B, target, 0, true);

        algo = new Dijkstra(source, target);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() {
    }

    @Test
    void initialize() {
        assertTrue(algo.dist.isEmpty());
        assertTrue(algo.prev.isEmpty());
        assertTrue(algo.Q.is_empty());

        algo.initialize();

        assertFalse(algo.dist.isEmpty());
        assertFalse(algo.prev.isEmpty());
        assertFalse(algo.Q.is_empty());

        assertEquals(algo.dist.get(source), 0);
        assertEquals(algo.dist.get(target), MAX_INT);
        assertTrue(algo.Q.contains(source));
        assertTrue(algo.Q.contains(target));
    }

    @Test
    void compute_Shortest_Path() {

        algo.initialize();

        assertFalse(algo.Q.is_empty());

        algo.compute_Shortest_Path();

        assertTrue(algo.Q.is_empty());
        assertNotEquals(MAX_INT, algo.dist.get(target));
        assertEquals(0, algo.dist.get(source));
    }
}