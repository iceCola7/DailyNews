package com.cxz.news.module.weather;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.WeatherInfo;

public class WeatherContract {

    interface IView extends IBaseView {
        void updateWeather(WeatherInfo weatherInfo);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void loadWeatherInfoData(String cityId);
    }

}
