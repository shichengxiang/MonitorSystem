package com.example.shichengxinag.monitorsystem.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.example.shichengxinag.monitorsystem.R;
import com.umeng.message.PushAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shichengxinag on 2017/6/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Unbinder bind;
    private AlertDialog mLoadingDialog;
    private Activity ac;

    public abstract int getLayout();

    public abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initJPush();
        ac=this;
        bind = ButterKnife.bind(this);
        init(savedInstanceState);
    }
    private void initJPush(){
        PushAgent.getInstance(this).onAppStart();//友盟统计

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        bind.unbind();
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    <T> void startActivity(final Class<T> clz) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ac, clz));
            }
        }, 400);
    }

    void displayLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new AlertDialog.Builder(this, R.style.DialogWrap)
                    .setView(R.layout.layout_loading)
                    .setCancelable(true)
                    .create();
            mLoadingDialog.getWindow().setGravity(Gravity.CENTER);
//            mLoadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (!mLoadingDialog.isShowing())
            mLoadingDialog.show();
    }

    void dismissLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }
}
