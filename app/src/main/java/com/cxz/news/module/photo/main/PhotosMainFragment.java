package com.cxz.news.module.photo.main;

import com.cxz.news.R;
import com.cxz.news.base.BaseFragment;

/**
 * Created by chenxz on 2017/4/12.
 */
public class PhotosMainFragment extends BaseFragment<PhotosMainContract.IPresenter> implements PhotosMainContract.IView {

    @Override
    protected PhotosMainContract.IPresenter createPresenter() {
        return new PhotosMainPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photos_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
