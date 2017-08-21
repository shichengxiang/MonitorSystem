package com.example.shichengxinag.monitorsystem.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.shichengxinag.monitorsystem.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        MIUISetStatusBarLightMode(getWindow(),true);
        FlymeSetStatusBarLightMode(getWindow(),true);
        initJPush();
        ac = this;
        bind = ButterKnife.bind(this);
        init(savedInstanceState);
    }

    private void initJPush() {
//        PushAgent.getInstance(this).onAppStart();//友盟统计

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        bind.unbind();
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }
    /* * 设置状态栏字体图标为深色，需要MIUIV6以上
   * @param window 需要设置的窗口
   * @param dark 是否把状态栏字体及图标颜色设置为深色
   * @return boolean 成功执行返回true
   * */
    public boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);  //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);   //清除黑色字体
                }
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    /* * 设置状态栏图标为深色和魅族特定的文字风格
    * 可以用来判断是否为Flyme用户
    * @param window 需要设置的窗口
    * @param dark 是否把状态栏字体及图标颜色设置为深色
    * @return boolean 成功执行返回true
    * */
    public boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void startActivity(Class clz) {
        startActivity(new Intent(ac, clz));
    }

    public void displayLoading() {
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

    public void dismissLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }
}
