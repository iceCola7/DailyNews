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
        }
        return "";
    }

}
