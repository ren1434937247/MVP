package com.bawei.renxintong1503a20170515;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.renxintong1503a20170515.Utils.Night_styleutils;
import com.bawei.renxintong1503a20170515.Utils.UiUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
public class MainActivity extends AppCompatActivity {
    private int theme = 0;
    private ImageView image;
    private TextView dayornight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.importantimage);



//        LayoutInflater layout=this.getLayoutInflater();
//        View view=layout.inflate(R.layout.leftcontent, null);


                // 创建对象
                SlidingMenu smenu = new SlidingMenu(this);
                // 设置出现在左边还是右边
                smenu.setMode(SlidingMenu.LEFT);
                // 设置调出slidingmenu的区域
                smenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                // 设置阴影的宽度
                smenu.setShadowWidth(8);
                smenu.setShadowDrawable(R.drawable.shadow);
                // 设置slidingmenu滑出来时的宽度
                smenu.setBehindOffset(100);
                // 设置刚拉出来的时候颜色，1为全黑
                smenu.setFadeDegree(0.3f);
                // 添加到Activity上面
                smenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
                //此处加入要侧滑的布局文件
                smenu.setMenu(R.layout.leftcontent);

        dayornight= (TextView) findViewById (R.id.dayornight);
        dayornight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(MainActivity.this);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.reload();
            }
        });


                // 关闭监听
                smenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {

                    @Override
                    public void onClosed() {
                        Toast.makeText(MainActivity.this, "关闭了", Toast.LENGTH_SHORT).show();

                    }
                });
                // 打开监听
                smenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {

                    @Override
                    public void onOpened() {
                        Toast.makeText(MainActivity.this, "打开了", Toast.LENGTH_SHORT).show();

                    }
                });
                // 创建动画对象设置显示的时候出现的动画，这里我写的是一个入场动画
                SlidingMenu.CanvasTransformer canvasTransformer = new SlidingMenu.CanvasTransformer() {

                    @Override
                    public void transformCanvas(Canvas canvas, float percentOpen) {
                        float scale = (float) (percentOpen * 0.25 + 0.75);
                        canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
                    }
                };
                smenu.setBehindCanvasTransformer(canvasTransformer);

            }



    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }

    }

