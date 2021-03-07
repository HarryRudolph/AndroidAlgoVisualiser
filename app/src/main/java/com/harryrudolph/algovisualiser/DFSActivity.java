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
 * A class for the DFS activity. Runs depth first search algorithm and adjusts data within
 * graph view.
 */
public class DFSActivity extends AppCompatActivity {
    private GraphView mGraphView;

    TextView stackDisplay;
    private String stackInfo;

    private boolean finished = false;

    private int startPos;
    private int endPos;
    private Graph g;
    private int[][] adjacencyMatrix;
    private boolean[] visited;
    private ArrayList<Integer> stack = new ArrayList<Integer>();

    /**
     * A method that is called when activity is created. Initialises data structures,
     * and waits for button press.
     * @param savedInstanceState null as no saved state is provided
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_f_s);
        setTitle("Depth First Search");

        //Get intention passed from InfoActivity
        Intent recIntent = getIntent();
        startPos = recIntent.getIntExtra("startPos",0);
        endPos = recIntent.getIntExtra("endPos",24);

        //Create new Graph object and get dummy graph.
        g = new Graph();
        g.makeDummyGraph();
        adjacencyMatrix = g.getMatrix();

        visited = new boolean[adjacencyMatrix.length]; //Initialise visited array. True if node has been visited.

        stackDisplay = findViewById(R.id.stackDisplay);
        mGraphView = findViewById(R.id.bfsGraphView);
        mGraphView.generateEdgeCoordinates(g.getMatrix());

        //Set colour of start and end nodes
        mGraphView.setColor(startPos, 2);
        mGraphView.setColor(endPos, 2);

        stack.add(startPos); //Add starting position to the stack.

        updateStackInfo();

        Button nextStepButton = findViewById(R.id.nextStepButton);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            /**
             * A method to wait for the user to press the nextStepButton
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                //Check if not finished before calling method
                if (!finished){
                    //Run dfs
                    DFS();
                    updateStackInfo();
                } else{
                    //If finished display Toast message
                    Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * A method to run a depth first search algorithm
     */
    private void DFS(){
        if (!stack.isEmpty()) {
            int top = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            mGraphView.setColor(top, 1); //Set colour of node as stepping through

            //Check to see if have found end point
            if (top == endPos) {
                finished = true;
            }

            for (int i = 0; i < adjacencyMatrix.length; i++) { //adding all neighbours to stack
                if (adjacencyMatrix[top][i] == 1 && visited[i] == false) {
                    //Have found a neighbour that has not been visited.
                    stack.add(i);
                }
            }

            visited[top] = true;
        }
    }

    /**
     * A method to update the stackDisplay TextView
     */
    private void updateStackInfo(){
        stackInfo = "Stack \n" + stack.toString();

        if(!finished) {
            stackInfo = stackInfo + "\nNext to visit: " +  stack.get(stack.size()-1).toString();
        }else {
            stackInfo = stackInfo + "\nFinished";
        }

        stackDisplay.setText(stackInfo);
    }

}