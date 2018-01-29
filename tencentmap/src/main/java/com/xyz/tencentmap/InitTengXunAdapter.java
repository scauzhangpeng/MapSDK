package com.xyz.tencentmap;
import android.text.TextUtils;

import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.xyz.maplib.ShowMapAdapter;

/**
 * Created by zengq on 2018/1/26.
 */

public class InitTengXunAdapter implements ShowMapAdapter {
  private MapView amap=null;
  private TencentMap mTengXunMap=null;
  public InitTengXunAdapter(Object map){
      if(map!=null){
          if(map instanceof MapView){
              amap= (MapView) map;
              mTengXunMap=((MapView) map).getMap();
          }
      }
  }


    @Override
    public void setMarkDefault(double longitude, double latitude, int iconResource) {
        BitmapDescriptor bitmap = null;
        if (!TextUtils.isEmpty(iconResource+"")) {
            bitmap = BitmapDescriptorFactory.fromResource(iconResource);
        }
        //标注坐标
        LatLng latLng = new LatLng(latitude,longitude);
        if(mTengXunMap!=null) {
            final Marker marker = mTengXunMap.addMarker(new MarkerOptions().
                    position(latLng).
                    title("北京").
                    snippet("DefaultMarker"));
            if(bitmap!=null){
                marker.setIcon(bitmap);
            }

        }
    }

    @Override
    public void onStart() {
        if(amap!=null){
            amap.onStart();
        }
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
         if(amap!=null){
             amap.onStop();
         }
    }

    @Override
    public void onRestart() {
        if(amap!=null){
            amap.onRestart();
        }
    }

    @Override
    public void onDestroy() {
         if(amap!=null){
             amap.onDestroy();
         }
    }
}
