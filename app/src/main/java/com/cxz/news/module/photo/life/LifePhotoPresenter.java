package com.cxz.news.module.photo.life;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.Photos.GankPhotos;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;
import com.cxz.news.utils.XLog;

import rx.Subscriber;
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
        RetrofitManager.getInstance(HostType.GANK_NEWS_PHOTO)
                .getGankPhotos(pageSize)
                .subscribe(subscriber);
    }
}
