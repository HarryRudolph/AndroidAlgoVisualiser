package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);

        switch (v.getId()) {
            case R.id.mainBFSButton:
                System.out.println("button1");
                intent.putExtra("Algo", "BFS");
                startActivity(intent);
                break;
            case R.id.mainDFSButton:
                System.out.println("button2");
                intent.putExtra("Algo", "DFS");
                startActivity(intent);

                break;
            case R.id.mainNQUEENSButton:
                System.out.println("button3");
                intent.putExtra("Algo", "N-QUEENS");
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton1 = findViewById(R.id.mainBFSButton);
        mainButton1.setOnClickListener(this);

        Button mainButton2 = findViewById(R.id.mainDFSButton);
        mainButton2.setOnClickListener(this);

        Button mainButton3 = findViewById(R.id.mainNQUEENSButton);
        mainButton3.setOnClickListener(this);


    }



}