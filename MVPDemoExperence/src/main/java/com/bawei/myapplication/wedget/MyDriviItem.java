package com.bawei.myapplication.wedget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyDriviItem extends RecyclerView.ItemDecoration {
    private Context context;
    private int screenWidth;
    public MyDriviItem(Context context) {
        this.context=context;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        //
        screenWidth = dm.widthPixels;


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //设置一个画笔，定义一个具有颜色的形状
        Paint paint=new Paint();
        //将线条定义成蓝色
        paint.setColor(Color.BLUE);

        //获得一共有多少个Item
        int childCount = parent.getChildCount();
        //设置for循环，为每一个item下方都设置
        for (int i = 0; i < childCount; i++) {
            View  view = parent.getChildAt(i);
            view.getWidth();
            view.getLeft();
            view.getBottom();
            view.getRight();
                      //左             //下            //最右面     线条的高度         画笔
            c.drawRect(view.getLeft(),view.getBottom(),screenWidth,view.getBottom()+10,paint);

        }


    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
