package com.cxz.news.module.video.detail;

import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.common.Constants;

public class VideoDetailActivity extends BaseActivity<VideoDetailContract.IPresenter> implements VideoDetailContract.IView {

    private String mVideoTitle;
    private String mMp4Url;
    @Override
    protected VideoDetailContract.IPresenter createPresenter() {
        return new VideoDetailPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initViews() {
        if(getIntent() != null){
            mVideoTitle = getIntent().getStringExtra(Constants.VIDEO_TITLE);
            mMp4Url = getIntent().getStringExtra(Constants.VIDEO_MP4_URL);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
