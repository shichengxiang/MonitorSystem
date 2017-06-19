package com.example.shichengxinag.monitorsystem.presenter;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.animation.LinearInterpolator;

import com.amap.api.maps.AMap;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.example.shichengxinag.monitorsystem.nets.x;
import com.example.shichengxinag.monitorsystem.view.MapView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public class MapPresenter extends BasePresenter<MapView> {

    MyLocationStyle myLocationStyle;
    AMap mAMap;

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
        aMap.addMarker(markerOptions);
        //test
        LatLng l2 = new LatLng(31.035516, 112.199266);
        MarkerOptions opt = new MarkerOptions();
        opt.position(l2)
                .title("荆门水库")
                .snippet("水位：正常\n水量：100");
        final Marker marker = aMap.addMarker(opt);
        //动画
        ScaleAnimation animation = new ScaleAnimation(0.8f,1f,0.8f,1.0f);
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
}
