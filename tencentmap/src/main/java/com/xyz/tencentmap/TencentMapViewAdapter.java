package com.xyz.tencentmap;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.UiSettings;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.xyz.maplib.map.IMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;
import com.xyz.maplib.map.MarkListener;
import com.xyz.maplib.map.ZoomLevelChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZP on 2018/2/11.
 */

public class TencentMapViewAdapter implements IMapView {

    private MapView mMapView;
    private TencentMap mTencentMapClient;
    private MarkListener markListener;
    private final List<MarkerOptions>markerOptionsList=new ArrayList<>();

    @Override
    public void onCreate(Context context, Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        mMapView.onStart();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void onPause() {
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        mMapView.onStop();
    }

    @Override
    public void onRestart() {
        mMapView.onRestart();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        if(markerOptionsList!=null&&markerOptionsList.size()>0){
            markerOptionsList.clear();
        }
    }

    @Override
    public int getRealMapLayoutId() {
        return R.layout.layout_tmap;
    }

    @Override
    public void setMapView(View view) {
        mMapView = (MapView) view.findViewById(R.id.tmap);
        if (mTencentMapClient == null) {
            mTencentMapClient = mMapView.getMap();
            UiSettings uiSettings = mTencentMapClient.getUiSettings();
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setCompassEnabled(false);
            uiSettings.setScaleViewEnabled(false);
            uiSettings.setLogoPosition(-50);
        }

    }

    @Override
    public LTMarker addMarker(LTMarkerOptions options) {
        MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(options.getIcon()))
                .position(new LatLng(options.getLatitude(), options.getLongitude()))
                .draggable(true);
        Marker marker = mTencentMapClient.addMarker(markerOption);
        LTMarker ltMarker = new LTMarker();
        ltMarker.setMarkerDelegate(new TencentMarkerAdapter(marker));
        return ltMarker;
    }

    @Override
    public void moveCamera(double latitude, double longitude) {
        mTencentMapClient.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.0f));
    }

    @Override
    public void cleanMarker() {
        if(mTencentMapClient!=null) {
            mTencentMapClient.clear();
        }
    }

    @Override
    public void refreshMarker() {
        cleanMarker();
        if(markerOptionsList!=null&&markerOptionsList.size()>0){
            markerOptionsList.clear();
        }
    }

    @Override
    public void setMarkers(List<LTMarkerOptions> optionses) {
        if(markerOptionsList!=null&&markerOptionsList.size()>0){
            markerOptionsList.clear();
        }
        for (LTMarkerOptions options:optionses){
            MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(options.getIcon()))
                    .position(new LatLng(options.getLatitude(), options.getLongitude()))
                    .draggable(true);
            markerOptionsList.add(markerOption);
        }
//        Log.e("tag","markOptionsList.Size:Add"+markerOptionsList.size());
    }

    @Override
    public void showHasMarkers(boolean isShow) {
        if(isShow){
            if(mTencentMapClient!=null){
                mTencentMapClient.clear();
            }
//            Log.e("tag","markOptionsList.Size:Show"+markerOptionsList.size());
            if(markerOptionsList!=null&&markerOptionsList.size()>0){
                for (MarkerOptions markerOptions:markerOptionsList){
                    Marker marker = mTencentMapClient.addMarker(markerOptions);
                }
            }else {
                Log.e("tag","没有覆盖物数据！");
            }
        }else {
            if(mTencentMapClient!=null){//不是现实覆盖物就清空覆盖物
                mTencentMapClient.clear();
            }
        }
    }

    @Override
    public void setMarkerClickTitle(MarkListener markListener) {
        this.markListener=markListener;
        mTencentMapClient.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    public void setZoomControlsEnabled(boolean b) {
        if (mTencentMapClient!=null){
            UiSettings settings= mTencentMapClient.getUiSettings();
            settings.setZoomControlsEnabled(b);
        }

    }

    @Override
    public void getCurrentZoomLevel(final ZoomLevelChangeListener zoomLevelChangeListener) {
        mTencentMapClient.setOnCameraChangeListener(new TencentMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                float zoomLevel=   cameraPosition.zoom;
                zoomLevelChangeListener.getZoomLevel(zoomLevel);

            }

            @Override
            public void onCameraChangeFinished(CameraPosition cameraPosition) {
                float zoomLevel=   cameraPosition.zoom;
                zoomLevelChangeListener.getZoomLevel(zoomLevel);
            }
        });
    }

    @Override
    public void setZoomLevel(float zoom, double lat, double lon) {
        LatLng lng = new LatLng(lat, lon);
        //设置中心点和缩放比例
        if(mTencentMapClient!=null) {
            CameraUpdate cameraSigma =
                    CameraUpdateFactory.newCameraPosition(new CameraPosition(
                            lng, //新的中心点坐标
                            zoom,  //新的缩放级别
                            45f, //俯仰角 0~45° (垂直地图时为0)
                            45f)); //偏航角 0~360° (正北方为0)
            //移动地图
            mTencentMapClient.moveCamera(cameraSigma);
        }
    }

    @Override
    public void setZoomLevel(float zoom) {
        if (mTencentMapClient!=null) {
            mTencentMapClient.moveCamera(CameraUpdateFactory.zoomTo(zoom));
        }
    }


    private TencentMap.OnMarkerClickListener markerClickListener=new TencentMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            MarkerOptions markerOptions=marker.getOptions();
            LatLng latLng=markerOptions.getPosition();
            List<LTMarkerOptions>LitMarkerOptions=new ArrayList<>();
            if(latLng!=null){//找出相同坐标
                for (MarkerOptions options: markerOptionsList){
                     LatLng lng=  options.getPosition();
                     boolean isDrag=options.isDraggable();
                    if(latLng.equals(options.getPosition())){
                        LTMarkerOptions markerOption=  new LTMarkerOptions.Builder().latitude(lng.latitude).longitude(lng.longitude).draggable(isDrag).build();
                        LitMarkerOptions.add(markerOption);
                    }
                }
            }
            if(LitMarkerOptions!=null&&LitMarkerOptions.size()>0){//点击的监听
                markListener.clickMarkTitle(LitMarkerOptions);
            }
            return false;
        }
    };
    public void setMapTraffic(boolean isShow){
        if (mTencentMapClient!=null) {
            mTencentMapClient.setTrafficEnabled(isShow);
        }
    }
}
