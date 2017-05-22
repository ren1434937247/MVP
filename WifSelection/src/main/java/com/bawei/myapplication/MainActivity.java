package com.bawei.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean wifi = NetWorkUtile.isWifi(MainActivity.this);

        View view = View.inflate(MainActivity.this, R.layout.wifi, null);
        dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setView(view);
        dialog.show();
//设置监听
        Button queding = (Button) view.findViewById(R.id.dialog_queding);
        Button quxiao = (Button) view.findViewById(R.id.dialog_quxiao);
        queding.setOnClickListener(this);
        quxiao.setOnClickListener(this);
    }

    public static class NetWorkUtile {

        public static boolean isWifi(Context context){

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null && info.getType() == manager.TYPE_WIFI){
                return true;
            }
            return false;
        }
    }
}
