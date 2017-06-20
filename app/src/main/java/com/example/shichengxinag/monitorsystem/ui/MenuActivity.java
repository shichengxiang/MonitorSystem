package com.example.shichengxinag.monitorsystem.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.notification.NotificationListActivity;
import com.szugyi.circlemenu.view.CircleLayout;

import butterknife.BindView;

/**
 * Created by shichengxinag on 2017/6/20.
 */

public class MenuActivity extends BaseActivity {

    @BindView(R.id.circleMenu)
    CircleLayout mCircleLayout;

    @Override
    public int getLayout() {
        return R.layout.activity_menu;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initMenu();
    }

    private void initMenu() {
//        TextView tv=new TextView(this);
//        tv.setText("first");
//        mCircleLayout.addView(tv,0);
//        TextView tv2=new TextView(this);
//        tv.setText("second");
//        mCircleLayout.addView(tv2,1);
//        TextView tv3=new TextView(this);
//        tv.setText("third");
//        mCircleLayout.addView(tv3,2);
//        TextView tv4=new TextView(this);
//        tv.setText("fourth");
//        mCircleLayout.addView(tv4,3);
        mCircleLayout.setOnItemClickListener(new CircleLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                switch (view.getId()) {
                    case R.id.toMonitor:
                        break;
                    case R.id.toNotifications:
                        startActivity(NotificationListActivity.class);
                        break;
                    case R.id.toSafeGuard:
                        break;
                    case R.id.toCenter:
                        break;
                }

            }
        });
        animationMenu();
    }

    /**
     * 菜单进入动画
     */
    private void animationMenu(){
        PropertyValuesHolder holder1=PropertyValuesHolder.ofFloat("scaleX",0f,1f);
        PropertyValuesHolder holder2=PropertyValuesHolder.ofFloat("scaleY",0f,1f);
        ObjectAnimator.ofPropertyValuesHolder(mCircleLayout,holder1,holder2)
                .setDuration(1000)
                .start();
    }
}
