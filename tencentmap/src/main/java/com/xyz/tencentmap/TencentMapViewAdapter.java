package com.xyz.tencentmap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.UiSettings;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.xyz.maplib.map.IMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;

/**
 * Created by ZP on 2018/2/11.
 */

public class TencentMapViewAdapter implements IMapView {

    private MapView mMapView;
    private TencentMap mTencentMapClient;

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
}
