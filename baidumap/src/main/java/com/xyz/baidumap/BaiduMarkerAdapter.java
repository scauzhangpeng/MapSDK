package com.xyz.baidumap;

import com.baidu.mapapi.map.Overlay;
import com.xyz.maplib.map.IMarker;

/**
 * Created by ZP on 2018/2/11.
 */

public class BaiduMarkerAdapter implements IMarker {

    private Overlay mOverlay;

    public BaiduMarkerAdapter(Overlay overlay) {
        mOverlay = overlay;
    }

    @Override
    public void remove() {
        mOverlay.remove();
    }
}
