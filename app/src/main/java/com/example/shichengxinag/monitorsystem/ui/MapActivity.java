package com.example.shichengxinag.monitorsystem.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.presenter.MapPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shichengxinag on 2017/6/16.
 */

public class MapActivity extends BaseActivity implements com.example.shichengxinag.monitorsystem.view.MapView {

    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.rootView)
    View rootView;
    AMap mAMap;
    private PopupWindow menuWindow;
    private MapPresenter mMapPresenter;

    @Override
    public int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        if (mAMap == null)
            mAMap = mMapView.getMap();
        mMapPresenter = new MapPresenter(this);
        mMapPresenter.initMyLocation(mAMap);
        mMapPresenter.initUISetting(mAMap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @OnClick({R.id.tv_msg})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_msg:
                displayMenuWindow();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    private void displayMenuWindow() {
        if (menuWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_menu, null);
            menuWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            menuWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88000000")));
            menuWindow.setOutsideTouchable(true);
            menuWindow.setAnimationStyle(R.style.popwindow_anim_left);
        }
        if (!menuWindow.isShowing())
            menuWindow.showAtLocation(rootView, Gravity.CENTER_VERTICAL | Gravity.RIGHT, 0, 0);

    }


    @Override
    public void onError(String msg) {
        toast(msg);
    }
}
