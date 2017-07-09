package com.cxz.news.retrofit;

/**
 * Created by chenxz on 2017/3/31.
 */
public class Api {
    /**
     * 天气预报url
     */
    public static final String WEATHER_HOST = "http://www.weather.com.cn/";//"http://wthrcdn.etouch.cn/";

    /**
     * 知乎日报的URL
     */
    public static final String ZHIHU_NEWS_HOST = "http://news.at.zhihu.com/api/4/";

    /**
     * 干货图片
     */
    public static final String GANK_PHOTO_HOST = "http://gank.io/api/data/福利/";

    /**
     * 生活图片
     */
    public static final String LIFE_PHOTO_HOST = "http://c.3g.163.com/";

    // 热点视频
    public static final String VIDEO_HOT_ID = "V9LG4B3A0";
    // 娱乐视频
    public static final String VIDEO_ENTERTAINMENT_ID = "V9LG4CHOR";
    // 搞笑视频
    public static final String VIDEO_FUN_ID = "V9LG4E6VR";
    // 精品视频
    public static final String VIDEO_CHOICE_ID = "00850FRB";

    /**
     * 视频
     */
    public static final String NETEAST_HOST = "https://c.m.163.com/";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        switch (hostType) {
            case HostType.WEATHER_INFO:
                return Api.WEATHER_HOST;
            case HostType.ZHIHU_NEWS_INFO:
                return Api.ZHIHU_NEWS_HOST;
            case HostType.GANK_NEWS_PHOTO:
                return Api.GANK_PHOTO_HOST;
            case HostType.LIFE_NEWS_PHOTO:
                return Api.LIFE_PHOTO_HOST;
            case HostType.NETEASE_NEWS_VIDEO:
                return Api.NETEAST_HOST;
        }
        return "";
    }

}
