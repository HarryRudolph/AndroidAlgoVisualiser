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

public class GraphView extends View {

    private static final int CIRCLESIZE = 75;
    private Paint graphPaint;
    private Paint textPaint;
    private Paint edgePaint;

    private float[] horizontalEdges;
    private float[] verticalEdges;

    public GraphView(Context context) {
        super(context);

        init(null);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        graphPaint =  new Paint(Paint.ANTI_ALIAS_FLAG); //Ensure anti-aliasing on
        graphPaint.setColor(Color.rgb(71,155,233));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);

        edgePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        edgePaint.setColor(Color.BLACK);
        edgePaint.setStrokeWidth(10);
    }

    public void swapColor() {
        graphPaint.setColor(Color.RED);
        postInvalidate(); //update the onDraw
    }

    public void generateEdges(int[][] matrix){
        for (int y = 0; y < matrix.length; y ++) {
            for (int x = 0; x < matrix.length; x++) {
                if (matrix[y][x] == 1){

                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cOffsetX = 140;
        float cOffsetY = 100;

        float tOffsetY = cOffsetY + 10;

        Integer counter = 0;

        //Drawing nodes
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++) {
                canvas.drawCircle(cOffsetX + (200*x), cOffsetY + (200*y), CIRCLESIZE, graphPaint);
                canvas.drawText(counter.toString(), cOffsetX + (200*x), tOffsetY + (200*y), textPaint);
                counter++;
            }
        }

        canvas.drawLine(cOffsetX+CIRCLESIZE, cOffsetY,cOffsetX+CIRCLESIZE + (200-(CIRCLESIZE*2)), cOffsetY, edgePaint);
        //Drawing Edges
        //draw horizontal edges
        //canvas.drawLines(horizontalEdges, edgeColor);
        //draw vertical edges
        //canvas.drawLines(verticalEdges, edgeColor);



    }
}
