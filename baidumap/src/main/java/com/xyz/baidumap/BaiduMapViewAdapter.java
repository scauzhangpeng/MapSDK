package com.xyz.baidumap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
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

public class BaiduMapViewAdapter implements IMapView {

    private MapView mMapView;
    private BaiduMap mBMapClient;
    private MarkListener markListener;
    private final List<OverlayOptions> markerOptionsList = new ArrayList<OverlayOptions>();

    @Override
    public void onCreate(Context context, Bundle savedInstanceState) {
        mMapView.onCreate(context, savedInstanceState);
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
        return R.layout.layout_bmap;
    }

    @Override
    public void setMapView(View view) {
        mMapView = (MapView) view.findViewById(R.id.bmap);
        if (mBMapClient == null) {
            mBMapClient = mMapView.getMap();
            mBMapClient.setCompassEnable(false);
            if (!mBMapClient.isMyLocationEnabled()) {
                mBMapClient.setMyLocationEnabled(true);
            }
        }
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
    }

    @Override
    public LTMarker addMarker(LTMarkerOptions options) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(options.getLatitude(), options.getLongitude()));
        markerOptions.draggable(options.isDraggable());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(options.getIcon()));
        Overlay overlay = mBMapClient.addOverlay(markerOptions);
        LTMarker ltMarker = new LTMarker();
        ltMarker.setMarkerDelegate(new BaiduMarkerAdapter(overlay));
        return ltMarker;
    }

    @Override
    public void moveCamera(double latitude, double longitude) {
        MyLocationData data =
                new MyLocationData.Builder()
                        .latitude(latitude)
                        .longitude(longitude)
                        .build();
        mBMapClient.setMyLocationData(data);
        mBMapClient.setMaxAndMinZoomLevel(21, 16);
        mBMapClient.setMyLocationConfiguration(
                new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, null));
    }

    @Override
    public void cleanMarker() {
        if (mBMapClient != null) {
            mBMapClient.clear();
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
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(options.getLatitude(), options.getLongitude()));
            markerOptions.draggable(options.isDraggable());
            markerOptions.icon(BitmapDescriptorFactory.fromResource(options.getIcon()));
            markerOptionsList.add(markerOptions);
        }
    }

    @Override
    public void showHasMarkers(boolean isShow) {
        if (isShow) {
            if (mBMapClient != null) {
                mBMapClient.clear();
            }
            if (markerOptionsList != null && markerOptionsList.size() > 0) {
                mBMapClient.addOverlays(markerOptionsList);
            } else {
                Log.e("tag", "没有覆盖物数据！");
            }
        } else {
            if (mBMapClient != null) {//不是现实覆盖物就清空覆盖物
                mBMapClient.clear();
            }
        }
    }

    @Override
    public void setMarkerClickTitle(MarkListener markListener) {
        this.markListener = markListener;
        mBMapClient.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    public void setZoomControlsEnabled(boolean b) {
        if (mMapView != null) {
            mMapView.showZoomControls(b);
        }

    }

    @Override
    public void getCurrentZoomLevel(final ZoomLevelChangeListener zoomLevelChangeListener) {
        mBMapClient.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                float z = mapStatus.zoom;
                zoomLevelChangeListener.getZoomLevel(z);
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                float z = mapStatus.zoom;
                zoomLevelChangeListener.getZoomLevel(z);
            }
        });
    }


    private BaiduMap.OnMarkerClickListener markerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            LatLng latLng = marker.getPosition();
            List<LTMarkerOptions> LitMarkerOptions = new ArrayList<>();
            if (latLng != null) {//找出相同坐标
                for (OverlayOptions option : markerOptionsList) {
                    if (option instanceof MarkerOptions) {
                        MarkerOptions options = ((MarkerOptions) option);
                        LatLng lng = options.getPosition();
                        boolean isDrag = options.isDraggable();
                        if (latLng.equals(options.getPosition())) {
                            LTMarkerOptions markerOption = new LTMarkerOptions.Builder().latitude(lng.latitude).longitude(lng.longitude).draggable(isDrag).build();
                            LitMarkerOptions.add(markerOption);
                        }
                    }

                }
            }
            if (LitMarkerOptions != null && LitMarkerOptions.size() > 0) {//点击的监听
                markListener.clickMarkTitle(LitMarkerOptions);
            }

            return false;
        }
    };


    public void setZoomLevel(float zoom, double lat, double lon) {
        LatLng center = new LatLng(lon, lat);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(center)
                .zoom(zoom)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBMapClient.setMapStatus(mMapStatusUpdate);
    }


    public void setZoomLevel(float zoom) {
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .zoom(zoom)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBMapClient.setMapStatus(mMapStatusUpdate);
    }

    @Override
    public void setMapTraffic(boolean isShow) {
        if (mBMapClient != null) {
            mBMapClient.setTrafficEnabled(isShow);
        }
    }
}
