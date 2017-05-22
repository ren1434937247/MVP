package com.bawei.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bawei.myapplication.bean.HomeBean;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyAdapter extends BaseAdapter {
    private Context mcontext;
    private ArrayList<HomeBean.DataBean> list=new ArrayList<>();
    public MyAdapter(Context context,List<HomeBean.DataBean> list){
        this.mcontext=context;
        this.list=list;
    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
