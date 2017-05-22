package com.bawei.myapplication.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bawei.myapplication.R;
import com.bawei.myapplication.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyRecyclerView extends RecyclerView.Adapter {
    private Context mcontext;
    private ArrayList<HomeBean.DataBean> list=new ArrayList<>();
    public MyRecyclerView(Context context,List<HomeBean.DataBean> data) {
        this.mcontext= context;
        this.list= (ArrayList<HomeBean.DataBean>) data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.content, null);
        Honner honner = new Honner(view);
        return honner;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
           Honner honner= (Honner) holder;
        honner.text.setText(""+list.get(position).getNews_title());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<HomeBean.DataBean> lists){
        if (lists!=null){
            list.addAll(lists);
        }
    }
    public class Honner extends  RecyclerView.ViewHolder {

        private final TextView text;

        public Honner(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.recycletext);
        }
    }
}
