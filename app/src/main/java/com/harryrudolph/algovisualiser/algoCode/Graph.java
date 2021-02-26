package com.harryrudolph.algovisualiser.algoCode;

public class Graph {
    int matrix[][];

    public Graph(){
        matrix = new int[25][25];
    }

    public void addEdge(int start, int end){
        matrix[start][end] = 1;
        matrix[end][start] = 1;
    }

    public int[][] getMatrix(){
        return matrix;
    }

    public void makeDummyGraph(){
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(1, 6);
        addEdge(2, 3);
        addEdge(2, 7);
        addEdge(3, 4);
        addEdge(4, 9);
        addEdge(5, 6);
        addEdge(5, 10);
        addEdge(6, 11);
        addEdge(7, 8);
        addEdge(8, 13);
        addEdge(10, 15);
        addEdge(11, 12);
        addEdge(13, 14);
        addEdge(13, 18);
        addEdge(15, 16);
        addEdge(15, 20);
        addEdge(17, 18);
        addEdge(17, 22);
        addEdge(18, 19);
        addEdge(18, 23);
        addEdge(19, 24);
        addEdge(21, 22);

    }



}
