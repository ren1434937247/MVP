package com.bawei.myapplication.view.iview;

import com.bawei.myapplication.model.Bean.HomeBean;

/**
 * Author:任昕彤
 * DESC:
 */

public interface IHomeView {
     void callbackStr(HomeBean data);
     void callbackErr(String errMsg,int errCode);

}
