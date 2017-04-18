package com.cxz.news.module.photo.life;

import com.cxz.news.R;
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

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.loadLifePhotos();
    }

    @Override
    public void updateItems(List<LifePhotoInfo> lifePhotoInfos) {

    }

    @Override
    public void updateMoreItems(List<LifePhotoInfo> lifePhotoInfos) {

    }
}
