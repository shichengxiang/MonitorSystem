package com.example.shichengxinag.monitorsystem.presenter;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.Environment;
import android.view.animation.LinearInterpolator;

import com.amap.api.maps.AMap;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.AlphaAnimation;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.shichengxinag.monitorsystem.ui.MapActivity;
import com.example.shichengxinag.monitorsystem.view.MapView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public class MapPresenter extends BasePresenter<MapView> implements AMap.OnMyLocationChangeListener, AMap.OnInfoWindowClickListener, RouteSearch.OnRouteSearchListener {

    MyLocationStyle myLocationStyle;
    AMap mAMap;
    RouteSearch mRouteSearch;//线路导航

    public MapPresenter(MapView view) {
        super(view);
    }

    /**
     * 定位蓝点初始化
     *
     * @param aMap
     */
    public void initMyLocation(AMap aMap) {
        myLocationStyle = new MyLocationStyle();
//        myLocationStyle.interval(2000);//2s定位一次
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.showMyLocation(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.setOnMyLocationChangeListener(this);
        aMap.setOnInfoWindowClickListener(this);
    }

    /**
     * 控件交互优化
     *
     * @param aMap
     */
    public void initUISetting(AMap aMap) {
        UiSettings settings = aMap.getUiSettings();
        settings.setZoomControlsEnabled(true);//缩放
        settings.setCompassEnabled(true);//指南针
        settings.setScaleControlsEnabled(true);//缩放
//        initScreeShot(aMap);
        addMarkers(aMap);
    }

    public void routeNavigator(AMap aMap, LatLonPoint startP, LatLonPoint endP) {
        mRouteSearch = new RouteSearch(mContext);
        mRouteSearch.setRouteSearchListener(this);
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startP, endP);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DRIVING_SINGLE_DEFAULT, null, null, "");
        mRouteSearch.calculateDriveRouteAsyn(query);

    }

    /**
     * 截屏
     *
     * @param aMap
     */
    public void initScreeShot(AMap aMap) {
        aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int status) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if (null == bitmap) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(
                            Environment.getExternalStorageDirectory() + "/荆门_"
                                    + sdf.format(new Date()) + ".png");
                    boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (b)
                        buffer.append("截屏成功 ");
                    else {
                        buffer.append("截屏失败 ");
                    }
                    if (status != 0)
                        buffer.append("地图渲染完成，截屏无网格");
                    else {
                        buffer.append("地图未渲染完成，截屏有网格");
                    }
                    mView.onError(buffer.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void addMarkers(AMap aMap) {
        LatLng latLng = new LatLng(39.915168, 116.403875);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng)
                .title("天安门水库")
                .snippet("水位：正常\n水量：100");
        final Marker mark = aMap.addMarker(markerOptions);
        final AlphaAnimation ani = new AlphaAnimation(1f, 0.1f);
        ani.setDuration(200);
        final AlphaAnimation ani2 = new AlphaAnimation(0.1f, 1f);
        ani2.setDuration(200);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                mark.setAnimation(ani2);
                mark.startAnimation();
            }
        });
        ani2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                mark.setAnimation(ani);
                mark.startAnimation();
            }
        });
        mark.setAnimation(ani);
        mark.startAnimation();

        //test
        LatLng l2 = new LatLng(31.035516, 112.199266);
        MarkerOptions opt = new MarkerOptions();
        opt.position(l2)
                .title("荆门水库")
                .snippet("水位：正常\n水量：100");
        final Marker marker = aMap.addMarker(opt);
        //动画
        ScaleAnimation animation = new ScaleAnimation(0.8f, 1f, 0.8f, 1.0f);
        long duration = 1000L;
        animation.setDuration(duration);
        animation.setInterpolator(new LinearInterpolator());

        marker.setAnimation(animation);
        marker.startAnimation();
        marker.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                marker.startAnimation();
            }
        });
        //自定义图标
        //点击事件
//        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                return false;
//            }
//        });
    }

    /**
     * 获取定位经纬度
     *
     * @param location
     */
    @Override
    public void onMyLocationChange(Location location) {
        mView.onError("定位经纬度：" + location.getLongitude() + "  " + location.getLatitude());
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        mView.onError(marker.getSnippet());
        mView.excuteMothed(MapActivity.DISPLAY_BOTTOM);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        mView.onSuccessDriveRoute(result, errorCode);
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }
}
