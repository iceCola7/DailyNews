package com.cxz.news.module.news.detail;

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
}
