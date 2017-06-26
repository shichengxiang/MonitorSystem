package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.fragment.GuardFragment;
import com.example.shichengxinag.monitorsystem.fragment.MapFragent;
import com.example.shichengxinag.monitorsystem.fragment.MineFrament;
import com.example.shichengxinag.monitorsystem.fragment.SearchFrament;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/26/026.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.nav)
    BottomBar mBottomBar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        initTab();
    }
    private void initTab(){
        mViewPager.setAdapter(new TabFragmentsAdapter(getSupportFragmentManager()));
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position=0;
                if (tabId==R.id.tab_map){
                    position=0;
                }else if(tabId==R.id.tab_guard){
                    position=1;
                }else if(tabId==R.id.tab_search){
                    position=2;
                }else{
                    position=3;
                }
                mViewPager.setCurrentItem(position);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.setDefaultTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                    fragment=new GuardFragment();
                    break;
                case 2:
                    fragment=new SearchFrament();
                    break;
                case 3:
                    fragment=new MineFrament();
                    break;
                default:
                    fragment=new MapFragent();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
