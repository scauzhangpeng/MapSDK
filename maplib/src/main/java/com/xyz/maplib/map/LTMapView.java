package com.xyz.maplib.map;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;


/**
 * Created by ZP on 2018/2/9.
 */

public class LTMapView extends FrameLayout {
    private Context mContext;
    private IMapView mIMapViewDelegate;

    public LTMapView(Context context) {
        this(context, null);
    }

    public LTMapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LTMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(mIMapViewDelegate.getRealMapLayoutId(), this);
//        mMapView = (MapView) view.findViewById(R.id.amap);
        mIMapViewDelegate.setMapView(view);
    }


    public void onCreate(Context context, Bundle savedInstanceState) {
        mIMapViewDelegate.onCreate(context, savedInstanceState);
    }

    public void onStart() {
        mIMapViewDelegate.onStart();
    }

    public void onResume() {
        mIMapViewDelegate.onResume();
    }

    public void onSaveInstanceState(Bundle outState) {
        mIMapViewDelegate.onSaveInstanceState(outState);
    }

    public void onPause() {
        mIMapViewDelegate.onPause();
    }

    public void onStop() {
        mIMapViewDelegate.onStop();
    }

    public void onRestart() {
        mIMapViewDelegate.onRestart();
    }

    public void onDestroy() {
        mIMapViewDelegate.onDestroy();
    }

    public void setMap(IMapView map) {
        mIMapViewDelegate = map;
        initView();
    }

    public LTMarker addMarker(LTMarkerOptions options) {
        return mIMapViewDelegate.addMarker(options);
    }

    public void moveCamera(double latitude, double longitude) {
        mIMapViewDelegate.moveCamera(latitude, longitude);
    }


    /**
     * 给覆盖物点击获取到覆盖物的标题
     *
     * @param markListener
     */
    public void setMarkListener(MarkListener markListener) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.setMarkerClickTitle(markListener);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    /**
     * 设置覆盖物数据
     *
     * @param options
     */
    public void setMarkOptions(List<LTMarkerOptions> options) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.setMarkers(options);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    /**
     * 清除覆盖物
     */
    public void cleanMarker() {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.cleanMarker();
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    public void refreshMarker() {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.refreshMarker();
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    /**
     * 是否显示覆盖物
     *
     * @param isShow
     */
    public void isShowMarkers(boolean isShow) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.showHasMarkers(isShow);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    /**
     * 开启放大缩小图标
     *
     * @param isShow
     */
    public void setZoomControlsEnabled(boolean isShow) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.setZoomControlsEnabled(isShow);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    public void setZoomLevelChangListener(ZoomLevelChangeListener zoomLevelChangListener) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.getCurrentZoomLevel(zoomLevelChangListener);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    public void setCenterAndLevel(float zoom, double lai, double lon) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.setZoomLevel(zoom, lai, lon);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    public void setLevel(float zoom) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.setZoomLevel(zoom);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }

    /**
     * 是否显示路况
     *
     * @param isShow
     */
    public void setTrafficEnabled(boolean isShow) {
        if (mIMapViewDelegate != null) {
            mIMapViewDelegate.setMapTraffic(isShow);
        } else {
            Log.e("tag", "mImapViewDelegage 为空");
        }
    }


}
