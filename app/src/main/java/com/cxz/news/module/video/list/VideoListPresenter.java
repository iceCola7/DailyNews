package com.cxz.news.module.video.list;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.videos.NeteastVideoSummary;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;
/**
 * Created by chenxz on 2017/4/26.
 */
public class VideoListPresenter implements VideoListContract.IPresenter {

    private VideoListContract.IView mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(VideoListContract.IView view) {
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
    public void loadVideoList(final String id, int startPage) {
        Subscriber subscriber = new BaseSubscriber<List<NeteastVideoSummary>>(new RequestCallback<List<NeteastVideoSummary>>() {
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
            public void requestSuccess(List<NeteastVideoSummary> data) {
                mView.updateVideoList(data);
            }
        });
        mCompositeSubscription.add(subscriber);
        RetrofitManager.getInstance(HostType.NETEASE_NEWS_VIDEO)
                .getVideoList(id,startPage)
                .flatMap(
                        new Func1<Map<String, List<NeteastVideoSummary>>, Observable<NeteastVideoSummary>>() {
                            @Override
                            public Observable<NeteastVideoSummary> call(Map<String, List<NeteastVideoSummary>> map) {
                                // 通过id取到list
                                return Observable.from(map.get(id));
                            }
                        })
                .toSortedList(new Func2<NeteastVideoSummary, NeteastVideoSummary, Integer>() {
                    @Override
                    public Integer call(NeteastVideoSummary neteastVideoSummary, NeteastVideoSummary neteastVideoSummary2) {
                        // 时间排序
                        return neteastVideoSummary2.getPtime().compareTo(neteastVideoSummary.getPtime());
                    }
                })
                .subscribe(subscriber);
    }
}
