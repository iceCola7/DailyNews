package com.cxz.news.module.photo.life;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.LifePhotosAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.bean.Photos.LifePhotoInfo;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/18.
 */
public class LifePhotoFragment extends BaseFragment<LifePhotoContract.IPresenter> implements LifePhotoContract.IView {

    @BindView(R.id.pull_load_recyclerView)
    PullLoadMoreRecyclerView mRecyclerView;
    private LifePhotosAdapter mAdapter;

    private String mSetId;//用于加载更多信息的ID

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
        mAdapter = new LifePhotosAdapter();
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.loadLifePhotos();
    }

    @Override
    public void updateItems(List<LifePhotoInfo> lifePhotoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.setLists(lifePhotoInfos);
        mSetId = lifePhotoInfos.get(lifePhotoInfos.size() - 1).getSetid();
    }

    @Override
    public void updateMoreItems(List<LifePhotoInfo> lifePhotoInfos) {
        mRecyclerView.setPullLoadMoreCompleted();
        mAdapter.appendLists(lifePhotoInfos);
        mSetId = lifePhotoInfos.get(lifePhotoInfos.size() - 1).getSetid();
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener{

        @Override
        public void onRefresh() {
            mPresenter.loadLifePhotos();
        }

        @Override
        public void onLoadMore() {
            mPresenter.loadMoreLifePhotos(mSetId);
        }
    }

}
