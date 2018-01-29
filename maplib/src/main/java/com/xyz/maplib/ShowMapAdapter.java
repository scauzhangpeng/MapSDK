package com.xyz.maplib;

/**
 * Created by zengq on 2018/1/26.
 */

public interface ShowMapAdapter {
    //longitude 经度， latitude纬度
    public void setMarkDefault(double longitude ,double latitude,int iconResource);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onRestart() ;
    public void onDestroy();

}
