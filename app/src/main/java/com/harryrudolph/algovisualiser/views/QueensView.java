package com.harryrudolph.algovisualiser.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Arrays;

/**
 * A class to create a custom view that a chess board of n size. Can also render circles to
 * represent queens.
 */
public class QueensView extends View {
    private final int BOARDOFFSETX = 140; //The x offset to start drawing board
    private final int BOARDOFFSETY = 100; //The y offset to start drawing board

    private final int QUEENOFFSETX = BOARDOFFSETX + 50; //The x offset to start drawing queen
    private final int QUEENOFFSETY = BOARDOFFSETY + 90; //The y offset to start drawing queen

    private final int SPACING = 100; //Spacing between board squares
    private final int SQUARELENGTH = 100; //Length of chessboard square

    private int dark = Color.rgb(184,139,74); //Colour of dark squares
    private int light = Color.rgb(227,193,111); //Colour of light squares.

    Paint boardPaint; //Paint object for board.
    Paint queenPaint; //Paint object for queen

    private int boardSize; // This is 0 indexed
    int[] queenBoard; //Array to hold queen positions. Index is X coordinate, Value is Y coordinate.

    /**
     * Constructor extending from superclass
     * @param context context for application
     */
    public QueensView(Context context) {
        super(context);
        init(null); //Calls init method with null
    }

    /**
     * Constructor extending from superclass
     * @param context context for application
     * @param attrs any attributes passed.
     */
    public QueensView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs); //Calls init method with any attributes passed
    }

    /**
     * Constructor extending from superclass.
     * @param context context for application
     * @param attrs any attributes passed
     * @param defStyleAttr any style attributes passed
     */
    public QueensView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs); //Calls init method with any attributes passed.
    }

    /**
     * Constructor extending from superclass.
     * @param context context for application
     * @param attrs any attributes passed
     * @param defStyleAttr any style attributes passed
     * @param defStyleRes any style resources passed
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public QueensView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs); //Call init with attributes
    }

    /**
     * Called when view is loaded. Used to
     * initiate values for paint objects.
     * @param set any attributes being passed in.
     */
    private void init(@Nullable AttributeSet set){
        boardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        queenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        queenPaint.setColor(Color.rgb(205, 50, 50));
    }

    /**
     * A method to generate the board as it will be rendered. Fills the queenBoard array with -1.
     * @param n size of board to be generated.
     */
    public void generateBoard(int n){
        boardSize = n-1; //0 indexed
        queenBoard = new int[n];
        Arrays.fill(queenBoard, -1); //Queen is set to -1 indicating that it has not yet been placed
    }

    /**
     * A method to update the queenBoard. This is called from outside as the algorithm is running.
     * @param board
     */
    public void updateBoard(int[] board){
        queenBoard = board;
        invalidate();
    }

    /**
     * Override of View's onDraw method
     * @param canvas canvas used to draw GraphView
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //Draw the chess board.
        for (int y = 0; y <= boardSize; y++){
            for (int x = 0; x <= boardSize; x++){
                if (y % 2 == 0){
                    if (x % 2 == 0) {
                        boardPaint.setColor(light);
                    } else boardPaint.setColor(dark);
                }else if (x % 2 != 0){
                    boardPaint.setColor(light);
                }else boardPaint.setColor(dark);

                int startX = BOARDOFFSETX + (x * SPACING);
                int startY = BOARDOFFSETX + (y * SPACING);
                int endX = startX + SQUARELENGTH;
                int endY = startY + SQUARELENGTH;

                canvas.drawRect(startX, startY, endX, endY, boardPaint);

                //Draw the queenBoard array
                if(queenBoard[x] != -1 ){
                    canvas.drawCircle(QUEENOFFSETX + (x*SPACING), QUEENOFFSETY + (queenBoard[x]*SPACING), 40, queenPaint);
                }
            }
        }

    }
}
