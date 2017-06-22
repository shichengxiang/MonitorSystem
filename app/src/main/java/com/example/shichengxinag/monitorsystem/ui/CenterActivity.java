package com.example.shichengxinag.monitorsystem.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.shichengxinag.monitorsystem.R;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/21/021.
 */

public class CenterActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.gridView)
    GridView mGridView;


    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    public int getLayout() {
        return R.layout.activity_center;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initTop();
        initGridView();
    }

    private void initTop() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    Log.d("===", Math.abs(verticalOffset) + "offset,  range=" + appBarLayout.getTotalScrollRange());
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mCollapsingToolbarLayout.setTitle("");//设置title不显示
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }

                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                    iv_head.setScaleX(((float) appBarLayout.getTotalScrollRange() - (float) Math.abs(verticalOffset)) / (float) appBarLayout.getTotalScrollRange());
                    iv_head.setScaleY(((float) appBarLayout.getTotalScrollRange() - (float) Math.abs(verticalOffset)) / (float) appBarLayout.getTotalScrollRange());
                }
            }
        });
    }

    private void initGridView() {
        String[] names = {"足迹", "日志", "考核", "日志", "考核", "日志", "考核", "日志", "考核"};
        int[] imgs = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", imgs[i]);
            map.put("id", names[i]);
            list.add(map);
        }
        mGridView.setAdapter(new SimpleAdapter(this, list, R.layout.item_grid_center, new String[]{"img", "id"}, new int[]{R.id.item_img, R.id.item_txt}));
    }
}
