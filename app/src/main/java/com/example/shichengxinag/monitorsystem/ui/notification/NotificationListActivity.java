package com.example.shichengxinag.monitorsystem.ui.notification;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.ScaleAnimation;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;
import com.example.shichengxinag.monitorsystem.utils.DialogUtils;

import butterknife.BindView;

/**
 * Created by shichengxinag on 2017/6/20.
 */

public class NotificationListActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    View toolbar;

    @Override
    public int getLayout() {
        return R.layout.activity_notificationlist;
    }

    @Override
    public void init(Bundle savedInstanceState) {
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
                DialogUtils.showPopWindow(NotificationListActivity.this, toolbar);
            }
        }, 1000);
    }
}
