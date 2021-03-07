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

    private boolean recursionQueen(int currentX, int[] board){
        if (currentX == board.length){
            return true;
        }

        for(int i = 0; i < board.length; i++) {

            board[currentX] = i;
            if (checkValid(currentX, board)) {
                if (recursionQueen(currentX + 1, board)) {
                    return true;
                }
            }
        }

        board[currentX] = -1;
        return false;
    }

    private boolean checkValid(int currentX, int[] board){
        //only have to check to left of each queen
        //X coordinates will never be the same due to the nature of the solution. We are placing
        //queens one column at at time
        for (int i = 0; i < currentX; i++){
            if (board[i] == board[currentX]){
                //We have a match in x dimension
                return false;
            }
        }

        int x = currentX -1;
        int y = board[currentX] -1;
        while (x >= 0 && y >= 0){
            if (board[x] == y){
                //collision in upper left diagonal
                return false;
            }
            x--;
            y--;
        }

        x = currentX -1;
        y = board[currentX]+1;
        while (x >= 0 && y < board.length){
            if (board[x] == y){
                //collision in lower left diagonal
                return false;
            }
            x--;
            y++;
        }
        return true;
    }
}
