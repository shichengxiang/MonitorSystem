package com.example.shichengxinag.monitorsystem.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/6/28/028.
 */

public class U {
    /**
     * 设置状态栏
     * @param context
     * @param view
     */
    public static void setTopView(Context context,View view){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height=getStatusBarHeight(context);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return (int) context.getResources().getDimension(identifier);
        }
        return 0;
    }
}
