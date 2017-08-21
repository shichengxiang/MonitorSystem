package com.example.shichengxinag.monitorsystem;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by shichengxinag on 2017/7/3.
 */

public class MyApp extends Application {

//    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";

    @Override
    public void onCreate() {
        super.onCreate();
//        Logger.addLogAdapter(new AndroidLogAdapter());
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);

    }
}
