package com.xyz.maplib.map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.List;

/**
 * Created by ZP on 2018/2/9.
 */

public interface IMapView {

    void onCreate(Context context, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onSaveInstanceState(Bundle outState);

    void onPause();

    void onStop();

    void onRestart();

    void onDestroy();

    int getRealMapLayoutId();

    void setMapView(View view);

    LTMarker addMarker(LTMarkerOptions options);

    void moveCamera(double latitude, double longitude);


    void cleanMarker();//清除覆盖物
    void refreshMarker();//刷新覆盖物
    void setMarkers(List<LTMarkerOptions> optionses);//设置覆盖物数据
    void showHasMarkers(boolean isShow);//是否显示覆盖物数据
    void setMarkerClickTitle(MarkListener markListener);//设置覆盖物点击的监听
    void setZoomControlsEnabled(boolean b);//设置放大缩小图标的显示
    void getCurrentZoomLevel(ZoomLevelChangeListener zoomLevelChangeListener);
    void setZoomLevel(float zoom, double lat, double lon);//带中心点的设置等级
    void setZoomLevel(float zoom);//不带中心点的
    void setMapTraffic(boolean isShow);//路况是否显示
}
