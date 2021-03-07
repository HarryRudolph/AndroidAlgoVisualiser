package com.harryrudolph.algovisualiser.algoCode;

import java.util.ArrayList;

/**
 * A class containing code for each implemented algorithm. This is stored for reference purposes,
 * it allows for the algorithm code to be clearer and not mixed in with rendering code.
 * @author Harry Rudolph
 */
public class Algorithms {
    /**
     * A method to run a breadth first search algorithm
     * @param start the starting position within the graph
     * @param end the end position to find within the graph
     * @param g the Graph object to traverse.
     */
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


    /**
     * A method to run a depth first search algorithm
     * @param start the starting position within the graph
     * @param end the end position to find within the graph
     * @param g the Graph object to traverse.
     */
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

    /**
     * A method to run the N-Queens backtracking algorithm. This method calls itself recursively.
     * @param currentX The current x value
     * @param board The board to loop through
     * @return Whether all queens are placed in a valid position
     */
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

    /**
     * A method to check whether a queen can be placed in a specific position.
     * @param currentX current X coordinate of queen
     * @param board current board (coordinates of queens)
     * @return true if queen can be placed, false if queen cannot be placed.
     */
    private boolean checkValid(int currentX, int[] board){
        //It is only necessary to check to the left of each queen.
        //x coordinates cannot be the same due to the nature of the implementation, we are placing
        //queens one column at at time.
        for (int i = 0; i < currentX; i++){
            if (board[i] == board[currentX]){
                //Collision in the same y
                return false;
            }
        }

        int x = currentX - 1; //-1 to start searching from one square away
        int y = board[currentX] - 1; //-1 to start searching from one square away
        while (x >= 0 && y >= 0){
            if (board[x] == y){
                //Collision in upper left diagonal
                return false;
            }
            x--;
            y--;
        }

        x = currentX - 1; //-1 to start searching from one square away
        y = board[currentX] + 1; //+1 to start searching from one square away
        while (x >= 0 && y < board.length){
            if (board[x] == y){
                //Collision in lower left diagonal
                return false;
            }
            x--;
            y++;
        }
        return true;
    }
}
