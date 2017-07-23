package com.cxz.news.module.video.detail;

import com.cxz.news.base.IBaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/7/9.
 */

public class VideoDetailPresenter implements VideoDetailContract.IPresenter {

    private VideoDetailContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(VideoDetailContract.IView view) {
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
    public void playVideo(String url, String name) {
        mView.playVideo(url,name);
    }
}
