package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainButton1:
                System.out.println("button1");
                break;
            case R.id.mainButton2:
                System.out.println("button2");
                break;
            case R.id.mainButton3:
                System.out.println("button3");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton1 = findViewById(R.id.mainButton1);
        mainButton1.setOnClickListener(this);

        Button mainButton2 = findViewById(R.id.mainButton2);
        mainButton2.setOnClickListener(this);

        Button mainButton3 = findViewById(R.id.mainButton3);
        mainButton3.setOnClickListener(this);


    }



}