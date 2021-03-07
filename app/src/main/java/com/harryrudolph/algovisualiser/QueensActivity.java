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

/**
 * A class for the QueensActivity. Runs the N-Queens problem and adjusts data within
 * QueensView
 */
public class QueensActivity extends AppCompatActivity {
    private QueensView mQueensView; //QueensView from activity
    private TextView mQueensTextView; //TextView from activity

    private int boardSize; //Board size as defined by user
    private int animationDelay; //Animation delay as defined by user.

    private int[] board; //Data structure to hold queen positions on board.

    private boolean finished; //True if algorithm is finished.

    /**
     * A method that is called when activity is created. Initialises data structures,
     * and waits for button press.
     * @param savedInstanceState null as no saved state is provided
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queens);
        setTitle("QueensView");

        //Get intention from InfoActivity
        Intent recIntent = getIntent();
        boardSize = recIntent.getIntExtra("BoardSize",8);
        animationDelay = recIntent.getIntExtra("AnimationDelay",10);

        finished = false;

        //Initialise the board array. -1 indicates queen has not been placed.
        board = new int[boardSize];
        Arrays.fill(board, -1);

        mQueensView = findViewById(R.id.QueensView);
        mQueensView.generateBoard(boardSize); //Generate the rendered board.

        mQueensTextView = findViewById(R.id.queensTextView);
        updateText();

        Button startButton = findViewById(R.id.nextStep);
        startButton.setOnClickListener(new View.OnClickListener() {
            /**
             * A method to wait for user to press startButton
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                //Start a Asynchronous background thread.
                new BackgroundRecursionThread().execute();
            }
        });

    }

    /**
     * A class to run the NQueens backtracking algorithm in the background using AsyncTask
     */
    private class BackgroundRecursionThread extends AsyncTask<Void, Void, Void> {
        /**
         * A method to run on a background thread
         * @param voids void
         * @return void
         */
        @Override
        protected Void doInBackground(Void... voids) {
            recursionQueen(0);
            return null;
        }

        /**
         * A method that is called after Async Task has finished.
         * @param aVoid void
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finished = true; // After completed is finished.
            mQueensView.updateBoard(board); //Update board one last time. This reduces calls in recursion.
            updateText(); //Update text
        }

    }

    /**
     * A method to run the N-Queens backtracking algorithm. This method calls itself recursively.
     * @param currentX The current x value
     * @return Whether all queens are placed in a valid position.
     */
    private boolean recursionQueen(int currentX){
        if (currentX == boardSize){
            return true;
        }

        for(int i = 0; i < boardSize; i++) {
            mQueensView.updateBoard(board); //Update the QueensView
            updateText();
            try {
                Thread.sleep(animationDelay); // Pausing the background thread allowing for animation.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            board[currentX] = i;
            if (checkValid(currentX)) {
                if (recursionQueen(currentX + 1)) { //Call itself with next X value.
                    return true;
                }
            }
        }

        board[currentX] = -1; //Queen has not been placed so set to -1
        return false;
    }


    /**
     * A method to check whether a queen can be placed in a specific position.
     * @param currentX current X coordinate of queen
     * @return true if queen can be placed, false if queen cannot be placed.
     */
    private boolean checkValid(int currentX){
        //It is only necessary to check to the left of each queen.
        //x coordinates cannot be the same due to the nature of the implementation, we are placing
        //queens one column at at time.
        for (int i = 0; i < currentX; i++){
            if (board[i] == board[currentX]){
                //Collision in the same y
                return false;
            }
        }

        int x = currentX - 1; //-1 to start searching from one square away
        int y = board[currentX] - 1; //-1 to start searching from one square away
        while (x >= 0 && y >= 0){
            if (board[x] == y){
                //Collision in upper left diagonal
                return false;
            }
            x--;
            y--;
        }

        x = currentX - 1; //-1 to start searching from one square away
        y = board[currentX] + 1; //+1 to start searching from one square away
        while (x >= 0 && y < boardSize){
            if (board[x] == y){
                //Collision in lower left diagonal
                return false;
            }
            x--;
            y++;
        }
        return true;
    }

    /**
     * A method to update the mQueensTextView text view.
     */
    private void updateText(){
        String text;
        text = "Queen array:\n"+ Arrays.toString(board);

        if (finished) text = text + "\n Finished";
        mQueensTextView.setText(text);
    }


}