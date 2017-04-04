package com.cxz.news.module.main;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/3/30.
 */
public class MainPresenter implements MainContract.IPresenter{

    protected MainContract.IView mView;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(MainContract.IView view) {
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
