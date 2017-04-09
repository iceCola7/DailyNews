package com.cxz.news.module.news.detail;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cxz.news.Constant;
import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.bean.news.Story;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity<NewsDetailContract.IPresenter> implements NewsDetailContract.IView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.web_view)
    WebView mWebView;

    private String mNewsId;

    @Override
    protected NewsDetailContract.IPresenter createPresenter() {
        return new NewsDetailPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolbar,true,"");
        initCollapsingToolbarLayout();
        initWebView();

        mNewsId = getIntent().getStringExtra(Constant.NEWS_EXTRA_ID);
        mPresenter.loadStoryDetailById(mNewsId);
    }

    private void initCollapsingToolbarLayout(){
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void initWebView(){
        mWebView.setScrollbarFadingEnabled(true);
        //能够和js交互
        mWebView.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        mWebView.getSettings().setBuiltInZoomControls(false);
        //缓存
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        mWebView.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        mWebView.getSettings().setAppCacheEnabled(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateStoryDetail(Story story) {
    }

    @Override
    public void showResult(String result) {
        mWebView.loadDataWithBaseURL("x-data://base",result,"text/html","utf-8",null);
    }

    @Override
    public void showResultWithoutBody(String url) {

    }

    @Override
    public void showTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void showCover(String imageUrl) {
        Glide.with(this).load(imageUrl).centerCrop().into(iv_header);
    }
}
