package com.example.shichengxinag.monitorsystem.presenter;

import android.view.View;

import com.example.shichengxinag.monitorsystem.nets.MyCallBack;
import com.example.shichengxinag.monitorsystem.nets.x;
import com.example.shichengxinag.monitorsystem.view.AccountView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/19/019.
 */

public class AccountPresenter extends BasePresenter<AccountView> {
    public AccountPresenter(AccountView view) {
        super(view);
    }
//    public void login(View view,String name, String pwd){
//        x.http().login(name,pwd).enqueue(new MyCallBack<String>() {
//            @Override
//            public void onNotConnected() {
//
//            }
//
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }
}
