package com.example.shichengxinag.monitorsystem.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.services.route.DriveRouteResult;
import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.presenter.MapPresenter;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/24/024.
 */

public class MapFragent extends Fragment implements com.example.shichengxinag.monitorsystem.view.MapView {


    MapView mMapView;
    MapPresenter mMapPresenter;
    AMap mAMap;
    private View mRoot;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (mRoot == null)
            mRoot = layoutInflater.inflate(R.layout.fragment_map, viewGroup, false);
//        try {
//            MapsInitializer.initialize(getContext());
            mMapPresenter = new MapPresenter(this);
        mMapView = (MapView) mRoot.findViewById(R.id.mapView);
        mMapView.onCreate(bundle);
        if (mAMap == null) {
                mAMap = mMapView.getMap();
            }
            mMapPresenter = new MapPresenter(this);
            mMapPresenter.initMyLocation(mAMap);
            mMapPresenter.initUISetting(mAMap);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
        ButterKnife.bind(this, mRoot);
        return mRoot;
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
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
