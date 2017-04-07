package com.cxz.news.module.news.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.base.IBasePresenter;

public class NewsDetailActivity extends BaseActivity<NewsDetailContract.IPresenter> implements NewsDetailContract.IView {


    @Override
    protected NewsDetailContract.IPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
