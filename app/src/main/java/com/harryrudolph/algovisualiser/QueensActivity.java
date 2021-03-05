package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.harryrudolph.algovisualiser.algoCode.Graph;
import com.harryrudolph.algovisualiser.views.QueensView;

public class QueensActivity extends AppCompatActivity {

    private QueensView mQueensView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queens);

        setTitle("QueensView");

        System.out.println("we made it");
        Intent recIntent = getIntent();
        int startPos = recIntent.getIntExtra("startPos",0);


        mQueensView = findViewById(R.id.QueensView);
        mQueensView.generateBoard(8);

    }
}