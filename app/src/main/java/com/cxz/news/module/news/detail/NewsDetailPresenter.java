package com.cxz.news.module.news.detail;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.news.Story;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/7.
 */
public class NewsDetailPresenter implements NewsDetailContract.IPresenter {
    protected NewsDetailContract.IView mView;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(NewsDetailContract.IView view) {
        this.mView = view;
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
    public void loadStoryDetailById(String storyId) {
        Subscriber subscriber = new BaseSubscriber<Story>(new RequestCallback<Story>() {
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
            public void requestSuccess(Story data) {
                mView.updateStoryDetail(data);
            }
        });
        RetrofitManager.getInstance(HostType.ZHIHU_NEWS_INFO)
                .getStoryDetailById(storyId)
                .subscribe(subscriber);
    }
}
