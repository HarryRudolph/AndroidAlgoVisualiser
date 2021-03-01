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

public class DFSActivity extends AppCompatActivity {
    private GraphView mGraphView;
    private int nodes = 25;

    TextView stackDisplay;
    private String stackInfo;

    private boolean nextStep = false;
    private boolean finished = false;

    private int startPos;
    private int endPos;
    private Graph g;
    private int[][] graph;
    private boolean[] visited = new boolean[nodes];
    private ArrayList<Integer> stack = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_f_s);

        setTitle("Depth First Search");

        Intent recIntent = getIntent();
        startPos = recIntent.getIntExtra("startPos",0);
        endPos = recIntent.getIntExtra("endPos",24);

        g = new Graph();
        g.makeDummyGraph();
        graph = g.getMatrix();

        stackDisplay = findViewById(R.id.stackDisplay);
        stackDisplay.setText(stackInfo);

        mGraphView = findViewById(R.id.bfsGraphView);
        mGraphView.generateEdges(g.getMatrix());

        stack.add(startPos);
        mGraphView.setColor(startPos, 2);
        mGraphView.setColor(24, 2);
        updateStackInfo();

        Button nextStepButton = findViewById(R.id.nextStepButton);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nextStep && !finished){
                    nextStep = true;
                    DFS();
                    updateStackInfo();
                }
                if (finished){
                    Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void DFS(){
        if (nextStep && !stack.isEmpty() && !finished) {
            int top = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            mGraphView.setColor(top, 1);

            //Check to see if have found end point
            if (top == endPos) {
                finished = true;
            }

            for (int i = 0; i < graph.length; i++) { //adding all neighbours to stack
                if (graph[top][i] == 1 && visited[i] == false) {
                    //Have found a neighbour that has not been visited.
                    stack.add(i);
                }
            }

            visited[top] = true;
            nextStep = false;
        }
    }

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