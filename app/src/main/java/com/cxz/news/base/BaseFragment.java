package com.cxz.news.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.App;
import com.cxz.news.R;
import com.cxz.news.module.main.MainContract;

import butterknife.ButterKnife;

/**
 * Created by chenxz on 2017/3/30.
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements MainContract.IView {

    protected T mPresenter;
    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;

    /**
     * 创建Presenter
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(attachLayoutRes(),null);
            ButterKnife.bind(this,mRootView);
            mPresenter = createPresenter();
            mPresenter.attachView(this);
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti){
            mIsMulti = true;
            updateViews(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti){
            mIsMulti = true;
            updateViews(false);
        }else{
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    /**
     * 获取 Application
     *
     * @return  Application
     */
    protected App getApp() {
        return App.getApp();
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((BaseActivity)getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }

    protected void initDrawerToggle(DrawerLayout drawerLayout, Toolbar toolbar){
        ((BaseActivity)getActivity()).initDrawerToggle(drawerLayout,toolbar);
    }

}
