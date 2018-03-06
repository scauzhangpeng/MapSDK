package com.xyz.maplib.location;

import android.content.Context;

/**
 * Created by ZP on 2018/1/15.
 */

public abstract class BaseMapILocation implements ILocation {

    protected Context mContext;

    public BaseMapILocation(Context context) {
        mContext = context;
    }

    protected MapLocationListener mMapLocationListener;

    @Override
    public void setMapLocationListener(MapLocationListener locationListener) {
        mMapLocationListener = locationListener;
    }

    @Override
    public void unRegisterMapLocationListener(MapLocationListener locationListener) {
        mMapLocationListener = null;
    }
}
