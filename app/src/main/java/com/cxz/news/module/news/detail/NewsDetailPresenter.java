package com.cxz.news.module.news.detail;

import android.content.Context;
import android.content.res.Configuration;

import com.cxz.news.base.BaseSubscriber;
import com.cxz.news.bean.news.Story;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.retrofit.manager.RetrofitManager;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chenxz on 2017/4/7.
 */
public class NewsDetailPresenter implements NewsDetailContract.IPresenter {
    protected NewsDetailContract.IView mView;

    private CompositeSubscription mCompositeSubscription;

    private Context mContext;
    public NewsDetailPresenter(Context context){
        this.mContext = context;
    }

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

    @Override
    public void loadStoryDetailById(String storyId) {
        Subscriber subscriber = new BaseSubscriber<Story>(new RequestCallback<Story>() {
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
            public void requestSuccess(Story data) {
                //mView.updateStoryDetail(data);
                mView.showTitle(data.getTitle());
                mView.showCover(data.getImage());
                mView.showResult(convertNewsContent(data.getBody()));
            }
        });
        RetrofitManager.getInstance(HostType.ZHIHU_NEWS_INFO)
                .getStoryDetailById(storyId)
                .subscribe(subscriber);
    }

    private String convertNewsContent(String result){

        result = result.replace("<div class=\"img-place-holder\">", "");
        result = result.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">";

        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
        String theme = "<body className=\"\" onload=\"onLoaded()\">";
        if ((mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES){
            theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
        }

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(theme)
                .append(result)
                .append("</body></html>").toString();
    }

}
