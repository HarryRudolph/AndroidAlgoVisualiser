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

import com.harryrudolph.algovisualiser.R;

public class CustomView extends View {

    private static final int CIRCLESIZE = 75;
    private Paint graphColor;

    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        graphColor =  new Paint(Paint.ANTI_ALIAS_FLAG); //Ensure anti-aliasing on
        graphColor.setColor(Color.rgb(139,195,74));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cOffsetX = 140;
        float cOffsetY = 100;

        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++) {
                canvas.drawCircle(cOffsetX + (200*x), cOffsetY + (200*y), CIRCLESIZE, graphColor);
            }
        }
        //canvas.drawColor(Color.RED); //Background Colour
    }
}
