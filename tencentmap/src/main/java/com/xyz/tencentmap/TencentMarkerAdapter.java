package com.xyz.tencentmap;

import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.xyz.maplib.map.IMarker;

/**
 * Created by ZP on 2018/2/11.
 */

public class TencentMarkerAdapter implements IMarker {

    private Marker mMarker;

    public TencentMarkerAdapter(Marker marker) {
        mMarker = marker;
    }

    @Override
    public void remove() {
        mMarker.remove();
    }
}
