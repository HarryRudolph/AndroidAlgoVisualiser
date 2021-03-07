package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

/**
 * A class for the MainActivity. This is the first screen the user comes to.
 * It implements an OnClickListener, waiting for the user to press one of three buttons.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * A method to wait for the user to press one of three buttons.
     * @param v current view
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);

        //Depending on ID pass different extra information to the Intent
        switch (v.getId()) {
            case R.id.mainBFSButton:
                intent.putExtra("Algo", "BFS");
                startActivity(intent);
                break;
            case R.id.mainDFSButton:
                intent.putExtra("Algo", "DFS");
                startActivity(intent);
                break;

            case R.id.mainNQUEENSButton:
                intent.putExtra("Algo", "NQUEENS");
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /**
     * A method that is called when the activity is created. Sets onclick listener
     * for each of the buttons.
     * @param savedInstanceState null as no saved state is provided
     */
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