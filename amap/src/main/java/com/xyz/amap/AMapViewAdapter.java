package com.xyz.amap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.xyz.maplib.map.IMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;

/**
 * Created by ZP on 2018/2/9.
 */

public class AMapViewAdapter implements IMapView {

    private MapView mMapView;
    private AMap mAMapClient;

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
}
