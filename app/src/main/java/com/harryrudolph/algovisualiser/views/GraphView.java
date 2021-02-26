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

import java.util.ArrayList;

public class GraphView extends View {

    private static final int CIRCLERADIUS = 75;
    private static final int SPACING = 200;
    private static final int NODECOUNT = 25;

    private float cOffsetX = 140;
    private float cOffsetY = 100;
    private float tOffsetY = cOffsetY + 10;

    private Paint nodePaint;
    private Paint textPaint;
    private Paint edgePaint;

    private float[] allEdges;
    private int[][] nodeColors = new int[5][5]; //stores color to paint nodes. 1 = red. 2 = green.

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
        nodePaint =  new Paint(Paint.ANTI_ALIAS_FLAG); //Ensure anti-aliasing on
        nodePaint.setColor(Color.rgb(71,155,233));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);

        edgePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        edgePaint.setColor(Color.BLACK);
        edgePaint.setStrokeWidth(10);
    }

    public void setColor(int node, int color) {
        nodeColors[node/5][node%5] = color;
        postInvalidate(); //update the onDraw
    }

    public void generateEdges(int[][] matrix){
        float length = (SPACING) - (CIRCLERADIUS * 2);
        ArrayList<Float> edgeAL = new ArrayList<Float>();
        //horizontal edges have same y
        //vertical edges have same x

        //length is always the same.
        // Starting pos increases by i*spacing + circle size

        // i and j go through the adjacency matrix
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1){
                    if(Math.abs(i-j) == 1){
                        //Then we know it's a horizontal line.
                        if (i < j) {
                            //Only render one half of adjacency matrix
                            float xPixel = cOffsetX + CIRCLERADIUS + ((i % 5) * SPACING);
                            float yPixel = cOffsetY + ((j / 5) * SPACING);

                            edgeAL.add(xPixel);
                            edgeAL.add(yPixel);
                            edgeAL.add(xPixel + length);
                            edgeAL.add(yPixel);
                        }
                    } else{
                        //Must be vertical due to restrictions of a set graph shape
                        if (i > j) {
                            //this time render the other half of adjacency matrix
                            float xPixel = cOffsetX + ((i%5) * SPACING);
                            float yPixel = cOffsetY + CIRCLERADIUS + ((j/5)* SPACING);

                            edgeAL.add(xPixel);
                            edgeAL.add(yPixel);
                            edgeAL.add(xPixel);
                            edgeAL.add(yPixel + length);
                        }
                    }

                }
            }
        }

        allEdges = new float[edgeAL.size()];
        for (int i = 0; i < edgeAL.size(); i++){
            allEdges[i] = edgeAL.get(i);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        Integer counter = 0;

        //Drawing nodes
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++) {
                switch (nodeColors[y][x]) {
                    case 1:
                        nodePaint.setColor(Color.rgb(250,128,114));
                        break;
                    case 2:
                        nodePaint.setColor(Color.rgb(50,205,50));
                        break;
                    default:
                        nodePaint.setColor(Color.rgb(71, 155, 233));
                        break;
                }
                canvas.drawCircle(cOffsetX + (SPACING*x), cOffsetY + (SPACING*y), CIRCLERADIUS, nodePaint);
                canvas.drawText(counter.toString(), cOffsetX + (SPACING*x), tOffsetY + (SPACING*y), textPaint);
                counter++;
            }
        }

        canvas.drawLines(allEdges, edgePaint);




    }
}
