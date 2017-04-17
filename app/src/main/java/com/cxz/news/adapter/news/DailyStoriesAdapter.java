package com.cxz.news.adapter.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.R;
import com.cxz.news.adapter.news.holder.DateViewHolder;
import com.cxz.news.adapter.news.holder.HeaderViewPagerHolder;
import com.cxz.news.adapter.news.holder.StoryViewHolder;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.news.bean.news.Story;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenxz on 2017/4/5.
 */
public class DailyStoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Item> mItems;
    private final List<Item> mTmpItems;
    private HeaderViewPagerHolder mViewPagerHolder;

    public static class Type {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_DATE = 1;
        public static final int TYPE_STORY = 2;
    }

    public DailyStoriesAdapter() {
        mItems = new ArrayList<>();
        mTmpItems = new ArrayList<>();
    }

    public void setList(DailyStories dailyStories) {
        mItems.clear();
        appendList(dailyStories);
    }

    public void appendList(DailyStories dailyStories) {
        int positionStart = mItems.size();

        if (positionStart == 0) {
            Item header = new Item();
            header.setType(Type.TYPE_HEADER);
            header.setStories(dailyStories.getTopStories());
            mItems.add(header);
        }
        Item dateItem = new Item();
        dateItem.setType(Type.TYPE_DATE);
        dateItem.setDate(dailyStories.getDate());
        mItems.add(dateItem);

        List<Story> stories = dailyStories.getStories();
        for (Story story : stories) {
            Item storyItem = new Item();
            storyItem.setType(Type.TYPE_STORY);
            storyItem.setStory(story);
            mItems.add(storyItem);
        }

        int itemCount = mItems.size() - positionStart;

        if (positionStart == 0) {
            notifyDataSetChanged();
        }else{
            notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof HeaderViewPagerHolder) {
            HeaderViewPagerHolder pagerHolder = (HeaderViewPagerHolder) holder;
            if (pagerHolder.isAutoScrolling()) {
                pagerHolder.stopAutoScroll();
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof HeaderViewPagerHolder) {
            HeaderViewPagerHolder pagerHolder = (HeaderViewPagerHolder) holder;
            if (!pagerHolder.isAutoScrolling()) {
                pagerHolder.startAutoScroll();
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case Type.TYPE_HEADER:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_header_viewpager, null);
                return new HeaderViewPagerHolder(itemView, mItems.get(0).getStories());
            case Type.TYPE_DATE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_item_date, null);
                return new DateViewHolder(itemView);
            case Type.TYPE_STORY:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_item_story, null);
                return new StoryViewHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Item item = mItems.get(position);
        switch (viewType) {
            case Type.TYPE_HEADER:
                mViewPagerHolder = ((HeaderViewPagerHolder) holder);
                mViewPagerHolder.bindHeaderView();
                break;
            case Type.TYPE_DATE:
                ((DateViewHolder) holder).bindDate(item.getDate());
                break;
            case Type.TYPE_STORY:
                ((StoryViewHolder) holder).bindStoryView(item.getStory());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    public Item getItem(int position) {
        return mItems.get(position);
    }

    public String getTitleBeforePosition(int position) {
        mTmpItems.clear();
        mTmpItems.addAll(mItems.subList(0, position + 1));
        Collections.reverse(mTmpItems);
        for (Item item : mTmpItems) {
            if (item.getType() == Type.TYPE_DATE) {
                return item.getDate();
            }
        }
        return "";
    }

    public static class Item {
        private int type;
        private String date;
        private Story story;
        private List<Story> stories;

        public int getType() {
            return type;
        }

        public String getDate() {
            return date;
        }

        public Story getStory() {
            return story;
        }

        public List<Story> getStories() {
            return stories;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setStory(Story story) {
            this.story = story;
        }

        public void setStories(List<Story> stories) {
            this.stories = stories;
        }
    }

    public void stopAutoScroll(){
        mViewPagerHolder.stopAutoScroll();
    }

}
