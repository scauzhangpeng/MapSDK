package com.xyz.maplib.location;

import android.content.Context;
import android.util.Log;

/**
 * Created by ZP on 2018/1/15.
 */

public class MapLocationClient {
    private static final String TAG = "MapLocationClient";

    protected MapLocationListener mMapLocationListener;

    protected Context mContext;

    protected ILocation mILocation;

    public MapLocationClient(Context context) {
        mContext = context;
    }

    public void startLocation() {
        Log.d(TAG, "startLocation: ");
        mILocation.startLocation();
        mILocation.setMapLocationListener(mMapLocationListener);
    }

    public void stopLocation() {
        Log.d(TAG, "stopLocation: ");
        mILocation.stopLocation();
    }

    public void setMapLocationListener(MapLocationListener locationListener) {
        mMapLocationListener = locationListener;
    }

    public void setMapAdapter(BaseMapILocation adapter) {
        mILocation = adapter;
    }
}
