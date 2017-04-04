package com.cxz.news.retrofit;

import com.cxz.news.bean.WeatherInfo;

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

}
