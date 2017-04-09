package com.cxz.news.retrofit;

import com.cxz.news.bean.WeatherInfo;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.news.bean.news.Story;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chenxz on 2017/3/30.
 */
public interface ApiStores {

    /**
     * 加载天气数据
     */
    @GET("adat/sk/{cityId}.html")
    Observable<WeatherInfo> getWeatherInfo(@Path("cityId") String cityId);

    /**
     * 加载最新的新闻
     * @return
     */
    @GET("news/latest")
    Observable<DailyStories> getLatestDailyStories();

    /**
     * 加载过去的新闻
     * @param date
     * @return
     */
    @GET("news/before/{date}")
    Observable<DailyStories> getBeforeDailyStories(@Path("date")String date);

    /**
     * 根据ID获取新闻的详情
     * @param storyId
     * @return
     */
    @GET("news/{storyId}")
    Observable<Story> getStoryDetailById(@Path("storyId") String storyId);
}
