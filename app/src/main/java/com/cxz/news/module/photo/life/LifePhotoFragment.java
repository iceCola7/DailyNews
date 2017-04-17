package com.cxz.news.module.photo.life;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.GankPhotosAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.bean.Photos.GankPhotos;
import com.cxz.news.bean.Photos.PhotoInfo;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/16.
 */
public class LifePhotoFragment extends BaseFragment<LifePhotoContract.IPresenter> implements LifePhotoContract.IView {

    @BindView(R.id.pull_load_recyclerView)
    PullLoadMoreRecyclerView mRecyclerView;

    GankPhotosAdapter mAdapter;

    private int pageSize = 1;

    @Override
    protected LifePhotoContract.IPresenter createPresenter() {
        return new LifePhotoPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_life;
    }

    @Override
    protected void initViews() {
        mAdapter = new GankPhotosAdapter();
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setStaggeredGridLayout(2);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.loadGankPhotos(pageSize);
    }

    @Override
    public void updateItems(List<PhotoInfo> photoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.setLists(photoInfos);
    }

    @Override
    public void updateMoreItems(List<PhotoInfo> photoInfos) {
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
