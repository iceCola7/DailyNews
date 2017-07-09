package com.cxz.news.module.photo.beauty;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.Photos.BeautyPhotos;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;
import com.cxz.news.utils.XLog;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/25.
 */
public class BeautyPhotoPresenter implements BeautyPhotoContract.IPresenter {

    private BeautyPhotoContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(BeautyPhotoContract.IView view) {
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
    public void loadBeautyPhotos(final int pageSize) {
        Subscriber subscriber = new BaseSubscriber<BeautyPhotos>(new RequestCallback<BeautyPhotos>() {
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
            public void requestSuccess(BeautyPhotos data) {
                if (pageSize == 0){
                    mView.updateItems(data.getBeautyPhotoInfo());
                }else {
                    mView.updateMoreItems(data.getBeautyPhotoInfo());
                }
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.LIFE_NEWS_PHOTO)
                .getBeautyPhotos(pageSize)
                .subscribe(subscriber);
    }
}
