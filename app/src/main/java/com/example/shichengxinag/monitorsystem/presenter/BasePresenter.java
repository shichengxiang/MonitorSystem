package com.example.shichengxinag.monitorsystem.presenter;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public abstract class BasePresenter<T> {
    public T mView;
    public Context mContext;

    public BasePresenter(T view) {
        this.mView = view;
        if(view instanceof Activity)
            mContext= ((Activity) view).getApplicationContext();
    }
    public void onDestory(){
        mView=null;
    }
}
