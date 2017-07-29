package com.example.shichengxinag.monitorsystem;

import android.app.Application;

import com.example.shichengxinag.monitorsystem.utils.Log;

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
        Log.d("注册推送");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Log.d("推送完成");

    }
}
