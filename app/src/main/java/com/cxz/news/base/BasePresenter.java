package com.cxz.news.base;

import rx.Subscription;

/**
 * Created by chenxz on 2017/4/1.
 */
public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter {

    protected Subscription mSubscription;
    protected T mView;

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mView = null;
    }

}
