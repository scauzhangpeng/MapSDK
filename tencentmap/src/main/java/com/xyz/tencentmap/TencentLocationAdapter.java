package com.xyz.tencentmap;

import android.content.Context;
import android.util.Log;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.xyz.maplib.location.BaseMapILocation;
import com.xyz.maplib.location.MapLocation;

/**
 * Created by ZP on 2018/1/16.
 */

public class TencentLocationAdapter extends BaseMapILocation {
    private static final String TAG = "TencentLocationAdapter";

    private TencentLocationManager mTencentLocationManager;
    private TencentLocationRequest mRequest;
    private TencentLocationListener mListener = new TencentLocationListener() {
        @Override
        public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {
            if (TencentLocation.ERROR_OK == error) {
                Log.d(TAG, "腾讯定位结果: " + tencentLocation.toString());
                if (mMapLocationListener != null) {
                    MapLocation location = convert(tencentLocation, error, s);
                    mMapLocationListener.onLocationChanged(location);
                }
            } else {
                Log.d(TAG, "onLocationChanged: error" + error);
            }
        }

        @Override
        public void onStatusUpdate(String s, int i, String s1) {

        }
    };


    public TencentLocationAdapter(Context context) {
        super(context);
        mRequest = TencentLocationRequest.create();
        mRequest.setInterval(2000);
        mRequest.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        mTencentLocationManager = TencentLocationManager.getInstance(mContext);

    }

    @Override
    public void startLocation() {
        int error = mTencentLocationManager.requestLocationUpdates(mRequest, mListener);
        Log.d(TAG, "TencentLocationAdapter: error:" + error);
    }

    @Override
    public void stopLocation() {
        mTencentLocationManager.removeUpdates(mListener);
    }

    @Override
    public String getVersion() {
        if (mTencentLocationManager != null) {
            return mContext.getString(R.string.tmap_location_version) + mTencentLocationManager.getVersion();
        } else {
            return null;
        }
    }

    private MapLocation convert(TencentLocation tencentLocation, int error, String s) {
        MapLocation location = new MapLocation();
        location.setCountry(tencentLocation.getNation());
        location.setProvince(tencentLocation.getProvince());
        location.setCity(tencentLocation.getCity());
        location.setCityCode(tencentLocation.getCityCode());
        location.setDistrict(tencentLocation.getDistrict());
        location.setLatitude(tencentLocation.getLatitude());
        location.setLongitude(tencentLocation.getLongitude());
        location.setSpeed(tencentLocation.getSpeed());
        location.setRadius(tencentLocation.getAccuracy());
        location.setAddress(tencentLocation.getAddress());
//        location.setAoiName(tencentLocation.getAoiName());
//        location.setPoiName(tencentLocation.getPoiName());
//        location.setRoad(tencentLocation.getRoad());
        location.setStreet(tencentLocation.getStreet());
        location.setStreetNum(tencentLocation.getStreetNo());
        return location;
    }
}
