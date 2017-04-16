package com.cxz.news.module.news.detail;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cxz.news.common.Constants;
import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;

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
    @BindView(R.id.tv_image_source)
    TextView tv_image_source;

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

        mNewsId = getIntent().getStringExtra(Constants.NEWS_EXTRA_ID);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }else if (id == R.id.action_copy_link){
            mPresenter.copyLink();
            return true;
        }else if (id == R.id.action_copy_text){
            mPresenter.copyText();
            return true;
        }else if (id == R.id.action_open_in_browser){
            mPresenter.openInBrowser();
            return true;
        }else if (id == R.id.action_share){
            mPresenter.shareAsText();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResult(String result) {
        mWebView.loadDataWithBaseURL("x-data://base",result,"text/html","utf-8",null);
    }

    @Override
    public void showResultWithoutBody(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void showTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void showImageSource(String source) {
        tv_image_source.setText(source);
    }

    @Override
    public void showCover(String imageUrl) {
        Glide.with(this).load(imageUrl).centerCrop().into(iv_header);
    }

    @Override
    public void showCopySuccess() {
        Toast.makeText(this,"复制成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBrowserNotFoundError() {
        Toast.makeText(this,"未找到浏览器",Toast.LENGTH_LONG).show();
    }
}
