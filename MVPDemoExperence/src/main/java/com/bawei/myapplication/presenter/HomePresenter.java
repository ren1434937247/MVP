package com.bawei.myapplication.presenter;

import android.util.Log;
import android.widget.ImageView;

import com.bawei.myapplication.model.Bean.HomeBean;
import com.bawei.myapplication.view.iview.IHomeView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Author:任昕彤
 * DESC:
 */

public class HomePresenter extends BasePresenter {
    private IHomeView ihomeview;

    public void getcontent() {
        String url = "http://api.expoon.com/AppNews/getNewsList/type/2/p/1";
        RequestParams requesrparms = new RequestParams("http://api.expoon.com/AppNews/getNewsList/type/2/p/1");
        requesrparms.setUri(url);
        x.http().get(requesrparms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(result, HomeBean.class);
                ihomeview.callbackStr(homeBean);
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
//    public void uploadImage(String url,String filePath) {
//        RequestParams params = new RequestParams(url);
//
//        //params.setAsJsonContent(true);
//        List<KeyValue> list = new ArrayList<>();
//        list.add(new KeyValue("file", new File(filePath)));
//        //list.add(new KeyValue("parameters", json.toString()));
//        MultipartBody body = new MultipartBody(list, "UTF-8");
//        params.setMultipart(true);
//        params.setRequestBody(body);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("myMessage"," "+result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.e("myMessage"," "+ex.getMessage());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//}

public void getimage(ImageView imageView,String url){
       x.image().bind(imageView,url);
    }
    public void attachView(IHomeView homeview){
        ihomeview=homeview;
    }
}
