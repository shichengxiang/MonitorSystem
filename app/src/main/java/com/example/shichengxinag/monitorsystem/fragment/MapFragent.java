package com.example.shichengxinag.monitorsystem.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapFragment;
import com.amap.api.maps.MapView;
import com.amap.api.services.route.DriveRouteResult;
import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.presenter.MapPresenter;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/24/024.
 */

public class MapFragent extends Fragment implements com.example.shichengxinag.monitorsystem.view.MapView {


    private static Fragment fragment;
    MapView mMapView;
    MapPresenter mMapPresenter;
    AMap mAMap;
    private View mRoot;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public static Fragment newInstance() {
        if (fragment == null) {
            synchronized (MapFragment.class) {
                if (fragment == null) {
                    fragment = new MapFragent();
                }
            }
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (mRoot == null) {
            mRoot = layoutInflater.inflate(R.layout.fragment_map, viewGroup, false);
        }
        mMapPresenter = new MapPresenter(this);
        if (mMapView == null)
            mMapView = (MapView) mRoot.findViewById(R.id.mapView);
        mMapView.onCreate(bundle);
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        mMapPresenter = new MapPresenter(this);
        mMapPresenter.initMyLocation(mAMap);
        mMapPresenter.initUISetting(mAMap);
        ButterKnife.bind(this, mRoot);
        return mRoot;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMapView.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onSuccessDriveRoute(DriveRouteResult result, int errorCode) {

    }

    @Override
    public void excuteMothed(int tag) {

    }

}
