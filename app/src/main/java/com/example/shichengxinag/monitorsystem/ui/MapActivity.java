package com.example.shichengxinag.monitorsystem.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.presenter.MapPresenter;
import com.example.shichengxinag.monitorsystem.ui.map.AMapUtil;
import com.example.shichengxinag.monitorsystem.ui.map.route.DrivingRouteOverLay;
import com.example.shichengxinag.monitorsystem.ui.notification.NewsActivity;
import com.example.shichengxinag.monitorsystem.ui.tables.TableActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shichengxinag on 2017/6/16.
 */

public class MapActivity extends BaseActivity implements com.example.shichengxinag.monitorsystem.view.MapView, View.OnClickListener {

    private static boolean isMark = true;
    public static final int DISPLAY_BOTTOM = 0x11;


    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.rootView)
    View rootView;
    AMap mAMap;
    @BindView(R.id.searchView)
    FloatingSearchView mSearchView;


    //    导航
    @BindView(R.id.area_bottom_positiondetail)
    View area_bottom_positiondetail;
    @BindView(R.id.click_toNav)
    View click_toNav;
    @BindView(R.id.area_topNavType)
    View area_topNavType;

    private LatLonPoint mStartPoint = new LatLonPoint(39.942295, 116.335891);//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = new LatLonPoint(39.995576, 116.481288);//终点，116.481288,39.995576

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
        initView();

    }

    private void initView() {
        final List<Suggestion> suggestions = Arrays.asList(new Suggestion("zhangsan"), new Suggestion("lisi"));
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                mSearchView.swapSuggestions(suggestions);

            }
        });
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @OnClick({R.id.click_msg, R.id.click_displayMenu, R.id.click_toNav})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.click_msg:
                startActivity(NewsActivity.class);
                break;
            case R.id.click_displayMenu:
                displayMenuWindow();
                break;
            case R.id.click_toNav:
                mMapPresenter.routeNavigator(mAMap, mStartPoint, mEndPoint);
                break;
        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    private void displayMenuWindow() {
        if (menuWindow == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_menu, null);
            menuWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            menuWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
            view.findViewById(R.id.click_toCenter).setOnClickListener(this);
            view.findViewById(R.id.click_toGuard).setOnClickListener(this);
            view.findViewById(R.id.click_toLimit).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_toCenter:
                startActivity(CenterActivity.class);
                menuWindow.dismiss();
                break;
            case R.id.click_toGuard:
                startActivity(TableActivity.class);
                menuWindow.dismiss();
                break;
            case R.id.click_toLimit:
                startActivity(TableActivity.class);
                menuWindow.dismiss();
                break;
        }
    }

    @Override
    public void onSuccessDriveRoute(DriveRouteResult result, int errorCode) {
        mAMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            DriveRouteResult mDriveRouteResult;
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    DrivingRouteOverLay drivingRouteOverlay = new DrivingRouteOverLay(this, mAMap, drivePath, mDriveRouteResult.getStartPos(), mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
//                    mBottomLayout.setVisibility(View.VISIBLE);
                    int dis = (int) drivePath.getDistance();
                    int dur = (int) drivePath.getDuration();
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
//                    mRotueTimeDes.setText(des);
//                    mRouteDetailDes.setVisibility(View.VISIBLE);
                    int taxiCost = (int) mDriveRouteResult.getTaxiCost();
                    toast("时间：" + des + " 费用：" + taxiCost);
//                    mRouteDetailDes.setText("打车约" + taxiCost + "元");
//                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(mContext,
//                                    DriveRouteDetailActivity.class);
//                            intent.putExtra("drive_path", drivePath);
//                            intent.putExtra("drive_result",
//                                    mDriveRouteResult);
//                            startActivity(intent);
//                        }
//                    });
                    displayTopNavType();
                } else if (result != null && result.getPaths() == null) {
                    toast("无结果");
                }

            } else {
//                ToastUtil.show(mContext, R.string.no_result);
            }
        } else {
            toast(errorCode + "");
        }
    }

    @Override
    public void excuteMothed(int tag) {
        if (tag == DISPLAY_BOTTOM) {
            displayBottomPositionDetail();
        }
    }

    @SuppressLint("ParcelCreator")
    public class Suggestion implements SearchSuggestion {
        String body;

        public Suggestion(String body) {
            this.body = body;
        }

        @Override
        public String getBody() {
            return body;
        }

        @Override
        public int describeContents() {
            return R.mipmap.ic_launcher;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }

    /**
     * 导航底部显示
     */
    public void displayBottomPositionDetail() {
        area_bottom_positiondetail.setVisibility(View.VISIBLE);
    }

    public void displayTopNavType() {
        area_topNavType.setVisibility(View.VISIBLE);
    }

    private void reloadUI() {
        area_bottom_positiondetail.setVisibility(View.GONE);
        area_topNavType.setVisibility(View.GONE);
        mAMap.reloadMap();
        mMapPresenter.initMyLocation(mAMap);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isMark)
                return super.onKeyDown(keyCode, event);
            else {
                reloadUI();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
