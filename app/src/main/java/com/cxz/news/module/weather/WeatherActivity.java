package com.cxz.news.module.weather;

import android.widget.Button;
import android.widget.TextView;

import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.bean.WeatherInfo;

import butterknife.BindView;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity<WeatherContract.IPresenter> implements WeatherContract.IView {

    @BindView(R.id.btn_load)
    Button btn_load;
    @BindView(R.id.tv_weather)
    TextView tv_result;

    @Override
    protected WeatherContract.IPresenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void updateWeather(WeatherInfo weatherInfo) {
        tv_result.setText(weatherInfo.toString());
    }

    @OnClick(R.id.btn_load)
    public void btn_load(){
        mPresenter.loadWeatherInfoData("101010100");
    }

}
