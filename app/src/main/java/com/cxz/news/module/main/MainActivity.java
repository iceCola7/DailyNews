package com.cxz.news.module.main;

import android.widget.Button;
import android.widget.TextView;

import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.IPresenter> implements MainContract.IView{

    @BindView(R.id.btn_load)
    Button btn_load;
    @BindView(R.id.tv_weather)
    TextView tv_weather;

    @Override
    protected MainContract.IPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }
}
