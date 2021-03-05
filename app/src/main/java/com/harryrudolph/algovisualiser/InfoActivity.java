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
        EditText param1 = findViewById(R.id.paramText1);
        EditText param2 = findViewById(R.id.paramText2);
        Button startAlgoButton = findViewById(R.id.startAlgoButton);

        Intent recIntent = getIntent();
        String type = recIntent.getStringExtra("Algo");

        switch (type) {
            case "BFS":
                setTitle(R.string.BFSTitle);
                algoDescription.setHint(R.string.BFSDescription);
                param1.setHint(R.string.BFSParam1);
                param2.setHint(R.string.BFSParam2);
                startAlgoButton.setText(R.string.BFSAction);

                Intent bfsIntent = new Intent(InfoActivity.this, BFSActivity.class);

                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bfsIntent.putExtra("startPos", Integer.parseInt(param1.getText().toString())); // have to convert to string first
                        bfsIntent.putExtra("endPos",Integer.parseInt(param2.getText().toString()));
                        startActivity(bfsIntent);
                    }
                });
                break;
            case "DFS":
                setTitle(R.string.DFSTitle);
                algoDescription.setText(R.string.DFSDescription);
                param1.setHint(R.string.DFSParam1);
                param2.setHint(R.string.DFSParam2);
                startAlgoButton.setText(R.string.DFSAction);
                Intent dfsIntent = new Intent(InfoActivity.this, DFSActivity.class);

                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dfsIntent.putExtra("startPos", 0); //@Hardcoded. Change this
                        dfsIntent.putExtra("endPos",24);
                        startActivity(dfsIntent);
                    }
                });
                break;
            case "NQUEENS":
                setTitle(R.string.NQUEENSTitle);
                algoDescription.setText(R.string.NQUEENSDescription);
                param1.setHint(R.string.NQUEENSParam1);
                param2.setHint(R.string.NQUEENSParam2);
                startAlgoButton.setText(R.string.NQUEENSAction);
                Intent queensIntent = new Intent(InfoActivity.this, QueensActivity.class);
                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        queensIntent.putExtra("BoardSize", 8); //@Hardcoded. Change this

                        startActivity(queensIntent);
                    }
                });
                break;
        }


    }

}