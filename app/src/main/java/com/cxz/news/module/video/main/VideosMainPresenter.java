package com.cxz.news.module.video.main;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/12.
 */
public class VideosMainPresenter implements VideosMainContract.IPresenter {

    private VideosMainContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(VideosMainContract.IView view) {
        this.mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }
}
