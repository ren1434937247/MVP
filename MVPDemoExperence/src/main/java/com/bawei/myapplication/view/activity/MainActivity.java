package com.bawei.myapplication.view.activity;


import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bawei.myapplication.R;
import com.bawei.myapplication.model.Bean.HomeBean;
import com.bawei.myapplication.presenter.HomePresenter;
import com.bawei.myapplication.view.adapter.MyAdapter;
import com.bawei.myapplication.view.adapter.MyRecyclerAdapter;
import com.bawei.myapplication.view.iview.IHomeView;
import com.bawei.myapplication.wedget.MyDriver;
import com.bawei.myapplication.wedget.MyDriviItem;

public class MainActivity extends AppCompatActivity implements IHomeView, SwipeRefreshLayout.OnRefreshListener {
     private RecyclerView recycle;
    private ListView lv;
    private MyAdapter myAdapter;
    private String uploadUrl = "http://169.254.159.111:8080/ssm/user/photoUpload";
    private MyRecyclerAdapter myRecyclerAdapter;
    private SwipeRefreshLayout swipe;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progress;
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info();
        indate();

    }
    private void info() {

       // lv = (ListView) findViewById(R.id.listview);
        recycle= (RecyclerView) findViewById(R.id.recycler);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        progress= (ProgressBar) findViewById(R.id.progress);
        swipe.setEnabled(true);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(Color.BLUE);

        recycle.addItemDecoration(new MyDriver(this));



//        swipe.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//            }
//        });
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    int itemCount = linearLayoutManager.getItemCount();
                    if (lastVisibleItemPosition+1==itemCount){
                        progress.setVisibility(View.VISIBLE);
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(2000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progress.setVisibility(View.GONE);
                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }.start();
                        Toast.makeText(MainActivity.this,"已到最下面了",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    private void indate() {
        homePresenter = new HomePresenter();
        //调用方法
        homePresenter.attachView(this);
        //适配器
        myAdapter = new MyAdapter(this);

        myAdapter.setPresenter(homePresenter);



        //lv.setAdapter(myAdapter);



        myRecyclerAdapter = new MyRecyclerAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3,gr);
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager()
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(myRecyclerAdapter);


//        String url= Environment.getExternalStorageDirectory().getAbsolutePath()+"/luoxuzhong.png";
//        Log.e("图片的路径",url);
//        homePresenter.uploadImage(uploadUrl,url);

    }


//调用HomePreserter中的两个方法---->callbackstr()和callbackErr()
    @Override
    public void callbackStr(HomeBean data) {
//        myAdapter.setData(data.getData());
//        //刷新适配器
//        myAdapter.notifyDataSetChanged();
        myRecyclerAdapter.setData(data.getData());
        myRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void callbackErr(String errMsg, int errCode) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        },5000);
        homePresenter.getcontent();
    }
}
