package com.cxz.news.module.news.detail;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxz.news.Constant;
import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.bean.news.Story;
import com.cxz.news.utils.XLog;

import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity<NewsDetailContract.IPresenter> implements NewsDetailContract.IView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.iv_header)
    ImageView iv_header;

    private String mNewsId;

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
        initToolBar(mToolbar,true,"");

        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        mNewsId = getIntent().getStringExtra(Constant.NEWS_EXTRA_ID);
        mPresenter.loadStoryDetailById(mNewsId);
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
        mCollapsingToolbarLayout.setTitle(story.getTitle());
        Glide.with(this).load(story.getImage()).centerCrop().into(iv_header);
    }
}
