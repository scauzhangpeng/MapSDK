package com.xyz.maplib.location;

/**
 * Created by ZP on 2018/1/15.
 */

public interface ILocation {

    void startLocation();

    void setMapLocationListener(MapLocationListener locationListener);

    void unRegisterMapLocationListener(MapLocationListener locationListener);

    void stopLocation();

    String getVersion();
}
