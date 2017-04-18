package com.cxz.news.module.photo.life;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.Photos.LifePhotoInfo;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;

import java.util.List;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/18.
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

    @Override
    public void loadLifePhotos() {
        Subscriber subscriber = new BaseSubscriber<List<LifePhotoInfo>>(new RequestCallback<List<LifePhotoInfo>>() {
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
            public void requestSuccess(List<LifePhotoInfo> data) {
                mView.updateItems(data);
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.LIFE_NEWS_PHOTO)
                .getLifePhotos()
                .subscribe(subscriber);
    }

    @Override
    public void loadMoreLifePhotos(String setId) {
        Subscriber subscriber = new BaseSubscriber<List<LifePhotoInfo>>(new RequestCallback<List<LifePhotoInfo>>() {
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
            public void requestSuccess(List<LifePhotoInfo> data) {
                mView.updateMoreItems(data);
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.LIFE_NEWS_PHOTO)
                .getMoreLifePhotos(setId)
                .subscribe(subscriber);
    }
}
