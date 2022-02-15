package com.example.symbio.ui.main;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class bfsTest {
    bfs g = new bfs( 0, 4,11); // first and second parameters are
    // the lowest and highest index in a graph
    public void BuildEdges() {
        //add connection Edges for testing
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 6);
        g.addEdge(2, 4);
        g.addEdge(5, 8);
        g.addEdge(6, 10);
        g.addEdge(7, 10);
        g.addEdge(8, 10);
        g.addEdge(9, 10);
    }

    @Test
    public  void getPath() {
        //Building the graph nodes
        BuildEdges();

        //call method to get the path
        g.makePath(3,9);
        //Assign expected outputs
        ArrayList <Integer> expected = new ArrayList<>();
        expected.add(9);
        expected.add(10);
        expected.add(6);
        expected.add(1);
        expected.add(0);
        expected.add(3);
        //Assigning output
        ArrayList<Integer> output= g.getPath();
        //Test equality between expected output and actual output
        assertArrayEquals(expected.toArray(),output.toArray());


    }

    @Test
    public  void getPath2() {
        //Building the graph nodes
        BuildEdges();
        g.makePath(10,0);
        //call method to get the path
        ArrayList <Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(6);
        expected.add(10);
        //Assigning output
        ArrayList<Integer> output= g.getPath();

        //Test equality between expected output and actual output
        assertArrayEquals(expected.toArray(),output.toArray());
    }

    @Test
    public  void getPath3() {
        //Building the graph nodes
        BuildEdges();
        g.makePath(8,2);
        //call method to get the path
        ArrayList <Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(4);
        expected.add(1);
        expected.add(0);
        expected.add(5);
        expected.add(8);
        //Assigning output
        ArrayList<Integer> output= g.getPath();
        //Test equality between expected output and actual output
        assertArrayEquals(expected.toArray(),output.toArray());
    }
    @Test
    public  void getPath4() {
        //Building the graph nodes
        BuildEdges();
        g.makePath(0,3);
        //call method to get the path
        ArrayList <Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(0);
        //Assigning output
        ArrayList<Integer> output= g.getPath();
        //Test equality between expected output and actual output
        assertArrayEquals(expected.toArray(),output.toArray());
    }
}