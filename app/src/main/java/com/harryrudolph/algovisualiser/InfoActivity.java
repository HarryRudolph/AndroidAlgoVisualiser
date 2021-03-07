package com.harryrudolph.algovisualiser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A class for the InfoActivity. This displays information on each algorithm and allows user
 * to adjust parameters. This activity renders different information based on the the intent from
 * MainActivity.
 */
public class InfoActivity extends AppCompatActivity {

    /**
     * A method that is called when activity is created. Initialises data structures,
     * and waits for the user to press button.
     * @param savedInstanceState null as no saved state is provided
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Finding all view elements.
        TextView algoDescription = findViewById(R.id.algoDescription);
        Button viewCodeButton = findViewById(R.id.viewCodeButton);
        TextView paramText1 = findViewById(R.id.paramTextView1);
        TextView paramText2 = findViewById(R.id.paramTextView2);
        EditText param1 = findViewById(R.id.paramText1);
        EditText param2 = findViewById(R.id.paramText2);
        Button startAlgoButton = findViewById(R.id.startAlgoButton);

        //Get intention passed from MainActivity
        Intent recIntent = getIntent();
        String type = recIntent.getStringExtra("Algo");

        //Depending on MainActivity selection, display different information for each algorithm.
        switch (type) {
            case "BFS":
                setTitle(R.string.BFSTitle);
                algoDescription.setText(R.string.BFSDescription);

                paramText1.setText(R.string.BFSParam1);
                paramText2.setText(R.string.BFSParam2);
                param1.setHint(R.string.BFSParam1);
                param2.setHint(R.string.BFSParam2);
                param1.setText("0");
                param2.setText("24");
                startAlgoButton.setText(R.string.BFSAction);

                //View Code button
                Uri bfsURL = Uri.parse("https://gist.github.com/HarryRudolph/77bfc56d456dbbd4f60d6d03e821b416");
                Intent bfsCodeIntent = new Intent(Intent.ACTION_VIEW, bfsURL);
                viewCodeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(bfsCodeIntent);

                    }
                });

                //Go to BFSActivity if button is pressed.
                Intent bfsIntent = new Intent(InfoActivity.this, BFSActivity.class);
                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bfsIntent.putExtra("startPos", Integer.parseInt(param1.getText().toString())); // Will crash if nothing is entered.
                        bfsIntent.putExtra("endPos",Integer.parseInt(param2.getText().toString()));
                        startActivity(bfsIntent);
                    }
                });
                break;

            case "DFS":
                setTitle(R.string.DFSTitle);
                algoDescription.setText(R.string.DFSDescription);

                paramText1.setText(R.string.DFSParam1);
                paramText2.setText(R.string.DFSParam2);
                param1.setHint(R.string.DFSParam1);
                param2.setHint(R.string.DFSParam2);
                param1.setText("0");
                param2.setText("24");
                startAlgoButton.setText(R.string.DFSAction);

                //View code button
                Uri dfsURL = Uri.parse("https://gist.github.com/HarryRudolph/8caa0b78f6246ecc84cee756895d607c");
                Intent dfsCodeIntent = new Intent(Intent.ACTION_VIEW, dfsURL);
                viewCodeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(dfsCodeIntent);

                    }
                });

                //Go to DFSActivity if button is pressed.
                Intent dfsIntent = new Intent(InfoActivity.this, DFSActivity.class);
                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dfsIntent.putExtra("startPos", Integer.parseInt(param1.getText().toString())); //Will crash if nothing is entered
                        dfsIntent.putExtra("endPos",Integer.parseInt(param2.getText().toString()));
                        startActivity(dfsIntent);
                    }
                });
                break;

            case "NQUEENS":
                setTitle(R.string.NQUEENSTitle);
                algoDescription.setText(R.string.NQUEENSDescription);

                paramText1.setText(R.string.NQUEENSParam1);
                paramText2.setText(R.string.NQUEENSParam2);
                param1.setHint(R.string.NQUEENSParam1);
                param2.setHint(R.string.NQUEENSParam2);
                param1.setText("8");
                param2.setText("10");
                param1.setHint(R.string.NQUEENSParam1);
                param2.setHint(R.string.NQUEENSParam2);
                startAlgoButton.setText(R.string.NQUEENSAction);

                //View code button
                Uri queenURL = Uri.parse("https://gist.github.com/HarryRudolph/544942e7ceaaba4c3c5de81ad13f4266");
                Intent queenCodeIntent = new Intent(Intent.ACTION_VIEW, queenURL);
                viewCodeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(queenCodeIntent);

                    }
                });


                //Go to QueensActivity if button is pressed.
                Intent queensIntent = new Intent(InfoActivity.this, QueensActivity.class);
                startAlgoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        queensIntent.putExtra("BoardSize", Integer.parseInt(param1.getText().toString())); //Will crash if nothing is entered
                        queensIntent.putExtra("AnimationDelay", Integer.parseInt(param2.getText().toString())); //Will crash if nothing is entered

                        startActivity(queensIntent);
                    }
                });
                break;
        }
    }
}