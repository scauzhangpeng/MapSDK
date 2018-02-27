package com.xyz.mapsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xyz.maplib.location.MapLocation;
import com.xyz.maplib.location.MapLocationClient;
import com.xyz.maplib.location.MapLocationListener;
import com.xyz.maplib.map.LTMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;
import com.xyz.tencentmap.TencentLocationAdapter;
import com.xyz.tencentmap.TencentMapViewAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView mTvLocationInfo;
    private MapLocationClient mMapLocationClient;
    private MapLocationListener mMapLocationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(MapLocation location) {
            mTvLocationInfo.setText(location.toString() + "时间戳:" + System.currentTimeMillis());
            mLTMapView.moveCamera(location.getLatitude(), location.getLongitude());
            if (mMarker == null) {
                addMarker(location);
            } else {
                mMarker.remove();
                addMarker(location);
            }
        }
    };

    private void addMarker(MapLocation location) {
        LTMarkerOptions options = new LTMarkerOptions.Builder()
                .draggable(true)
                .icon(R.drawable.ic_main_poi_event)
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .build();
        mMarker = mLTMapView.addMarker(options);
    }

    private LTMapView mLTMapView;
    private LTMarker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SDKInitializer.initialize(getApplicationContext());//百度地图的初始化化配置 这个是必须的放在setContentView  最好是放在自定义的Application里面 以后就可以全局的调用
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
        initLocationClient();

//        if(mAmapAdapter!=null) {
//            mAmapAdapter.setMarkDefault(116.397972, 39.906901, R.drawable.ic_main_poi_event);//地图定位到北京
//        }
        LTMarkerOptions options = new LTMarkerOptions.Builder()
                .draggable(true)
                .icon(R.drawable.ic_main_poi_event)
                .longitude(116.397972)
                .latitude(39.906901)
                .build();
        mMarker = mLTMapView.addMarker(options);
    }

    private void initLocationClient() {
        mMapLocationClient = new MapLocationClient(getApplicationContext());
        mMapLocationClient.setMapLocationListener(mMapLocationListener);
//        mMapLocationClient.setMapAdapter(new AMapLocationAdapter(getApplicationContext()));
//        mMapLocationClient.setMapAdapter(new BaiduLocationAdapter(getApplicationContext()));
        mMapLocationClient.setMapAdapter(new TencentLocationAdapter(getApplicationContext()));
    }

    private void initView(Bundle savedInstanceState) {
        mTvLocationInfo = (TextView) findViewById(R.id.tv_location_info);
        //map
        mLTMapView = (LTMapView) findViewById(R.id.map);
        //比正常操作地图多一个设置，必须前于onCreate方法
//        mLTMapView.setMap(new AMapViewAdapter());
//        mLTMapView.setMap(new BaiduMapViewAdapter());
        mLTMapView.setMap(new TencentMapViewAdapter());
        mLTMapView.onCreate(getApplicationContext(), savedInstanceState);
    }

    public void startLocation(View view) {
        mMapLocationClient.startLocation();
    }

    public void stopLocation(View view) {
        mMapLocationClient.stopLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLTMapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLTMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLTMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLTMapView.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mLTMapView.onRestart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLTMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLTMapView.onDestroy();
    }
}
