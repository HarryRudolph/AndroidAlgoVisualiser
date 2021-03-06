package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.harryrudolph.algovisualiser.views.QueensView;

import java.util.Arrays;

public class QueensActivity extends AppCompatActivity {
    private QueensView mQueensView;

    private int[] board;
    private int boardSize;

    boolean ran = false; //@hardcoded

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queens);
        setTitle("QueensView");

        Intent recIntent = getIntent();
        boardSize = recIntent.getIntExtra("BoardSize",8);

        board = new int[boardSize];
        Arrays.fill(board, -1);



        mQueensView = findViewById(R.id.QueensView);
        mQueensView.generateBoard(boardSize);
        //mQueensView.placeQueen(1, 3);


        Button nextStepButton = findViewById(R.id.nextStep);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgrounRecursionThread().execute();
                if (false) recursionQueen(0);
                ran = true;

                if (false){
                    Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private class BackgrounRecursionThread extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            recursionQueen(0);
            return null;
        }
    }

    private boolean recursionQueen(int currentX){
        if (currentX == boardSize){
            return true;
        }

        for(int i = 0; i < boardSize; i++) {
            mQueensView.updateBoard(board);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            board[currentX] = i;
            if (checkValid(currentX)) {
                if (recursionQueen(currentX + 1)) {
                    return true;
                }
            }
        }
        board[currentX] = -1;
        return false;
    }

    private boolean checkValid(int currentX){
        //only have to check to left of each queen
        //X coordinates will never be the same due to the nature of the solution. We are placing
        //queens one column at at time
        for (int i = 0; i < currentX; i++){
            if (board[i] == board[currentX]){
                //We have a match in x dimension
                System.out.println("collision in X");

                return false;
            }
        }

        int x = currentX -1;
        int y = board[currentX] -1;
        while (x >= 0 && y >= 0){
            if (board[x] == y){
                //collision in upper left diagonal
                System.out.println("collision in upper left");
                return false;
            }
            x--;
            y--;
        }

        x = currentX -1;
        y = board[currentX]+1;
        while (x >= 0 && y < boardSize){
            if (board[x] == y){
                //collision in lower left diagonal
                System.out.println("collision in lower left");

                return false;
            }
            x--;
            y++;
        }
        return true;
    }

}