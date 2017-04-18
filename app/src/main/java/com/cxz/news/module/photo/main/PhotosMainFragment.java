package com.cxz.news.module.photo.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.ViewPagerAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.module.main.MainActivity;
import com.cxz.news.module.photo.gank.GankPhotoFragment;
import com.cxz.news.module.photo.life.LifePhotoFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/12.
 */
public class PhotosMainFragment extends BaseFragment<PhotosMainContract.IPresenter> implements PhotosMainContract.IView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ViewPagerAdapter mViewPagerAdapter;

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
        initToolBar(mToolbar,false,getString(R.string.photos));
        initDrawerToggle(((MainActivity)mContext).getDrawerLayout(),mToolbar);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new GankPhotoFragment());
        fragments.add(new LifePhotoFragment());
        fragments.add(new LifePhotoFragment());
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        mViewPagerAdapter.setItems(fragments,new String[]{getString(R.string.photo_gank),getString(R.string.photo_life),getString(R.string.photo_life)});
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
