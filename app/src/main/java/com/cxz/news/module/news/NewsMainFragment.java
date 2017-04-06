package com.cxz.news.module.news;

import com.cxz.news.R;
import com.cxz.news.adapter.DailyStoriesAdapter;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.base.IBasePresenter;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.news.utils.XLog;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/4.
 */
public class NewsMainFragment extends BaseFragment<NewsMainContract.IPresenter> implements NewsMainContract.IView{

    @BindView(R.id.recycler_view)
    PullLoadMoreRecyclerView mRecyclerView;

    DailyStoriesAdapter mAdapter;

    @Override
    protected NewsMainContract.IPresenter createPresenter() {
        return new NewsMainPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_main;
    }

    @Override
    protected void initViews() {
        mAdapter = new DailyStoriesAdapter(getActivity());
        mRecyclerView.setRefreshing(false);
        mRecyclerView.setLinearLayout();
        mPresenter.loadLatestDailyStories();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void updateLatestDailyStories(DailyStories dailyStories) {
        XLog.e("----------------->"+dailyStories.getDate());

        mAdapter.setList(dailyStories);

        mRecyclerView.setAdapter(mAdapter);
    }
}
