package com.cxz.news.adapter.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.holder.BeautyPhotoHolder;
import com.cxz.news.bean.Photos.BeautyPhotoInfo;
import com.cxz.news.bean.Photos.LifePhotoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxz on 2017/4/25.
 */
public class BeautyPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BeautyPhotoInfo> mBeautyPhotoInfos;

    public BeautyPhotosAdapter(){
        mBeautyPhotoInfos = new ArrayList<>();
    }

    public void setLists(List<BeautyPhotoInfo> lists){
        mBeautyPhotoInfos.clear();
        appendLists(lists);
    }

    public void appendLists(List<BeautyPhotoInfo> lists){
        mBeautyPhotoInfos.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_beauty_item,null);
        return new BeautyPhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BeautyPhotoHolder)holder).bindData(mBeautyPhotoInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeautyPhotoInfos.size();
    }
}
