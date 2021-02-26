package com.harryrudolph.algovisualiser.algoCode;

import java.util.ArrayList;

public class BFS {
    private void BFS(int start, int end, Graph g){
        int[][] graph = g.getMatrix();
        boolean[] visited = new boolean[25]; //number of nodes
        ArrayList<Integer> q = new ArrayList<Integer>();

        q.add(start);

        while (!q.isEmpty()) {
                int current = q.get(0); //-1 as arraylist is 0 indexed
                q.remove(0);

                //Check to see if have visited the current node
                if (visited[current] == false) {

                    //Check to see if have found end point
                    if (current == end) {
                        break;
                    }

                    for (int i = 0; i < graph.length; i++) { //adding all neighbours to queue
                        if (graph[current][i] == 1) {
                            //Have found a neighbour
                            q.add(i);
                        }
                    }

                    visited[current] = true;
                }
            }
        }
}
