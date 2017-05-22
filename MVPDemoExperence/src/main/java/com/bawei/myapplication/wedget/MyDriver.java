package com.bawei.myapplication.wedget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyDriver extends RecyclerView.ItemDecoration {
    private Context mcontext;
    private final int widthPixels;

    public MyDriver(Context context){
        this.mcontext=context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthPixels = displayMetrics.widthPixels;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setColor(Color.RED);


        int childCount = parent.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View view = parent.getChildAt(i);
            int left = view.getLeft();
            int width = view.getWidth();
            int right = view.getRight();
            int bottom = view.getBottom();
           c.drawRect(left,bottom,widthPixels,bottom+5,paint);

        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
