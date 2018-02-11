package com.xyz.baidumap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.LatLng;
import com.xyz.maplib.map.IMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;


/**
 * Created by ZP on 2018/2/9.
 */

public class BaiduMapViewAdapter implements IMapView {

    private MapView mMapView;
    private BaiduMap mBMapClient;


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
}
