package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.harryrudolph.algovisualiser.views.GraphView;

public class BFSActivity extends AppCompatActivity {

    private GraphView mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_f_s);

        setTitle("Breadth First Search");


    }
}