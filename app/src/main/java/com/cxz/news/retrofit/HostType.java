package com.cxz.news.retrofit;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by chenxz on 2017/4/1.
 */
public class HostType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 3;

    /**
     * 网易新闻视频的host
     */
    @HostTypeChecker
    public static final int NETEASE_NEWS_VIDEO = 1;

    /**
     * 干货图片的host
     */
    @HostTypeChecker
    public static final int GANK_NEWS_PHOTO = 2;

    /**
     * 天气查询的host
     */
    @HostTypeChecker
    public static final int WEATHER_INFO = 3;

    /**
     * 知乎日报的host
     */
    @HostTypeChecker
    public static final int ZHIHU_NEWS_INFO = 4;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({NETEASE_NEWS_VIDEO, GANK_NEWS_PHOTO, WEATHER_INFO,ZHIHU_NEWS_INFO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }

}
