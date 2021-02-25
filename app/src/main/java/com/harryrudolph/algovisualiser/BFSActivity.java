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

        Graph g = new Graph(nodes);
        g.makeDummyGraph();
    }

    private void BFS(int start, int end){
        boolean[] visited = new boolean[nodes];
        ArrayList<Integer> q = new ArrayList<Integer>();


    }
}