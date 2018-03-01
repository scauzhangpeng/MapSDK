package com.xyz.maplib.map;

import java.util.List;

/**
 * Created by zengq on 2018/2/26.
 * 这个是实现点击监听的自定义接口
 */

public interface MarkListener {
    public void clickMarkTitle(List<LTMarkerOptions> markerOptionsList);
}
