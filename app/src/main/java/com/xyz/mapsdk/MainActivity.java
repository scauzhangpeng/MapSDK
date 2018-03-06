package com.xyz.mapsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.xyz.baidumap.BaiduLocationAdapter;
import com.xyz.baidumap.BaiduMapViewAdapter;
import com.xyz.maplib.location.MapLocation;
import com.xyz.maplib.location.MapLocationClient;
import com.xyz.maplib.location.MapLocationListener;
import com.xyz.maplib.map.LTMapView;
import com.xyz.maplib.map.LTMarker;
import com.xyz.maplib.map.LTMarkerOptions;
import com.xyz.maplib.map.MarkListener;
import com.xyz.maplib.map.ZoomLevelChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tvLevel;
    private TextView mTvLocationInfo;
    private MapLocationClient mMapLocationClient;
    private MapLocation mLocation;
    private MapLocationListener mMapLocationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(MapLocation location) {
            Log.d(TAG, "LT定位: " + location.toString());
            mLocation = location;
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
        SDKInitializer.initialize(getApplicationContext());//百度地图的初始化化配置 这个是必须的放在setContentView  最好是放在自定义的Application里面 以后就可以全局的调用
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
//        mMapLocationClient = new MapLocationClient(new AMapLocationAdapter(getApplicationContext()));
        mMapLocationClient = new MapLocationClient(new BaiduLocationAdapter(getApplicationContext()));
//        mMapLocationClient = new MapLocationClient(new TencentLocationAdapter(getApplicationContext()));
        mMapLocationClient.setMapLocationListener(mMapLocationListener);
    }

    private void initView(Bundle savedInstanceState) {
        mTvLocationInfo = (TextView) findViewById(R.id.tv_location_info);
        tvLevel = (TextView) findViewById(R.id.tv_level);
        //map
        mLTMapView = (LTMapView) findViewById(R.id.map);
        //比正常操作地图多一个设置，必须前于onCreate方法
//        mLTMapView.setMap(new AMapViewAdapter());
        mLTMapView.setMap(new BaiduMapViewAdapter());
//        mLTMapView.setMap(new TencentMapViewAdapter());
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

    /**
     * 添加多个覆盖物  还没显示
     *
     * @param view
     */
    public void addMore(View view) {
        List<LTMarkerOptions> optionses = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            LTMarkerOptions options = new LTMarkerOptions.Builder()
                    .draggable(true)
                    .icon(R.drawable.ic_main_poi_event)
                    .longitude(116.397972 + 0.01 * i)
                    .latitude(39.906901 + 0.01 * i)
                    .build();
            optionses.add(options);
        }
        mLTMapView.setMarkOptions(optionses);
        mLTMapView.setMarkListener(new MarkListener() {
            @Override
            public void clickMarkTitle(List<LTMarkerOptions> markerOptionsList) {
                if (markerOptionsList.size() == 1) {
                    Toast.makeText(MainActivity.this, "lai：" + markerOptionsList.get(0).getLatitude() + "Long:" + markerOptionsList.get(0).getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //显示或者隐藏
    public void showOrHide(View view) {
        if (view.getTag() == null || (view.getTag() != null && (((int) view.getTag()) == 1))) {
            view.setTag(0);
            mLTMapView.isShowMarkers(true);
        } else {
            view.setTag(1);
            mLTMapView.isShowMarkers(false);
        }
    }

    /**
     * 清空覆盖物
     *
     * @param view
     */
    public void cleanMark(View view) {
        mLTMapView.cleanMarker();
    }

    /**
     * 刷新覆盖物  然后在添加覆盖物并且显示
     *
     * @param view
     */
    public void refreshMark(View view) {
        mLTMapView.refreshMarker();
    }

    /**
     * 设置开启关闭缩放大小图标
     *
     * @param view
     */
    public void setZoomControl(View view) {
        if (view.getTag() == null || (view.getTag() != null && (((int) view.getTag()) == 1))) {
            view.setTag(0);
            mLTMapView.setZoomControlsEnabled(true);
        } else {
            view.setTag(1);
            mLTMapView.setZoomControlsEnabled(false);
        }
    }

    /**
     * 开启地图缩放等级监听
     *
     * @param view
     */
    public void statLevelChangeListener(View view) {
        if (mLTMapView != null) {
            mLTMapView.setZoomLevelChangListener(new ZoomLevelChangeListener() {
                @Override
                public void getZoomLevel(float zoomLevel) {
                    tvLevel.setText(zoomLevel + "");
                }
            });
        }
    }

    /**
     * 设置地图的缩放级别和中心点
     *
     * @param view
     */
    public void setCenterAndLevel(View view) {
        if (mLTMapView != null) {
            if (mLocation == null) {
                mLTMapView.setCenterAndLevel(13, 39.906901, 116.397972);
            } else {
                mLTMapView.setCenterAndLevel(2, mLocation.getLatitude(), mLocation.getLongitude());
            }
        }
    }

    /**
     * 只是设置地图缩放级别
     *
     * @param view
     */
    public void setLevel(View view) {
        if (mLTMapView != null) {
            mLTMapView.setLevel(10);
        }
    }

    /**
     * 设置是否开启路况
     *
     * @param view
     */
    public void setTrafficEnable(View view) {
        if (view.getTag() == null || (view.getTag() != null && (((int) view.getTag()) == 1))) {
            view.setTag(0);
            mLTMapView.setTrafficEnabled(true);
        } else {
            view.setTag(1);
            mLTMapView.setTrafficEnabled(false);
        }
    }

    /**
     * 获取SDK版本号.
     *
     * @param view {@link android.widget.Button}
     */
    public void getVersion(View view) {
        String version = mMapLocationClient.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_LONG).show();
    }


}
