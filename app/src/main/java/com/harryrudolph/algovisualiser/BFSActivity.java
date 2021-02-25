package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.harryrudolph.algovisualiser.algoCode.Graph;
import com.harryrudolph.algovisualiser.views.GraphView;

import java.util.ArrayList;

public class BFSActivity extends AppCompatActivity {

    private int connections;
    private GraphView mCustomView;
    private int nodes = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_f_s);

        setTitle("Breadth First Search");

        Intent recIntent = getIntent();
        int startPos = recIntent.getIntExtra("startPos",0);
        int endPos = recIntent.getIntExtra("endPos",24);


    }

    private void BFS(int start, int end){
        Graph g = new Graph(nodes);
        g.makeDummyGraph();

        int[][] graph = g.getMatrix();

        boolean[] visited = new boolean[nodes];
        ArrayList<Integer> q = new ArrayList<Integer>();

        q.add(start);

        while (!q.isEmpty()) {
            int current = q.get(q.size() - 1); //-1 as arraylist is 0 indexed
            q.remove(q.size() - 1);

            //Check to see if have visited the current node
            if (visited[current] == false){

                //Check to see if have found end point
                if (current == end) {
                    break;
                }

                for (int i = 0; i < graph.length; i++){ //adding all neighbours to queue
                    if (graph[current][i] == 1){
                        //Have found a neighbour
                        q.add(i);
                    }
                }

                visited[current] = true;
            }
        }
    }
}