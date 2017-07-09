package com.cxz.news.module.video.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.ViewPagerAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.module.main.MainActivity;
import com.cxz.news.module.video.list.VideoListFragment;
import com.cxz.news.retrofit.Api;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/26.
 */
public class VideosMainFragment extends BaseFragment<VideosMainContract.IPresenter> implements VideosMainContract.IView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected VideosMainContract.IPresenter createPresenter() {
        return new VideosMainPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photos_main;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolbar,false,getString(R.string.videos));
        initDrawerToggle(((MainActivity)mContext).getDrawerLayout(),mToolbar);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_HOT_ID,0));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_ENTERTAINMENT_ID,1));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_FUN_ID,2));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_CHOICE_ID,3));
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        mViewPagerAdapter.setItems(fragments,new String[]{getString(R.string.video_hot),getString(R.string.video_entertainment),getString(R.string.video_funny),getString(R.string.video_boutique)});
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}