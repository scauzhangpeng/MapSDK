package com.xyz.baidumap;
import android.text.TextUtils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.xyz.maplib.ShowMapAdapter;

/**
 * Created by zengq on 2018/1/26.
 */

public class InitBaiduAdapter implements ShowMapAdapter {
  private  MapView amap=null;
  private BaiduMap mBaiduMap=null;
  public InitBaiduAdapter(Object map){
      if(map!=null){
          if(map instanceof MapView){
              amap= (MapView) map;
              mBaiduMap=((MapView) map).getMap();
          }
      }
  }


  @Override
  public void setMarkDefault(double longitude, double latitude, int iconResource) {
    //定义Maker坐标点
    LatLng point = new LatLng(latitude,longitude);
//      //构建Marker图标
      BitmapDescriptor bitmap = null;
      if (!TextUtils.isEmpty(iconResource+"")) {
        bitmap = BitmapDescriptorFactory.fromResource(iconResource);
      }
//    构建MarkerOption，用于在地图上添加Marker
    MarkerOptions markerOption=new MarkerOptions();
      markerOption.position(point);
      if(bitmap!=null){
        markerOption.icon(bitmap);
      }
      OverlayOptions option=markerOption;
//    OverlayOptions option = new MarkerOptions()
//            .position(point)
//              .icon(bitmap);
    //在地图上添加Marker，并显示
    mBaiduMap.addOverlay(option);
  }

  @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {

    }
}
