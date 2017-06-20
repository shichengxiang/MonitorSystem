package com.example.shichengxinag.monitorsystem.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.shichengxinag.monitorsystem.R;

/**
 * Created by shichengxinag on 2017/6/20.
 */

public class DialogUtils {

    public static void showPopWindow(Context context, View view) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_topfilter, null);
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popwindow_anim);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.showAsDropDown(view);
    }
}
