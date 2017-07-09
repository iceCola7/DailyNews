package com.cxz.news.module.video.list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cxz.news.R;
import com.cxz.news.adapter.videos.VideoAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.bean.videos.NeteastVideoSummary;
import com.cxz.news.utils.XLog;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/26.
 */
public class VideoListFragment extends BaseFragment<VideoListContract.IPresenter> implements VideoListContract.IView {

    @BindView(R.id.pull_load_recyclerView)
    PullLoadMoreRecyclerView mRecyclerView;

    private VideoAdapter mAdapter;

    protected static final String VIDEO_ID = "video_id";
    protected static final String POSITION = "position";

    private String mVideoId;
    private int mPosition;
    private int mStartPage = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mVideoId = getArguments().getString(VIDEO_ID);
            mPosition = getArguments().getInt(POSITION);
        }
    }

    public static VideoListFragment newInstance(String newsId, int position){
        VideoListFragment fragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VIDEO_ID, newsId);
        bundle.putInt(POSITION,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected VideoListContract.IPresenter createPresenter() {
        return new VideoListPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_video_list;
    }

    @Override
    protected void initViews() {
        mAdapter = new VideoAdapter();
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setGridLayout(2);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.loadVideoList(mVideoId,mStartPage);
    }

    @Override
    public void updateVideoList(List<NeteastVideoSummary> lists) {
        mRecyclerView.setPullLoadMoreCompleted();
        if(lists.size() > 0)
            mAdapter.setLists(lists);
    }

    @Override
    public void updateMoreVideoList(List<NeteastVideoSummary> lists) {
        mRecyclerView.setPullLoadMoreCompleted();
        if(lists.size() > 0)
            mAdapter.appendLists(lists);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener{

        @Override
        public void onRefresh() {
            mStartPage = 0;
            mPresenter.loadVideoList(mVideoId,mStartPage);
        }

        @Override
        public void onLoadMore() {
            mStartPage += 10;
            mPresenter.loadVideoList(mVideoId,mStartPage);
        }
    }

}
