package com.xyz.mapsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

//import com.xyz.baidumap.BaiduAdapter;
//import com.amap.api.maps.MapView;
//import com.xyz.amap.AMapAdapter;
//import com.xyz.amap.InitAmapAdapter;
//import com.baidu.mapapi.SDKInitializer;
//import com.baidu.mapapi.map.MapView;
//import com.xyz.amap.AMapAdapter;
//import com.xyz.baidumap.BaiduAdapter;
//import com.xyz.baidumap.BaiduAdapter;
//import com.xyz.baidumap.InitBaiduAdapter;
//import com.tencent.tencentmap.mapsdk.maps.MapView;
//import com.tencent.tencentmap.mapsdk.maps.TencentMap;
//import com.baidu.mapapi.SDKInitializer;
//import com.baidu.mapapi.map.MapView;
//import com.xyz.baidumap.BaiduAdapter;
//import com.xyz.baidumap.InitBaiduAdapter;
import com.amap.api.maps.MapView;
//import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.xyz.amap.AMapAdapter;
import com.xyz.amap.InitAmapAdapter;
import com.xyz.maplib.MapLocation;
import com.xyz.maplib.MapLocationClient;
import com.xyz.maplib.MapLocationListener;
import com.xyz.maplib.ShowMapAdapter;
//import com.xyz.tencentmap.InitTengXunAdapter;
//import com.xyz.tencentmap.TencentMapAdapter;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView=null;//高德地图
//    private MapView mMapView=null;//百度地图
//      private MapView mMapView=null;//腾讯地图
    private TextView mTvLocationInfo;
    private MapLocationClient mMapLocationClient;
    private MapLocationListener mMapLocationListener = new MapLocationListener() {
        @Override
        public void onLocationChanged(MapLocation location) {
            mTvLocationInfo.setText(location.toString() + "时间戳:" + System.currentTimeMillis());
        }
    };

    private ShowMapAdapter mAmapAdapter;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        SDKInitializer.initialize(getApplicationContext());//百度地图的初始化化配置 这个是必须的放在setContentView  最好是放在自定义的Application里面 以后就可以全局的调用
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLocationClient();
//        高德地图需要Bundule savedInstanceState  绑定是在initView里面
        mAmapAdapter=new InitAmapAdapter(mMapView,savedInstanceState);
        // 百度地图  绑定在initView里面
//        mAmapAdapter=  new InitBaiduAdapter(mMapView);
        //腾讯地图
//        mAmapAdapter=new InitTengXunAdapter(mMapView);
        if(mAmapAdapter!=null) {
            mAmapAdapter.setMarkDefault(116.397972, 39.906901, R.mipmap.ic_launcher);//地图定位到北京
        }
    }

    private void initLocationClient() {
        mMapLocationClient = new MapLocationClient(getApplicationContext());
        mMapLocationClient.setMapLocationListener(mMapLocationListener);
        mMapLocationClient.setMapAdapter(new AMapAdapter(getApplicationContext()));
//        mMapLocationClient.setMapAdapter(new BaiduAdapter(getApplicationContext()));
//        mMapLocationClient.setMapAdapter(new TencentMapAdapter(getApplicationContext()));
    }
    private void initView() {
        mTvLocationInfo = (TextView) findViewById(R.id.tv_location_info);
        if(mMapView==null){//高德地图
            mMapView= (MapView) findViewById(R.id.map_amap);
        }
//        if(mMapView==null) {//百度地图
//            mMapView = (MapView) findViewById(R.id.map_baidu);
//        }
//          if(mMapView==null){//腾讯地图
//              mMapView= (MapView) findViewById(R.id.map_tengxun);
//          }

    }

    public void startLocation(View view) {
        mMapLocationClient.startLocation();
    }

    public void stopLocation(View view) {
        mMapLocationClient.stopLocation();
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if(mAmapAdapter!=null){
            mAmapAdapter.onStart();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if(mAmapAdapter!=null){
            mAmapAdapter.onResume();
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if(mAmapAdapter!=null){
            mAmapAdapter.onPause();
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(mAmapAdapter!=null){
            mAmapAdapter.onStop();
        }
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub

        super.onRestart();
        if(mAmapAdapter!=null){
            mAmapAdapter.onRestart();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(mAmapAdapter!=null){
            mAmapAdapter.onDestroy();
        }
    }
}
