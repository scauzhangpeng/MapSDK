package com.xyz.amap;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.xyz.maplib.location.BaseMapILocation;
import com.xyz.maplib.location.MapLocation;
import com.xyz.maplib.location.MapLocationListener;


/**
 * 适配器 - 高德地图定位.
 * 将高德地图定位信息转换成MapSDK的通用API。
 * Created by ZP on 2018/1/15.
 */

public class AMapLocationAdapter extends BaseMapILocation {
    private static final String TAG = "AMapLocationAdapter";
    private AMapLocationClient mAMapLocationClient;


    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(com.amap.api.location.AMapLocation aMapLocation) {
            Log.d(TAG, "高德定位: " + aMapLocation.toString());
            if (mMapLocationListener != null) {
                MapLocation location = convert(aMapLocation);
                mMapLocationListener.onLocationChanged(location);
            }
        }
    };

    public AMapLocationAdapter(Context context) {
        super(context);
        mAMapLocationClient = new AMapLocationClient(mContext);
    }

    @Override
    public void setMapLocationListener(MapLocationListener locationListener) {
        super.setMapLocationListener(locationListener);
        mAMapLocationClient.setLocationListener(mAMapLocationListener);
    }

    @Override
    public void unRegisterMapLocationListener(MapLocationListener locationListener) {
        super.unRegisterMapLocationListener(locationListener);
        mAMapLocationClient.unRegisterLocationListener(mAMapLocationListener);
    }

    @Override
    public void startLocation() {
        Log.d(TAG, "startLocation: ");
        mAMapLocationClient.startLocation();
    }

    @Override
    public void stopLocation() {
        Log.d(TAG, "stopLocation: ");
        mAMapLocationClient.stopLocation();
    }

    @Override
    public String getVersion() {
        if (mAMapLocationClient != null) {
            Log.d(TAG, mContext.getString(R.string.amap_location_version) + mAMapLocationClient.getVersion());
            return "AMap:" + mAMapLocationClient.getVersion();
        } else {
            return null;
        }
    }

    private MapLocation convert(com.amap.api.location.AMapLocation aMapLocation) {
        MapLocation location = new MapLocation();
        location.setCountry(aMapLocation.getCountry());
//        location.setCountryCode(baiduLocation.getCountryCode());
        location.setProvince(aMapLocation.getProvince());
        location.setCity(aMapLocation.getCity());
        location.setCityCode(aMapLocation.getCityCode());
        location.setDistrict(aMapLocation.getDistrict());
        location.setLatitude(aMapLocation.getLatitude());
        location.setLongitude(aMapLocation.getLongitude());
        location.setAdCode(aMapLocation.getAdCode());
        location.setRadius(aMapLocation.getAccuracy());
        location.setHasSpeed(aMapLocation.hasSpeed());
        location.setSpeed(aMapLocation.getSpeed());
        location.setLocationDescribe(aMapLocation.getDescription());
        location.setAddress(aMapLocation.getAddress());
        location.setAoiName(aMapLocation.getAoiName());
        location.setPoiName(aMapLocation.getPoiName());
        location.setRoad(aMapLocation.getRoad());
        location.setStreet(aMapLocation.getStreet());
        location.setStreetNum(aMapLocation.getStreetNum());
        return location;
    }
}
