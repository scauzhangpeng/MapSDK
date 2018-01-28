# MapSDK
>公司采用的定位几乎都是使用高德地图SDK，但是在地理围栏的功能选型上，却最后选型百度地图SDK。由于上线版本已经使用高德地图SDK，导致后来APK同时集成了
高德地图SDK以及百度地图SDK。现参考[《滴滴国际化项目 Android 端架构演进与详解》](https://mp.weixin.qq.com/s/FUR7fgks2EyrT5PTaUFvWQ)封装出MapSDK，
便于之后项目随时切换百度地图SDK、高德地图SDK、腾讯地图SDK。

>MapSDK是三家地图SDK的API最大集，API设计靠拢高德地图SDK。具体设计思路参考滴滴分享的文章。

API调用关系：  
![](http://mmbiz.qpic.cn/mmbiz_jpg/eL7YiacLdzwrbf6np3uRKntzJc356OdJz8ztffz2xicgXJSxxcAOFLVqYbo0rsHfQibp0aAibJ1ltMeY9noefFGvKg/640?tp=webp&wxfrom=5&wx_lazy=1)

项目编译依赖关系：  
![](http://mmbiz.qpic.cn/mmbiz_jpg/eL7YiacLdzwrbf6np3uRKntzJc356OdJznsCbhITwDKmMpWJYSCVe3CQ9kWjC5NlEU6zBsXpcz6mxTXK46lJ0cA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1)
