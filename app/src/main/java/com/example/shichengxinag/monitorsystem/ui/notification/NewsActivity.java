package com.example.shichengxinag.monitorsystem.ui.notification;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shichengxinag on 2017/7/6.
 */

public class NewsActivity extends BaseActivity {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private String[] mTitles={"故障消息","派单消息","预警消息"};
    @Override
    public int getLayout() {
        return R.layout.activity_news;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initTabs();
    }

    private void initTabs() {
//        mTabLayout.addTab(mTabLayout.newTab().setText("故障消息"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("派单消息"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("预警消息"));
        mViewPager.setAdapter(new NewsPageAdapter(this));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private class NewsPageAdapter extends PagerAdapter {
        List<View> mViews = new ArrayList<>();
        private View mView1, mView2, mView3;

        public NewsPageAdapter(Context context) {
            mView1 = LayoutInflater.from(context).inflate(R.layout.item_newstab, null);
            mView2 = LayoutInflater.from(context).inflate(R.layout.item_newstab, null);
            mView3 = LayoutInflater.from(context).inflate(R.layout.item_newstab, null);
            mViews.add(mView1);
            mViews.add(mView2);
            mViews.add(mView3);
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
