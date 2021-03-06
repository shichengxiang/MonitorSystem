package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.customes.NoScrollViewPager;
import com.example.shichengxinag.monitorsystem.fragment.GuardFragment;
import com.example.shichengxinag.monitorsystem.fragment.LogFragment;
import com.example.shichengxinag.monitorsystem.fragment.MapFragent;
import com.example.shichengxinag.monitorsystem.fragment.MineFrament;
import com.example.shichengxinag.monitorsystem.fragment.SearchFrament;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by Administrator on 2017/6/27/027.
 */

public class Main2Activity extends FragmentActivity {
    BottomBar mBottomBar;
    NoScrollViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab() {
        mBottomBar = (BottomBar) findViewById(R.id.nav);
        mViewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new TabFragmentsAdapter(getSupportFragmentManager()));
        mViewPager.setNoScroll(true);
        mViewPager.setOffscreenPageLimit(1);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mViewPager.setCurrentItem(getPositionById(tabId));
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.setDefaultTab(getIdByPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(0);
    }

    class TabFragmentsAdapter extends FragmentPagerAdapter {

        public TabFragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 1:
                    fragment = new GuardFragment();
                    break;
                case 2:
                    fragment = new SearchFrament();
                    break;
                case 3:
                    fragment = new MineFrament();
                    break;
                case 4:
                    fragment = LogFragment.newInstance();
                    break;
                default:
                    fragment = MapFragent.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    private int getIdByPosition(int p) {
        int result = 0;
        if (p == 0) {
            result = R.id.tab_map;
        } else if (p == 1) {
            result = R.id.tab_guard;
        } else if (p == 2) {
            result = R.id.tab_search;
        } else if (p == 3) {
            result = R.id.tab_mine;
        } else {
            result = R.id.tab_log;
        }
        return result;
    }

    private int getPositionById(int id) {
        int position = 0;
        if (id == R.id.tab_map) {
            position = 0;
        } else if (id == R.id.tab_guard) {
            position = 1;
        } else if (id == R.id.tab_search) {
            position = 2;
        } else if (id == R.id.tab_mine) {
            position = 3;
        } else {
            position = 4;
        }
        return position;
    }
}
