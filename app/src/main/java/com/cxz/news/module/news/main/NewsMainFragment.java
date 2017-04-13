package com.cxz.news.module.news.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cxz.news.R;
import com.cxz.news.adapter.DailyStoriesAdapter;
import com.cxz.news.adapter.holder.DateViewHolder;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.base.BaseFragment;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.recyclerview.PullLoadMoreRecyclerView;

import butterknife.BindView;

/**
 * Created by chenxz on 2017/4/4.
 */
public class NewsMainFragment extends BaseFragment<NewsMainContract.IPresenter> implements NewsMainContract.IView{

    @BindView(R.id.pull_load_recyclerView)
    PullLoadMoreRecyclerView mRecyclerView;

    DailyStoriesAdapter mAdapter;
    private String mDate;
    private LinearLayoutManager mLinearLayoutManager;

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
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        mLinearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mPresenter.loadLatestDailyStories();

        mRecyclerView.getRecyclerView().setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                changeActionBarTitle(dy);
            }
        });

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void updateLatestDailyStories(DailyStories dailyStories) {
        if (dailyStories != null) {
            mDate = dailyStories.getDate();
            mRecyclerView.setPullLoadMoreCompleted();
            mAdapter.setList(dailyStories);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void updateBeforeDailyStories(DailyStories dailyStories) {
        if (dailyStories != null) {
            mDate = dailyStories.getDate();
            mRecyclerView.setPullLoadMoreCompleted();
            mAdapter.appendList(dailyStories);
            mAdapter.notifyDataSetChanged();
        }
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            mPresenter.loadLatestDailyStories();
        }

        @Override
        public void onLoadMore() {
            mPresenter.loadBeforeDailyStories(mDate);
        }
    }

    private String mTitle;
    private int lastTitLePos = -1;
    /**
     * 修改toolbar title
     * @param dy
     */
    private void changeActionBarTitle(int dy){
        int position = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (lastTitLePos == position)
            return;
        DailyStoriesAdapter.Item item = mAdapter.getItem(position);
        int type = item.getType();
        if (type == DailyStoriesAdapter.Type.TYPE_HEADER){
            mTitle = getString(R.string.news_home);
        }else if (dy > 0 && type == DailyStoriesAdapter.Type.TYPE_DATE){
            mTitle = DateViewHolder.getDate(item.getDate(),getActivity());
        }else if (dy <= 0){
            mTitle = DateViewHolder.getDate(mAdapter.getTitleBeforePosition(position),getActivity());
        }
        ((BaseActivity)getActivity()).getSupportActionBar().setTitle(mTitle);
        lastTitLePos = position;
    }

}
