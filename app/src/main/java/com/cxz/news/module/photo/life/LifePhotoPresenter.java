package com.cxz.news.module.photo.life;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/16.
 */
public class LifePhotoPresenter implements LifePhotoContract.IPresenter {

    private LifePhotoContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(LifePhotoContract.IView view) {
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
