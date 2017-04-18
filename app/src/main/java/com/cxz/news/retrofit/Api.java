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
    public static final String LIFE_PHOTO_HOST = "http://c.3g.163.com/photo/api/";

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
        }
        return "";
    }

}
