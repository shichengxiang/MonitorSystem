package com.example.shichengxinag.monitorsystem.view;


import com.amap.api.services.route.DriveRouteResult;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public interface MapView extends BaseView {
    void onSuccessDriveRoute(DriveRouteResult result, int errorCode);
    void excuteMothed(int tag);
}
