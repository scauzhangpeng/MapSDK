package com.xyz.maplib.map;

/**
 * Created by ZP on 2018/2/11.
 */

public class LTMarker implements IMarker {

    private IMarker markerDelegate;

    @Override
    public void remove() {
        markerDelegate.remove();
    }

    public void setMarkerDelegate(IMarker marker) {
        markerDelegate = marker;
    }
}
