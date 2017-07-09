package com.cxz.news.adapter.videos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.R;
import com.cxz.news.adapter.videos.holder.VideoHolder;
import com.cxz.news.bean.videos.NeteastVideoSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxz on 2017/7/9.
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<NeteastVideoSummary> mVideoLists;

    public VideoAdapter(){
        mVideoLists = new ArrayList<>();
    }

    public void setLists(List<NeteastVideoSummary> lists){
        mVideoLists.clear();
        appendLists(lists);
    }

    public void appendLists(List<NeteastVideoSummary> lists){
        mVideoLists.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item,null);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VideoHolder)holder).bindData(mVideoLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideoLists.size();
    }
}
