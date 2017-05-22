package com.bawei.myapplication.application;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Author:任昕彤
 * DESC:
 */

public class MyApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
