package com.example.shichengxinag.monitorsystem.view;

import com.amap.api.services.route.DriveRouteResult;
import com.example.shichengxinag.monitorsystem.view.BaseView;

/**
 * Created by Administrator on 2017/6/17/017.
 */

public interface MapView extends BaseView {
    void onSuccessDriveRoute(DriveRouteResult result, int errorCode);
}
