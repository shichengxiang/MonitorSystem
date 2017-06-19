package com.example.shichengxinag.monitorsystem.nets;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/6/20/020.
 */

public interface MyCallBack<T> extends Callback<T>{
    void onNotConnected();
}
