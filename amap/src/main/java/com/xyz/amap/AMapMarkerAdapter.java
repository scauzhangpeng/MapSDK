package com.xyz.amap;

import com.amap.api.maps.model.Marker;
import com.xyz.maplib.map.IMarker;

/**
 * Created by ZP on 2018/2/11.
 */

public class AMapMarkerAdapter implements IMarker {

    private Marker mMarker;

    public AMapMarkerAdapter(Marker marker) {
        mMarker = marker;
    }

    @Override
    public void remove() {
        mMarker.remove();
    }
}
