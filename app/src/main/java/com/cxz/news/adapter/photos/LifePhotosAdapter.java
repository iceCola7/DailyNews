package com.cxz.news.adapter.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.holder.LifePhotoHolder;
import com.cxz.news.bean.Photos.LifePhotoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxz on 2017/4/18.
 */
public class LifePhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LifePhotoInfo> mLifePhotoInfos;

    public LifePhotosAdapter(){
        mLifePhotoInfos = new ArrayList<>();
    }

    public void setLists(List<LifePhotoInfo> lists){
        mLifePhotoInfos.clear();
        appendLists(lists);
    }

    public void appendLists(List<LifePhotoInfo> lists){
        mLifePhotoInfos.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_life_item,null);
        return new LifePhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LifePhotoHolder)holder).bindData(mLifePhotoInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return mLifePhotoInfos.size();
    }
}
