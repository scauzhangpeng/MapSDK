package com.xyz.maplib.map;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by ZP on 2018/2/9.
 */

public class LTMapView extends FrameLayout {
    private Context mContext;
    private IMapView mIMapViewDelegate;

    public LTMapView(Context context) {
        this(context, null);
    }

    public LTMapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LTMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(mIMapViewDelegate.getRealMapLayoutId(), this);
//        mMapView = (MapView) view.findViewById(R.id.amap);
        mIMapViewDelegate.setMapView(view);
    }


    public void onCreate(Context context, Bundle savedInstanceState) {
        mIMapViewDelegate.onCreate(context, savedInstanceState);
    }

    public void onStart() {
        mIMapViewDelegate.onStart();
    }

    public void onResume() {
        mIMapViewDelegate.onResume();
    }

    public void onSaveInstanceState(Bundle outState) {
        mIMapViewDelegate.onSaveInstanceState(outState);
    }

    public void onPause() {
        mIMapViewDelegate.onPause();
    }

    public void onStop() {
        mIMapViewDelegate.onStop();
    }

    public void onRestart() {
        mIMapViewDelegate.onRestart();
    }

    public void onDestroy() {
        mIMapViewDelegate.onDestroy();
    }

    public void setMap(IMapView map) {
        mIMapViewDelegate = map;
        initView();
    }

    public LTMarker addMarker(LTMarkerOptions options) {
        return mIMapViewDelegate.addMarker(options);
    }

    public void moveCamera(double latitude, double longitude) {
        mIMapViewDelegate.moveCamera(latitude, longitude);
    }
}
