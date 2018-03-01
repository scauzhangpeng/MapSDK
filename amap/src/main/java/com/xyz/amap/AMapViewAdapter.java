package com.xyz.amap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.xyz.maplib.map.IMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;
import com.xyz.maplib.map.MarkListener;
import com.xyz.maplib.map.ZoomLevelChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZP on 2018/2/9.
 */

public class AMapViewAdapter implements IMapView {

    private MapView mMapView;
    private AMap mAMapClient;
    private final List<MarkerOptions> markerOptionsList = new ArrayList<>();
    private MarkListener markListener;

    @Override
    public void onCreate(Context context, Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        mMapView.onPause();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        if (markerOptionsList != null && markerOptionsList.size() > 0) {
            markerOptionsList.clear();
        }
    }

    @Override
    public int getRealMapLayoutId() {
        return R.layout.layout_amap;
    }

    @Override
    public void setMapView(View view) {
        mMapView = (MapView) view.findViewById(R.id.amap);
        if (mAMapClient == null) {
            mAMapClient = mMapView.getMap();
            mAMapClient.setMyLocationEnabled(true);
        }
    }


    @Override
    public LTMarker addMarker(LTMarkerOptions options) {
        MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(options.getIcon()))
                .position(new LatLng(options.getLatitude(), options.getLongitude()))
                .draggable(true);
        Marker marker = mAMapClient.addMarker(markerOption);
        LTMarker ltMarker = new LTMarker();
        ltMarker.setMarkerDelegate(new AMapMarkerAdapter(marker));
        return ltMarker;
    }

    @Override
    public void moveCamera(double latitude, double longitude) {
        mAMapClient.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
    }

    @Override
    public void cleanMarker() {
        if (mAMapClient != null) {
            mAMapClient.clear();//清空所有的覆盖物
        }
    }

    @Override
    public void refreshMarker() {
        cleanMarker();
        if (markerOptionsList != null && markerOptionsList.size() > 0) {
            markerOptionsList.clear();
        }
    }

    @Override
    public void setMarkers(List<LTMarkerOptions> optionses) {
        if (markerOptionsList != null && markerOptionsList.size() > 0) {
            markerOptionsList.clear();
        }
        for (LTMarkerOptions options : optionses) {
            MarkerOptions markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(options.getIcon()))
                    .position(new LatLng(options.getLatitude(), options.getLongitude()))
                    .draggable(true);
            markerOptionsList.add(markerOption);
        }
    }

    @Override
    public void showHasMarkers(boolean isShow) {
        if (isShow) {
            if (mAMapClient != null) {
                mAMapClient.clear();
            }
            if (markerOptionsList != null && markerOptionsList.size() > 0) {
                for (MarkerOptions markerOptions : markerOptionsList) {
                    Marker marker = mAMapClient.addMarker(markerOptions);
                }
            } else {
                Log.e("tag", "没有覆盖物数据！");
            }
        } else {
            if (mAMapClient != null) {//不是现实覆盖物就清空覆盖物
                mAMapClient.clear();
            }
        }
    }

    @Override
    public void setMarkerClickTitle(MarkListener markListener) {
        this.markListener = markListener;
        mAMapClient.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    public void setZoomControlsEnabled(boolean b) {
          if (mAMapClient!=null){
              UiSettings settings= mAMapClient.getUiSettings();
              settings.setZoomControlsEnabled(b);
          }

    }

    @Override
    public void getCurrentZoomLevel(final ZoomLevelChangeListener zoomLevelChangeListener) {
        mAMapClient.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                float z=  cameraPosition.zoom;
                zoomLevelChangeListener.getZoomLevel(z);
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                float z=  cameraPosition.zoom;
                zoomLevelChangeListener.getZoomLevel(z);
            }
        });
    }

    @Override
    public void setZoomLevel(float zoom, double lat, double lon) {
        LatLng marker1 = new LatLng(lat, lon);
        //设置中心点和缩放比例
        if(mAMapClient!=null) {
            mAMapClient.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
            mAMapClient.moveCamera(CameraUpdateFactory.zoomTo(zoom));
        }
    }

    @Override
    public void setZoomLevel(float zoom) {
        if(mAMapClient!=null){
            mAMapClient.moveCamera(CameraUpdateFactory.zoomTo(zoom));
        }
    }

    @Override
    public void setMapTraffic(boolean isShow) {
        if(mAMapClient!=null){
            mAMapClient.setTrafficEnabled(isShow);
        }
    }


    private AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            MarkerOptions markerOptions = marker.getOptions();
            LatLng latLng = markerOptions.getPosition();
            List<LTMarkerOptions> LitMarkerOptions = new ArrayList<>();
            if (latLng != null) {//找出相同坐标
                for (MarkerOptions options : markerOptionsList) {
                    LatLng lng = options.getPosition();
                    boolean isDrag = options.isDraggable();
                    if (latLng.equals(options.getPosition())) {
                        LTMarkerOptions markerOption = new LTMarkerOptions.Builder().latitude(lng.latitude).longitude(lng.longitude).draggable(isDrag).build();
                        LitMarkerOptions.add(markerOption);
                    }
                }
            }
            if (LitMarkerOptions != null && LitMarkerOptions.size() > 0) {//点击的监听
                markListener.clickMarkTitle(LitMarkerOptions);
            }

            return false;
        }
    };
}
