package com.harryrudolph.algovisualiser.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class QueensView extends View {
    private final int OFFSETX = 140; //The x offset to start drawing
    private final int OFFSETY = 100; //The y offset to start drawing

    private final int SPACING = 100;
    private final int LENGTH = 100;

    private int dark = Color.rgb(184,139,74);
    private int light = Color.rgb(227,193,111);

    private int boardSize = 7; // This is 0 indexed

    Paint boardPaint;
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
    }

    private void generateBoard(int n){
        boardSize = n;
        if (n > 8) {
            return;
        }
    }

    /**
     * Override of View's onDraw method
     * @param canvas canvas used to draw GraphView
     */
    @Override
    protected void onDraw(Canvas canvas) {
        for (int y = 0; y <= boardSize; y++){
            for (int x = 0; x <= boardSize; x++){
                if (y % 2 == 0){
                    if (x % 2 == 0) {
                        boardPaint.setColor(light);
                    } else boardPaint.setColor(dark);
                }else if (x % 2 != 0){
                    boardPaint.setColor(light);
                }else boardPaint.setColor(dark);

                int startX = OFFSETX + (x * SPACING);
                int startY = OFFSETX + (y * SPACING);
                int endX = startX + LENGTH;
                int endY = startY + LENGTH;

                canvas.drawRect(startX, startY, endX, endY, boardPaint);
            }
        }

    }
}
