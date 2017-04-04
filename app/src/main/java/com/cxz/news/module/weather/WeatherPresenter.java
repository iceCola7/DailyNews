package com.cxz.news.module.weather;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.WeatherInfo;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;
import com.cxz.news.utils.XLog;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public class WeatherPresenter implements WeatherContract.IPresenter{

    protected WeatherContract.IView mView;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void loadWeatherInfoData(String cityId) {
        Subscriber subscriber = new BaseSubscriber<WeatherInfo>(new RequestCallback<WeatherInfo>() {
            @Override
            public void beforeRequest() {
                XLog.i("-------------beforeRequest");
            }

            @Override
            public void requestError(String msg) {
                XLog.i("-------------requestError");
            }

            @Override
            public void requestComplete() {
                XLog.i("-------------requestComplete");
            }

            @Override
            public void requestSuccess(WeatherInfo data) {
                mView.updateWeather(data);
            }
        });

        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.WEATHER_INFO)
                .getWeatherInfoObservable(cityId)
                .subscribe(subscriber);

    }

    @Override
    public void attachView(WeatherContract.IView view) {
        this.mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        XLog.i("-------------subscribe");
    }

    @Override
    public void unsubscribe() {
        XLog.i("-------------unsubscribe");
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
