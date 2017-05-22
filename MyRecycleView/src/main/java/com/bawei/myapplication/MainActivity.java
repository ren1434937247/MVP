package com.bawei.myapplication;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bawei.myapplication.adapter.MyRecyclerView;
import com.bawei.myapplication.bean.HomeBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recycle;
    private SwipeRefreshLayout swipe;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data();
        json();
        info();


    }

    public void json() {
        String url="http://api.expoon.com/AppNews/getNewsList/type/2/p/1";
        RequestParams requestParams = new RequestParams();
        requestParams.setUri(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            private MyRecyclerView myRecyclerView;

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(result, HomeBean.class);
                myRecyclerView = new MyRecyclerView(MainActivity.this,homeBean.getData());
                recycle.setAdapter(myRecyclerView);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void data() {
        recycle = (RecyclerView) findViewById(R.id.recycle);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
    }

    private void info() {
       // MyRecyclerView myRecyclerView = new MyRecyclerView(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycle.setLayoutManager(linearLayoutManager);
       // recycle.setAdapter(myRecyclerView);
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
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(2000);
                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {

                                       }
                                   });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        },5000);

    }
}
