package com.cxz.news.module.photo.gank;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.Photos.GankPhotos;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/16.
 */
public class GankPhotoPresenter implements GankPhotoContract.IPresenter {

    private GankPhotoContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(GankPhotoContract.IView view) {
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

    @Override
    public void loadGankPhotos(final int pageSize) {
        Subscriber subscriber = new BaseSubscriber<GankPhotos>(new RequestCallback<GankPhotos>() {
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
            public void requestSuccess(GankPhotos data) {
                if (pageSize == 1)
                    mView.updateItems(data.getResults());
                else
                    mView.updateMoreItems(data.getResults());
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.GANK_NEWS_PHOTO)
                .getGankPhotos(pageSize)
                .subscribe(subscriber);
    }
}
