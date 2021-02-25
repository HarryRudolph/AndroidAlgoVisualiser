package com.harryrudolph.algovisualiser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView algoDescription = findViewById(R.id.algoDescription);
        Button viewCodeButton = findViewById(R.id.viewCodeButton);
        EditText param = findViewById(R.id.paramText);
        Button startAlgoButton = findViewById(R.id.startAlgoButton);

        Intent recIntent = getIntent();
        String type = recIntent.getStringExtra("Algo");

        switch (type) {
            case "BFS":
                setTitle(R.string.BFSTitle);
                algoDescription.setText(R.string.BFSDescription);
                param.setText(R.string.BFSParam);
                startAlgoButton.setText(R.string.BFSAction);

                Intent intent = new Intent(InfoActivity.this, BFSActivity.class);

                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("startPos", 0); //@Hardcoded. Change this
                        intent.putExtra("endPos", 25);
                        startActivity(intent);
                    }
                });
                break;
            case "DFS":
                setTitle(R.string.DFSTitle);
                algoDescription.setText(R.string.DFSDescription);
                param.setText(R.string.DFSParam);
                startAlgoButton.setText(R.string.DFSAction);

                //Intent intent = new Intent(InfoActivity.this, To the next place)
                break;
            case "NQEENS":
                setTitle(R.string.NQUEENSTitle);
                algoDescription.setText(R.string.NQUEENSDescription);
                param.setText(R.string.NQUEENSParam);
                startAlgoButton.setText(R.string.NQUEENSAction);
                break;
        }


    }

}