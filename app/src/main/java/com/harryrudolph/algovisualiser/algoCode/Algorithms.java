package com.harryrudolph.algovisualiser.algoCode;

import java.util.ArrayList;

public class Algorithms {
    private void BFS(int start, int end, Graph g) {
        int[][] graph = g.getMatrix();
        boolean[] visited = new boolean[25]; //number of nodes
        ArrayList<Integer> q = new ArrayList<Integer>();

        q.add(start);

        while (!q.isEmpty()) {
            int current = q.get(0);
            q.remove(0);

            //Check to see if have found end point
            if (current == end) {
                //Finished
            }
            for (int i = 0; i < graph.length; i++) { //adding all neighbours to queue
                if (graph[current][i] == 1 && visited[i] == false) {
                    //Have found a neighbour that has not been visited.
                    q.add(i);
                }
            }
            visited[current] = true;
        }
    }


        private void DFS(int start, int end, Graph g){
            int[][] graph = g.getMatrix();
            boolean[] visited = new boolean[25]; //number of nodes
            ArrayList<Integer> stack = new ArrayList<Integer>();

            stack.add(start);

            while (!stack.isEmpty()) {
                int top = stack.get(stack.size()-1); //-1 as arraylist is 0 indexed
                stack.remove(stack.size()-1);

                //Check to see if have found end point
                if (top == end) {
                    //Finished
                }
                for (int i = 0; i < graph.length; i++) { //adding all neighbours to stack
                    if (graph[top][i] == 1 && visited[i] == false) {
                        //Have found a neighbour that has not been visited.
                        stack.add(i);
                    }
                }
                visited[top] = true;
            }
        }
}
