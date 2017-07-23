package com.cxz.news.module.video.detail;

import android.net.Uri;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cxz.news.BuildConfig;
import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.common.Constants;
import com.cxz.news.utils.ViewUtil;
import com.cxz.news.utils.ijkplayer.IjkController;
import com.cxz.news.utils.ijkplayer.widget.media.IjkVideoView;
import com.cxz.news.widget.ThreePointLoadingView;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

public class VideoDetailActivity extends BaseActivity<VideoDetailContract.IPresenter> implements VideoDetailContract.IView, View.OnClickListener , IjkController.ITrackHolder {

    @BindView(R.id.video_play)
    IjkVideoView mVideoView;
    @BindView(R.id.loading_view)
    ThreePointLoadingView mLoadingView;

    IjkController mIjkController;

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
        ViewUtil.setFullScreen(this);
        getWindow().setBackgroundDrawable(null);
        if(getIntent() != null){
            mVideoTitle = getIntent().getStringExtra(Constants.VIDEO_TITLE);
            mMp4Url = getIntent().getStringExtra(Constants.VIDEO_MP4_URL);
            mPresenter.playVideo(mMp4Url,mVideoTitle);
        }
        mLoadingView.setOnClickListener(this);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mVideoView.isPlaying()){
            mVideoView.start();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIjkController != null) {
            mIjkController.onDestroy();
        }
        mVideoView.release(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mIjkController != null && mIjkController.isShowing()) {
            mIjkController.hide();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        mLoadingView.play();
    }

    @Override
    public void hideLoading() {
        mLoadingView.stop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLoadingView.setBackground(null);
        } else {
            mLoadingView.setBackgroundDrawable(null);
        }
    }

    @Override
    public void playVideo(String url, String name) {
        try{
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
            mVideoView.setVideoURI(Uri.parse(url));

            mIjkController = new IjkController(this,name);
            mVideoView.setMediaController(mIjkController);

            mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    hideLoading();
                    mVideoView.start();
                    mIjkController.show();
                }
            });

            mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                    hideLoading();
                    Toast.makeText(VideoDetailActivity.this,"视频播放出错了！",Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }catch (UnsatisfiedLinkError e){
            e.printStackTrace();
            hideLoading();
            Toast.makeText(VideoDetailActivity.this,
                    "你的CPU是" + Build.CPU_ABI + ",当前播放器使用的编译版本" + BuildConfig.FLAVOR + "不匹配，需要参考app/build.gradle的productFlavors，在Build Variants选择适合的CPU的版本Run App或者打包哦！",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public ITrackInfo[] getTrackInfo() {
        return mVideoView == null ? null : mVideoView.getTrackInfo();
    }

    @Override
    public int getSelectedTrack(int trackType) {
        return mVideoView == null ? -1 : mVideoView.getSelectedTrack(trackType);
    }

    @Override
    public void selectTrack(int stream) {
        if(mVideoView != null){
            mVideoView.selectTrack(stream);
        }
    }

    @Override
    public void deselectTrack(int stream) {
        if (mVideoView != null){
            mVideoView.deselectTrack(stream);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
