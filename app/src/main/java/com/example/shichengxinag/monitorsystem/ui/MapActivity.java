package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.presenter.MapPresenter;

import butterknife.BindView;

/**
 * Created by shichengxinag on 2017/6/16.
 */

public class MapActivity extends BaseActivity implements com.example.shichengxinag.monitorsystem.view.MapView{

    @BindView(R.id.mapView)
    MapView mMapView;
    AMap mAMap;
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
        mMapPresenter=new MapPresenter(this);
        mMapPresenter.initMyLocation(mAMap);
        mMapPresenter.initUISetting(mAMap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
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


    @Override
    public void onError(String msg) {
        toast(msg);
    }
}
