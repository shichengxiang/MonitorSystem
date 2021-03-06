package com.example.shichengxinag.monitorsystem.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.AlphaAnimation;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.MapActivity;
import com.example.shichengxinag.monitorsystem.ui.tables.SelectStaffActivity;
import com.example.shichengxinag.monitorsystem.view.MapView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public class MapPresenter extends BasePresenter<MapView> implements AMap.OnMyLocationChangeListener, AMap.OnInfoWindowClickListener, RouteSearch.OnRouteSearchListener {

    MyLocationStyle myLocationStyle;
    AMap mAMap;
    RouteSearch mRouteSearch;//线路导航
    private LatLonPoint mStartPoint;

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
        if(aMap!=null){
            aMap.getUiSettings().setMyLocationButtonEnabled(true);
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.setMyLocationEnabled(true);
            aMap.setOnMyLocationChangeListener(this);
            aMap.setOnInfoWindowClickListener(this);
        }
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
        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint, endP);
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
    public class MyInfoWindowAdapter implements AMap.InfoWindowAdapter{
        View infoWindow=null;

        @Override
        public View getInfoWindow(Marker marker) {
            if(infoWindow==null){
                infoWindow= LayoutInflater.from(mContext).inflate(R.layout.layout_infowindow,null);
            }
            TextView tv_state= (TextView) infoWindow.findViewById(R.id.tv_state);
            TextView tv_dispatch= (TextView) infoWindow.findViewById(R.id.tv_dispatch);
            View mV_toNav=infoWindow.findViewById(R.id.tv_toNav);
            tv_dispatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, SelectStaffActivity.class));
                }
            });
            mV_toNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mView.excuteMothed(MapActivity.GOTONAV);
                }
            });

            return infoWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    public void addMarkers(AMap aMap) {
        aMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
        ArrayList<MarkerOptions> list = new ArrayList<>();
        for (float i = 0; i < 4; i++) {
            MarkerOptions opt = new MarkerOptions();
            opt.position(new LatLng(39.992929 - i / 1000f, 116.337626 - i / 100f))
//                    .title("第" + i + "水库")
//                    .snippet("水位：异常\n水量：异常")
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(mContext.getResources(), R.mipmap.ic_locred)));
            list.add(opt);
        }
        list.get(0).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.mipmap.ic_locgreen)));
        list.get(1).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.mipmap.ic_locgreen)));
        list.get(2).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.mipmap.ic_locred)));
        list.get(3).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.mipmap.ic_locred)));
        ArrayList<Marker> markers = aMap.addMarkers(list, true);
//        LatLng latLng3 = new LatLng(39.915168, 118.403875);
//        MarkerOptions markerOptions3 = new MarkerOptions();
//        markerOptions.position(latLng3)
//                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                        .decodeResource(mContext.getResources(), R.drawable.ic_alarm_purple)))
//                .title("天安门水库")
//                .snippet("水位：正常\n水量：100");
//        aMap.addMarker(markerOptions3);
//        LatLng latLng4 = new LatLng(39.915168, 119.403875);
//        MarkerOptions markerOptions4 = new MarkerOptions();
//        markerOptions.position(latLng4)
//                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                        .decodeResource(mContext.getResources(), R.drawable.ic_alarm_green)))
//                .title("天安门水库")
//                .snippet("水位：正常\n水量：100");
//        aMap.addMarker(markerOptions4);

        final AlphaAnimation ani = new AlphaAnimation(1f, 0.1f);
        ani.setDuration(400);

        for (int i = 0; i < markers.size(); i++) {
            final Marker marker = markers.get(i);
            marker.setAnimation(ani);
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
//            ani.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart() {
//
//                }
//
//                @Override
//                public void onAnimationEnd() {
//                    marker.setAnimation(ani2);
//                    marker.startAnimation();
//
//                }
//            });
//            ani2.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart() {
//
//                }
//
//                @Override
//                public void onAnimationEnd() {
//                    marker.setAnimation(ani);
//                    marker.startAnimation();
//                }
//            });
        }

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
        mStartPoint = new LatLonPoint(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        mView.onError(marker.getSnippet());
//        mView.excuteMothed(MapActivity.DISPLAY_BOTTOM);
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
