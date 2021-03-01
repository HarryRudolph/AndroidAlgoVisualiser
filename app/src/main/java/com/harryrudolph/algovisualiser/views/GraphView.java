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

    private static final int CIRCLERADIUS = 75; //The radius of each circle (node) to be drawn.
    private static final int SPACING = 200; //The spacing between the centres of each circle.

    private float cOffsetX = 140; //The x offset to start drawing each circle from.
    private float cOffsetY = 100; //The y offset to start drawing each circle from.

    private float tOffsetX = cOffsetX;  //The x offset to start drawing text from.
    private float tOffsetY = cOffsetY + 15; //The y offset to start drawing text from.

    private Paint nodePaint; //The Paint object for each circle (node).
    private Paint textPaint; //The Paint object for the text (numbers in circle).
    private Paint edgePaint; //The Paint object for drawing the edges.

    private float[] allEdges; //Float array to store x and y co-ords of all edges.

    //stores color to paint nodes. 0=blue. 1=red. 2=green.
    private int[][] nodeColors = new int[5][5];

    /**
     * Constructor extending from superclass
     * @param context context for application
     */
    public GraphView(Context context) {
        super(context);
        init(null); //Calls init method with null
    }

    /**
     * Constructor extending from superclass
     * @param context context for application
     * @param attrs any attributes passed.
     */
    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs); //Calls init method with any attributes passed
    }

    /**
     * Constructor extending from superclass.
     * @param context context for application
     * @param attrs any attributes passed
     * @param defStyleAttr any style attributes passed
     */
    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs); //Call init with attributes
    }

    /**
     * Called when view is loaded. Used to
     * initiate values for paint objects.
     * @param set any attributes being passed in.
     */
    private void init(@Nullable AttributeSet set){
        nodePaint =  new Paint(Paint.ANTI_ALIAS_FLAG); //Ensure anti-aliasing on
        nodePaint.setColor(Color.rgb(71,155,233)); //Default color for circles

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);

        edgePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        edgePaint.setColor(Color.BLACK);
        edgePaint.setStrokeWidth(10); //Thickness of edge lines
    }

    /**
     * Called from external class to change a circle to a different color.
     * @param node The node (circle) to change, based on position in list of nodes.
     * @param color The color to set the circle to. 0=blue. 1=red. 2=green.
     */
    public void setColor(int node, int color) {
        nodeColors[node/5][node%5] = color; //Accessing x and y coordinates of that specific node(circle)
        postInvalidate(); //update the onDraw
    }

    /**
     * A method to generate the x and y coordinates used to draw each edge.
     * This only works when nodes are laid out in a rectangular formation.
     * To work with different formations use linear interpolation.
     * @param matrix The adjacency matrix containing values of which edges are connected.
     */
    public void generateEdges(int[][] matrix){
        float length = (SPACING) - (CIRCLERADIUS * 2); //The length of each line regardless of horizontal/vertical
        ArrayList<Float> edgeAL = new ArrayList<Float>(); //ArrayList to store edge startX, startY, endX, endY

        // i and j loop through the adjacency matrix
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1){ //If a connection between two nodes is found
                    if(Math.abs(i-j) == 1){ //Then we know it's a horizontal line. as i and j are next to eachother.
                        if (i < j) { //Only render one half of adjacency matrix
                            /*Set up the starting position for the horizontal lines.
                              Then add them to edgeAL, with the length added to final X.
                             */
                            float xPixel = cOffsetX + CIRCLERADIUS + ((i % 5) * SPACING);
                            float yPixel = cOffsetY + ((j / 5) * SPACING);

                            edgeAL.add(xPixel);
                            edgeAL.add(yPixel);
                            edgeAL.add(xPixel + length);
                            edgeAL.add(yPixel);
                        }
                    } else {
                        //Must be vertical due to restrictions of a set graph formation
                        if (i > j) {
                            //this time render the other half of adjacency matrix
                            float xPixel = cOffsetX + ((i%5) * SPACING);
                            float yPixel = cOffsetY + CIRCLERADIUS + ((j/5)* SPACING);

                            edgeAL.add(xPixel);
                            edgeAL.add(yPixel);
                            edgeAL.add(xPixel);
                            edgeAL.add(yPixel + length); //Adding length to Y as vertical line.
                        }
                    }

                }
            }
        }

        //Convert arrayList to array
        allEdges = new float[edgeAL.size()];
        for (int i = 0; i < edgeAL.size(); i++){
            allEdges[i] = edgeAL.get(i);
        }

    }

    /**
     * Override of View's onDraw method
     * @param canvas canvas used to draw GraphView
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Integer counter = 0; //Used to number each node.

        //Draw nodes.
        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++) {
                switch (nodeColors[y][x]) { //Set color of each node based on nodeColors array.
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
                //Draw each node.
                canvas.drawCircle(cOffsetX + (SPACING*x), cOffsetY + (SPACING*y), CIRCLERADIUS, nodePaint);
                //Draw the number for each node.
                canvas.drawText(counter.toString(), tOffsetX + (SPACING*x), tOffsetY + (SPACING*y), textPaint);
                counter++; //Increase the number
            }
        }

        canvas.drawLines(allEdges, edgePaint); //allEdges array is given to draw all edges.

    }
}
