package com.cxz.news.module.photo.gank;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.GankPhotosAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.bean.Photos.GankPhotoInfo;
import com.cxz.news.module.photo.SpacesItemDecoration;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/16.
 */
public class GankPhotoFragment extends BaseFragment<GankPhotoContract.IPresenter> implements GankPhotoContract.IView {

    @BindView(R.id.pull_load_recyclerView)
    PullLoadMoreRecyclerView mRecyclerView;

    GankPhotosAdapter mAdapter;

    private int pageSize = 1;

    @Override
    protected GankPhotoContract.IPresenter createPresenter() {
        return new GankPhotoPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_gank;
    }

    @Override
    protected void initViews() {
        mAdapter = new GankPhotosAdapter();
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setStaggeredGridLayout(2);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mRecyclerView.setAdapter(mAdapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecyclerView.getRecyclerView().addItemDecoration(decoration);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.loadGankPhotos(pageSize);
    }

    @Override
    public void updateItems(List<GankPhotoInfo> photoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.setLists(photoInfos);
    }

    @Override
    public void updateMoreItems(List<GankPhotoInfo> photoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.appendLists(photoInfos);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            pageSize = 1;
            mPresenter.loadGankPhotos(pageSize);
        }

        @Override
        public void onLoadMore() {
            pageSize++;
            mPresenter.loadGankPhotos(pageSize);
        }
    }

}
