package com.xyz.tencentmap;

import android.content.Context;
import android.util.Log;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.xyz.maplib.BaseMapAdapter;
import com.xyz.maplib.MapLocation;

/**
 * Created by ZP on 2018/1/16.
 */

public class TencentMapAdapter extends BaseMapAdapter {
    private static final String TAG = "TencentMapAdapter";

    private TencentLocationManager mTencentLocationManager;
    private TencentLocationRequest mRequest;
    private TencentLocationListener mListener = new TencentLocationListener() {
        @Override
        public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {
            if (TencentLocation.ERROR_OK == error) {
                MapLocation location  = new MapLocation();
                location.setProvince(tencentLocation.getProvince());
                location.setCity(tencentLocation.getCity());
                location.setDistrict(tencentLocation.getDistrict());
                location.setLatitude(tencentLocation.getLatitude());
                location.setLongitude(tencentLocation.getLongitude());
                mMapLocationListener.onLocationChanged(location);
            } else {
                Log.d(TAG, "onLocationChanged: error" + error);
            }
        }

        @Override
        public void onStatusUpdate(String s, int i, String s1) {

        }
    };


    public TencentMapAdapter(Context context) {
        super(context);
        mRequest = TencentLocationRequest.create();
        mRequest.setInterval(2000);
        mRequest.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        mTencentLocationManager = TencentLocationManager.getInstance(mContext);

    }

    @Override
    public void startLocation() {
        int error = mTencentLocationManager.requestLocationUpdates(mRequest, mListener);
        Log.d(TAG, "TencentMapAdapter: error:" + error);
    }

    @Override
    public void stopLocation() {
        mTencentLocationManager.removeUpdates(mListener);
    }
}
