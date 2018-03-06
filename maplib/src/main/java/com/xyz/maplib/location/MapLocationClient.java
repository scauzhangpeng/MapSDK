package com.xyz.maplib.location;

import android.util.Log;

/**
 * Created by ZP on 2018/1/15.
 */

public class MapLocationClient implements ILocation {
    private static final String TAG = "MapLocationClient";


    protected ILocation mILocation;

    public MapLocationClient(BaseMapILocation adapter) {
        mILocation = adapter;
    }

    public void startLocation() {
        Log.d(TAG, "startLocation: ");
        mILocation.startLocation();
    }

    public void stopLocation() {
        Log.d(TAG, "stopLocation: ");
        mILocation.stopLocation();
    }

    public String getVersion() {
        return mILocation.getVersion();
    }

    public void setMapLocationListener(MapLocationListener locationListener) {
        mILocation.setMapLocationListener(locationListener);
    }

    @Override
    public void unRegisterMapLocationListener(MapLocationListener locationListener) {
        mILocation.unRegisterMapLocationListener(locationListener);
    }

    public void setMapAdapter(BaseMapILocation adapter) {
        mILocation = adapter;
    }
}
