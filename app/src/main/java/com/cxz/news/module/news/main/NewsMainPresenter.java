package com.cxz.news.module.news.main;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;
import com.cxz.news.utils.XLog;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/4.
 */
public class NewsMainPresenter implements NewsMainContract.IPresenter {

    private NewsMainContract.IView mViews;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(NewsMainContract.IView view) {
        this.mViews = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void loadLatestDailyStories() {
        Subscriber subscriber = new BaseSubscriber<DailyStories>(new RequestCallback<DailyStories>() {
            @Override
            public void beforeRequest() {

            }

            @Override
            public void requestError(String msg) {
                XLog.e(msg);
            }

            @Override
            public void requestComplete() {

            }

            @Override
            public void requestSuccess(DailyStories data) {
                mViews.updateLatestDailyStories(data);
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.ZHIHU_NEWS_INFO)
                .getLatestDailyStories()
                .subscribe(subscriber);
    }

    @Override
    public void loadBeforeDailyStories(String date) {
        Subscriber subscriber = new BaseSubscriber<DailyStories>(new RequestCallback<DailyStories>() {
            @Override
            public void beforeRequest() {

            }

            @Override
            public void requestError(String msg) {

            }

            @Override
            public void requestComplete() {

            }

            @Override
            public void requestSuccess(DailyStories data) {
                mViews.updateBeforeDailyStories(data);
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.ZHIHU_NEWS_INFO)
                .getBeforeDailyStories(date)
                .subscribe(subscriber);
    }
}
