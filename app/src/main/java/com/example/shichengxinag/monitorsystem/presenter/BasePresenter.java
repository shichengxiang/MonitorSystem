package com.example.shichengxinag.monitorsystem.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public abstract class BasePresenter<T> {
    public T mView;
    public Activity mContext;

    public BasePresenter(T view) {
        this.mView = view;
        if (view instanceof Activity)
            mContext = (Activity) view;
        else if (view instanceof Fragment) {
            mContext = ((Fragment) view).getActivity();
        }
    }

    public void onDestory() {
        mView = null;
        mContext = null;
    }

    public boolean isNetConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }
}
