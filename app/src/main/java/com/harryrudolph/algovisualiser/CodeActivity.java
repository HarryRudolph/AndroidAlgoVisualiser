package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CodeActivity extends AppCompatActivity {

    String algoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);


        Intent recIntent = getIntent();
        algoName = recIntent.getStringExtra("type");

        TextView code = findViewById(R.id.codeTextView);

        switch (algoName){
            case "bfs":
                code.setText(R.string.BFSCode);
                setTitle("BFS Code");
                break;
            case "dfs":
                code.setText(R.string.DFSCode);
                setTitle("DFS Code");
                break;
            case "queen":
                code.setText(R.string.NQUEENSCode);
                setTitle("NQUEENS Code");
                break;
        }

    }
}