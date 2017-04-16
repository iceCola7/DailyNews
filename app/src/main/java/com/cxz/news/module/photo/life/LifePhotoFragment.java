package com.cxz.news.module.photo.life;

import com.cxz.news.R;
import com.cxz.news.base.BaseFragment;

/**
 * Created by chenxz on 2017/4/16.
 */
public class LifePhotoFragment extends BaseFragment<LifePhotoContract.IPresenter> implements LifePhotoContract.IView {

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

    }
}
