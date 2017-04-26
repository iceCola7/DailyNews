package com.cxz.news.module.photo.beauty;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.BeautyPhotosAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.bean.Photos.BeautyPhotoInfo;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/25.
 */
public class BeautyPhotoFragment extends BaseFragment<BeautyPhotoContract.IPresenter> implements BeautyPhotoContract.IView {

    @BindView(R.id.pull_load_recyclerView)
    PullLoadMoreRecyclerView mRecyclerView;

    private BeautyPhotosAdapter mAdapter;
    private int pageSize = 0;

    @Override
    protected BeautyPhotoContract.IPresenter createPresenter() {
        return new BeautyPhotoPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_beauty;
    }

    @Override
    protected void initViews() {
        mAdapter = new BeautyPhotosAdapter();
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.loadBeautyPhotos(pageSize);
    }

    @Override
    public void updateItems(List<BeautyPhotoInfo> beautyPhotoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.setLists(beautyPhotoInfos);
    }

    @Override
    public void updateMoreItems(List<BeautyPhotoInfo> beautyPhotoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.appendLists(beautyPhotoInfos);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener{

        @Override
        public void onRefresh() {
            pageSize = 0;
            mPresenter.loadBeautyPhotos(pageSize);
        }

        @Override
        public void onLoadMore() {
            pageSize++;
            mPresenter.loadBeautyPhotos(pageSize);
        }
    }

}
