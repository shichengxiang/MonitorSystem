package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.fragment.GuardFragment;
import com.example.shichengxinag.monitorsystem.fragment.MapFragent;
import com.example.shichengxinag.monitorsystem.fragment.MineFrament;
import com.example.shichengxinag.monitorsystem.fragment.SearchFrament;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/24/024.
 */

public class MainActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.bottomNavigation)
    BottomNavigationView mBottomNavigationView;
    @Nullable
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initBottom();

    }
    private void initBottom(){
        mBottomNavigationView.addTab(new BottomNavigationItem("地图",R.color.colorAccent,R.drawable.ic_map));
        mBottomNavigationView.addTab(new BottomNavigationItem("我的",R.color.colorAccent,R.drawable.ic_me));
        mViewPager.setAdapter(new TabFramentsPageAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.setUpWithViewPager(mViewPager,new int[]{R.color.blue,R.color.colorAccent,R.color.bg,R.color.colorPrimaryDark},new int[]{R.drawable.ic_map,R.drawable.ic_me,R.drawable.ic_guard,R.drawable.ic_search});
    }
    class TabFramentsPageAdapter extends FragmentPagerAdapter{

        public TabFramentsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
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
