package com.xyz.amap;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.xyz.maplib.ShowMapAdapter;

/**
 * Created by zengq on 2018/1/26.
 */

public class InitAmapAdapter implements ShowMapAdapter {
    private final String TAG="amapAdapter";
    private MapView amap=null;
    private com.amap.api.maps.AMap aMap=null;
    public InitAmapAdapter(Object map,Bundle var1){
        if(map!=null){
            if(map instanceof MapView){
                Log.e(TAG,"初始化高德地图");
                ((MapView)map).onCreate(var1);// 此方法必须重写
                 amap= (MapView) map;
                 aMap = ((MapView)map).getMap();
            }
        }
    }


    @Override
    public void setMarkDefault(double longitude, double latitude, int iconResource) {
        MarkerOptions markerOption = new MarkerOptions();
        LatLng point = new LatLng(latitude,longitude);
        markerOption.position(point);
        markerOption.title("北京市").snippet("北京市："+longitude+","+ latitude);

        markerOption.draggable(false);//设置Marker不可拖动
        BitmapDescriptor bitmap = null;
        if (!TextUtils.isEmpty(iconResource+"")) {
            bitmap = BitmapDescriptorFactory.fromResource(iconResource);
        }
        if(bitmap!=null) {
            markerOption.icon(bitmap);
        }
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
      Marker marker= aMap.addMarker(markerOption);
//        LatLng latLng = new LatLng(39.906901,116.397972);
//        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        if(amap!=null){
            amap.onResume();
        }
    }

    @Override
    public void onPause() {
        if(amap!=null){
            amap.onPause();
        }
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {
        if(amap!=null){
            amap.onDestroy();
        }
    }
}
