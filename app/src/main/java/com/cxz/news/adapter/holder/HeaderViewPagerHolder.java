package com.cxz.news.adapter.holder;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.R;
import com.cxz.news.bean.news.Story;
import com.cxz.news.module.news.detail.NewsDetailActivity;
import com.cxz.news.Constant;
import com.cxz.news.widget.CirclePageIndicator;
import com.cxz.news.widget.MyViewPager;
import com.cxz.news.widget.StoryHeaderView;

import java.util.List;

/**
 * Created by chenxz on 2017/4/5.
 */
public class HeaderViewPagerHolder extends RecyclerView.ViewHolder {

    private final MyViewPager mViewPager;
    private final CirclePageIndicator mPageIndicator;
    private PagerAdapter mPagerAdapter;

    public HeaderViewPagerHolder(View itemView, List<Story> stories) {
        super(itemView);
        mViewPager = (MyViewPager) itemView.findViewById(R.id.viewPager);
        mPageIndicator = (CirclePageIndicator) itemView.findViewById(R.id.indicator);
        if (!(stories != null && stories.size() != 0)) {
            return;
        } else if (stories.size() < 2) {
            mPageIndicator.setVisibility(View.GONE);
        }
        mPagerAdapter = new HeaderPagerAdapter(stories);
    }

    public void bindHeaderView(){
        if (mViewPager.getAdapter() == null) {
            mViewPager.setAdapter(mPagerAdapter);
            mPageIndicator.setViewPager(mViewPager);
        } else {
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    public boolean isAutoScrolling() {
        if (mViewPager != null) {
            return mViewPager.isAutoScrolling();
        }
        return false;
    }

    public void stopAutoScroll() {
        if (mViewPager != null) {
            mViewPager.stopAutoScroll();
        }
    }

    public void startAutoScroll() {
        if (mViewPager != null) {
            mViewPager.startAutoScroll();
        }
    }

    private final static class HeaderPagerAdapter extends PagerAdapter {
        private final List<Story> mStories;

        private int mChildCount;

        public HeaderPagerAdapter(List<Story> stories) {
            mStories = stories;
        }

        @Override
        public int getCount() {
            return mStories == null ? 0 : mStories.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            StoryHeaderView storyHeaderView = StoryHeaderView.newInstance(container);
            final Story story = mStories.get(position);
            storyHeaderView.bindData(story.getTitle(), story.getImageSource(), story.getImage());
            storyHeaderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
                    intent.putExtra(Constant.NEWS_EXTRA_ID,String.valueOf(story.getId()));
                    v.getContext().startActivity(intent);
                }
            });
            container.addView(storyHeaderView);
            return storyHeaderView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((StoryHeaderView) object);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            mChildCount = getCount();
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }
    }

}
