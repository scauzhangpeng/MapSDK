package com.xyz.maplib.map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ZP on 2018/2/9.
 */

public interface IMapView {

    void onCreate(Context context, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onSaveInstanceState(Bundle outState);

    void onPause();

    void onStop();

    void onRestart();

    void onDestroy();

    int getRealMapLayoutId();

    void setMapView(View view);

    LTMarker addMarker(LTMarkerOptions options);

    void moveCamera(double latitude, double longitude);
}
