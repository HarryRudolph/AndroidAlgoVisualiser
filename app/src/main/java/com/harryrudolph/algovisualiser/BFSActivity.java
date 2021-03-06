package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.harryrudolph.algovisualiser.algoCode.Graph;
import com.harryrudolph.algovisualiser.views.GraphView;

import java.util.ArrayList;

public class BFSActivity extends AppCompatActivity {
    private GraphView mGraphView;
    private int nodes = 25;

    TextView queueDisplay;
    private String queueInfo;

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
        startPos = recIntent.getIntExtra("startPos", 0);
        endPos = recIntent.getIntExtra("endPos",24);

        g = new Graph();
        g.makeDummyGraph();
        graph = g.getMatrix();

        queueDisplay = findViewById(R.id.queueDisplay);
        queueDisplay.setText(queueInfo);

        mGraphView = findViewById(R.id.bfsGraphView);
        mGraphView.generateEdges(g.getMatrix());

        q.add(startPos);
        mGraphView.setColor(startPos, 2);
        mGraphView.setColor(endPos, 2);
        updateQueueInfo();

        Button nextStepButton = findViewById(R.id.nextStepButton);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nextStep && !finished){
                    nextStep = true;
                    BFS();
                    updateQueueInfo();
                }
                if (finished){
                    Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void BFS(){
        if (nextStep && !q.isEmpty() && !finished) {
            int current = q.get(0);
            q.remove(0);
            mGraphView.setColor(current, 1);

            //Check to see if have found end point
            if (current == endPos) {
                finished = true;
            }

            for (int i = 0; i < graph.length; i++) { //adding all neighbours to queue
                if (graph[current][i] == 1 && visited[i] == false) {
                    //Have found a neighbour that has not been visited.
                    q.add(i);
                }
            }

            visited[current] = true;
            nextStep = false;
        }
    }

    private void updateQueueInfo(){
        queueInfo = "Queue \n" + q.toString();

        if(!finished) {
            queueInfo = queueInfo + "\nNext to visit: " +  q.get(0).toString();
        }else {
            queueInfo = queueInfo + "\nFinished";
        }

        queueDisplay.setText(queueInfo);
    }
}