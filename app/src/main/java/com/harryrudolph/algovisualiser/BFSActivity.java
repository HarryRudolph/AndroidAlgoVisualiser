package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.harryrudolph.algovisualiser.algoCode.Graph;
import com.harryrudolph.algovisualiser.views.GraphView;

import java.util.ArrayList;

public class BFSActivity extends AppCompatActivity {

    private int connections;
    private GraphView mGraphView;
    private int nodes = 25;

    private boolean nextStep = false;
    private boolean finished = false;

    private int startPos;
    private int endPos;
    private Graph g;
    private int[][] graph;
    private boolean[] visited = new boolean[nodes];
    private ArrayList<Integer> q = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_f_s);

        setTitle("Breadth First Search");

        Intent recIntent = getIntent();
        startPos = recIntent.getIntExtra("startPos",0);
        endPos = recIntent.getIntExtra("endPos",24);

        g = new Graph();
        g.makeDummyGraph();
        graph = g.getMatrix();

        mGraphView = findViewById(R.id.bfsGraphView);
        mGraphView.generateEdges(g.getMatrix());

        q.add(startPos);
        mGraphView.setColor(startPos, 2);
        mGraphView.setColor(24, 2);


        Button nextStepButton = findViewById(R.id.nextStepButton);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nextStep){
                    nextStep = true;
                    BFS();
                }
            }
        });


    }

    private void BFS(){
        System.out.println("BFS was called");
        if (nextStep && !q.isEmpty() && !finished) {
            int current = q.get(q.size() - 1); //-1 as arraylist is 0 indexed
            q.remove(q.size() - 1);

            //Check to see if have visited the current node
            if (visited[current] == false) {

                //Check to see if have found end point
                if (current == endPos) {
                    finished = true;
                }

                for (int i = 0; i < graph.length; i++) { //adding all neighbours to queue
                    if (graph[current][i] == 1) {
                        //Have found a neighbour

                        mGraphView.setColor(current, 1);
                        q.add(i);

                    }
                }

                visited[current] = true;
            }
            nextStep = false;
        }
    }

}