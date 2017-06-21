package com.example.shichengxinag.monitorsystem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shichengxinag on 2017/6/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    Unbinder bind;

    public abstract int getLayout();

    public abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bind = ButterKnife.bind(this);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    <T> void startActivity(Class<T> clz){
        startActivity(new Intent(this,clz));
    }
    void displayLoading(){
//        AlertDialog.Builder(this);
    }
}
