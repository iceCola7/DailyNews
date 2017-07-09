package com.cxz.news.retrofit;

import com.cxz.news.bean.Photos.BeautyPhotos;
import com.cxz.news.bean.Photos.GankPhotos;
import com.cxz.news.bean.Photos.LifePhotoInfo;
import com.cxz.news.bean.WeatherInfo;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.news.bean.news.Story;
import com.cxz.news.bean.videos.NeteastVideoSummary;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    /**
     * 获取干货图片
     * @param pageSize
     * @return
     */
    @GET("20/{pageSize}")
    Observable<GankPhotos> getGankPhotos(@Path("pageSize") int pageSize);

    /**
     * 获取生活图片
     * @return
     */
    @GET("photo/api/list/0096/4GJ60096.json")
    Observable<List<LifePhotoInfo>> getLifePhotos();

    /**
     * 获取更多生活图片
     * @param setId
     * @return
     */
    @GET("photo/api/morelist/0096/4GJ60096/{setId}.json")
    Observable<List<LifePhotoInfo>> getMoreLifePhotos(@Path("setId") String setId);

    /**
     * 获取图片
     * @param pageSize
     * @return
     */
    @GET("recommend/getChanListNews?channel=T1456112189138&size=20")
    Observable<BeautyPhotos> getBeautyPhotos(@Query("offset") int pageSize);

    /**
     * 网易视频列表 例子：http://c.m.163.com/nc/video/list/V9LG4B3A0/n/0-10.html
     *
     * @param id        视频类别id
     * @param startPage 开始的页码
     * @return 被观察者
     */
    @GET("nc/video/list/{id}/n/{startPage}-10.html")
    Observable<Map<String, List<NeteastVideoSummary>>> getVideoList(
            @Path("id") String id,
            @Path("startPage") int startPage);

}
