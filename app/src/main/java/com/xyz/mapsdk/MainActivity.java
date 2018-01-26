package com.xyz.mapsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xyz.baidumap.BaiduAdapter;
import com.xyz.maplib.MapLocation;
import com.xyz.maplib.MapLocationClient;
import com.xyz.maplib.MapLocationListener;

public class MainActivity extends AppCompatActivity {

    private TextView mTvLocationInfo;
    private MapLocationClient mMapLocationClient;
    private MapLocationListener mMapLocationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(MapLocation location) {
            mTvLocationInfo.setText(location.toString() + "时间戳:" + System.currentTimeMillis());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLocationClient();
    }

    private void initLocationClient() {
        mMapLocationClient = new MapLocationClient(getApplicationContext());
        mMapLocationClient.setMapLocationListener(mMapLocationListener);
//        mMapLocationClient.setMapAdapter(new AMapAdapter(getApplicationContext()));
        mMapLocationClient.setMapAdapter(new BaiduAdapter(getApplicationContext()));
//        mMapLocationClient.setMapAdapter(new TencentMapAdapter(getApplicationContext()));
    }

    private void initView() {
        mTvLocationInfo = (TextView) findViewById(R.id.tv_location_info);
    }

    public void startLocation(View view) {
        mMapLocationClient.startLocation();
    }

    public void stopLocation(View view) {
        mMapLocationClient.stopLocation();
    }
}
