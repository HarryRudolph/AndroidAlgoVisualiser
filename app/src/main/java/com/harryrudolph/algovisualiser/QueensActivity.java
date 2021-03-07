package com.harryrudolph.algovisualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harryrudolph.algovisualiser.views.QueensView;

import java.util.Arrays;

public class QueensActivity extends AppCompatActivity {
    private QueensView mQueensView;
    private TextView mQueensTextView;

    private int[] board;

    private int boardSize;
    private int animationDelay;

    private boolean finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queens);
        setTitle("QueensView");

        Intent recIntent = getIntent();
        boardSize = recIntent.getIntExtra("BoardSize",8);
        animationDelay = recIntent.getIntExtra("AnimationDelay",10);

        finished = false;

        board = new int[boardSize];
        Arrays.fill(board, -1);

        mQueensView = findViewById(R.id.QueensView);
        mQueensView.generateBoard(boardSize);

        mQueensTextView = findViewById(R.id.queensTextView);
        updateText();

        Button nextStepButton = findViewById(R.id.nextStep);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundRecursionThread().execute();
            }
        });

    }

    private class BackgroundRecursionThread extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            recursionQueen(0);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finished = true;
            mQueensView.updateBoard(board);
            updateText();
        }

    }

    private boolean recursionQueen(int currentX){
        if (currentX == boardSize){
            return true;
        }

        for(int i = 0; i < boardSize; i++) {
            mQueensView.updateBoard(board);
            updateText();
            try {
                Thread.sleep(animationDelay);
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
                return false;
            }
        }

        int x = currentX -1;
        int y = board[currentX] -1;
        while (x >= 0 && y >= 0){
            if (board[x] == y){
                //collision in upper left diagonal
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
                return false;
            }
            x--;
            y++;
        }
        return true;
    }

    private void updateText(){
        String text;
        text = "Queen array:\n"+ Arrays.toString(board);

        if (finished) text = text + "\n Finished";
        mQueensTextView.setText(text);
    }


}