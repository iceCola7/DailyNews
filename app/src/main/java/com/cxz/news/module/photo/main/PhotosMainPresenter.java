package com.cxz.news.module.photo.main;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/12.
 */
public class PhotosMainPresenter implements PhotosMainContract.IPresenter {

    private PhotosMainContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(PhotosMainContract.IView view) {
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
