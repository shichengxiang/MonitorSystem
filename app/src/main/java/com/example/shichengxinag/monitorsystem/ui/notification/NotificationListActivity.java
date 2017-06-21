package com.example.shichengxinag.monitorsystem.ui.notification;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;
import com.example.shichengxinag.monitorsystem.utils.DialogUtils;

import butterknife.BindView;

/**
 * Created by shichengxinag on 2017/6/20.
 */

public class NotificationListActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    View toolbar;
    PopupWindow mPopupWindow;
    GestureDetectorCompat mGestureDetector;

    @Override
    public int getLayout() {
        return R.layout.activity_notificationlist;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initGesture();
    }

    @Override
    protected void onResume() {
        super.onResume();
        animationFilterView();
    }

    void animationFilterView() {
//        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f, 1, 0f);
//        animation.setDuration(600);
//        v_topFilter.startAnimation(animation);
//        v_topFilter.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayPopWindow();
            }
        }, 400);
    }

    void initGesture() {
        mGestureDetector = new GestureDetectorCompat(this,new MyGestureListener());
    }

    void displayPopWindow() {
        if (mPopupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.layout_topfilter, null);
            mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setAnimationStyle(R.style.popwindow_anim);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        }
        if (!mPopupWindow.isShowing())
            mPopupWindow.showAsDropDown(toolbar);
    }
    class MyGestureListener extends SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("===",e.getY()+"位置");
            return super.onDown(e);
        }
    }
}
