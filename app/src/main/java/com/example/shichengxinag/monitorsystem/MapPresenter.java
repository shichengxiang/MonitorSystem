package com.example.shichengxinag.monitorsystem;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public class MapPresenter extends BasePresenter<MapView> {

    MyLocationStyle myLocationStyle;
    AMap mAMap;
    public MapPresenter(MapView view) {
        super(view);
    }
    public void initMyLocation(){
        myLocationStyle=new MyLocationStyle();
        myLocationStyle.interval(2000);//2s定位一次

    }
}
