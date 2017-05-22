package com.bawei.myapplication.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myapplication.R;
import com.bawei.myapplication.model.Bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter {
    private Context mcontext;
     private ArrayList<HomeBean.DataBean> data=new ArrayList<>();
    public MyRecyclerAdapter(Context context){
        this.mcontext=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.layout_bj, null);
        MyHonner myhonner=new MyHonner(view);
        return myhonner;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         MyHonner myhonner= (MyHonner) holder;
         myhonner.text1.setText("       "+data.get(position).getNews_title());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<HomeBean.DataBean> datas) {

        if (datas!=null){
            data.addAll(datas);
        }
    }


    class MyHonner extends RecyclerView.ViewHolder{

        private final TextView text1;

//        public void setData(ArrayList<String> datas){
//
//        }
        public void setData(List<HomeBean.DataBean> datas) {

        }

        public MyHonner(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.text1);
           // TextView text3= (TextView) itemView.findViewById(R.id.text3);
        }
    }
}
