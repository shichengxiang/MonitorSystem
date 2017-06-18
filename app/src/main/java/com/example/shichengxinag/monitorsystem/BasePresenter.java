package com.example.shichengxinag.monitorsystem;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public abstract class BasePresenter<T> {
    public T mView;

    public BasePresenter(T view) {
        this.mView = view;
    }
    public void onDestory(){
        mView=null;
    }
}
