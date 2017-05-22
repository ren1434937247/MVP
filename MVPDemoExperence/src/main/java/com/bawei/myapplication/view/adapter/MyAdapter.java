package com.bawei.myapplication.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myapplication.R;
import com.bawei.myapplication.model.Bean.HomeBean;
import com.bawei.myapplication.presenter.HomePresenter;


import java.util.ArrayList;
import java.util.List;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyAdapter extends BaseAdapter {
    private Context mcontext;
    private HomePresenter mHomePresenter;
    private List<HomeBean.DataBean> data = new ArrayList<>();
    private Honner honner;

    public MyAdapter(Context context) {
        this.mcontext = context;
    }

    public void setPresenter(HomePresenter presenter) {
        this.mHomePresenter = presenter;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HomeBean.DataBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Honner honner = new Honner();
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.layout_bj, null);

            honner.text1 = (TextView) convertView.findViewById(R.id.text1);
            honner.text2 = (TextView) convertView.findViewById(R.id.text3);
            honner.imageview = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(honner);
        } else {
            honner = (Honner) convertView.getTag();
        }
        honner.text1.setText(data.get(position).getNews_title());
        honner.text2.setText(data.get(position).getNews_summary());
        mHomePresenter.getimage(honner.imageview, data.get(position).getPic_url());
        return convertView;

    }

    static class Honner {
        TextView text1;
        TextView text2;
        ImageView imageview;
    }
    public void setData(List<HomeBean.DataBean> data) {
        if (data != null) {
            this.data.addAll(data);
        }

}


    }
