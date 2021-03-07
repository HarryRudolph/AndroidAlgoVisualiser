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

/**
 * A class for the BFS activity. Runs breadth first search algorithm and adjusts data within
 * GraphView.
 */
public class BFSActivity extends AppCompatActivity {
    private GraphView mGraphView; //Graph view from activity

    private TextView queueDisplay; //Text view from activity
    private String queueInfo; //String to hold data to be rendered in queueDisplay

    private boolean finished = false; //True if search has finished.

    private int startPos; //Start position for BFS
    private int endPos; //End position for BFS
    private Graph g; //Graph object
    private int[][] adjacencyMatrix; //Matrix holding all graph connections.
    private boolean[] visited; //Array to hold nodes that have been visited
    private ArrayList<Integer> q = new ArrayList<Integer>(); //Queue datastructure.

    /**
     * A method that is called when activity is created. Initialises data structures,
     * and waits for button press.
     * @param savedInstanceState null as no saved state is provided
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_f_s);
        setTitle("Breadth First Search");

        //Get intention passed from InfoActivity
        Intent recIntent = getIntent();
        startPos = recIntent.getIntExtra("startPos", 0);
        endPos = recIntent.getIntExtra("endPos",24);

        //Create new Graph object and get dummy graph.
        g = new Graph();
        g.makeDummyGraph();
        adjacencyMatrix = g.getMatrix();

        visited = new boolean[adjacencyMatrix.length]; //Initialise visited array. True if node has been visited.

        queueDisplay = findViewById(R.id.queueDisplay);
        mGraphView = findViewById(R.id.bfsGraphView);
        mGraphView.generateEdgeCoordinates(g.getMatrix());

        //Setting the start and end nodes to be different colours
        mGraphView.setColor(startPos, 2);
        mGraphView.setColor(endPos, 2);

        q.add(startPos); //Add the starting position to the queue.

        updateQueueInfo();

        Button nextStepButton = findViewById(R.id.nextStepButton);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            /**
             * A method to wait for the user to press the nextStepButton
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                //Don't want user to call method if searching has finished
                if (!finished){
                    //Run BFS
                    BFS();
                    updateQueueInfo();
                } else{
                    //If finished display Toast message.
                    Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * A method to run a breadth first search algorithm
     */
    private void BFS(){
        if (!q.isEmpty()) {
            int current = q.get(0);
            q.remove(0);
            mGraphView.setColor(current, 1); //Set colour of node as working through.

            //Check to see if have found end point
            if (current == endPos) {
                finished = true;
            }

            for (int i = 0; i < adjacencyMatrix.length; i++) { //adding all neighbours to queue
                if (adjacencyMatrix[current][i] == 1 && visited[i] == false) {
                    //Have found a neighbour that has not been visited.
                    q.add(i);
                }
            }

            visited[current] = true;
        }
    }

    /**
     * A method to update the the queueDisplay TextView.
     */
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