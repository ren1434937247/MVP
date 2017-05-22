package com.bawei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.myapplication.adapter.MyAdapter;
import com.bawei.myapplication.bean.HomeBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements PullToRefreshListView.OnRefreshListener2{

    private PullToRefreshListView pulltorefresh;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intview();
        intdata();

    }

    private void intdata() {

        String url="http://api.expoon.com/AppNews/getNewsList/type/2/p/1";
        RequestParams requestParams = new RequestParams();
        requestParams.setUri(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(result, HomeBean.class);
                myAdapter = new MyAdapter(this);
                pulltorefresh.setAdapter(null);
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

    private void intview() {
        pulltorefresh = (PullToRefreshListView) findViewById(R.id.pulltorefrece);
        //对下拉的监听
        pulltorefresh.setOnRefreshListener(this);

        pulltorefresh.setAdapter(null);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
      refrenshData();
    }

    private void refrenshData() {
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
    loadMore();
    }

    private void loadMore() {
    }
}
