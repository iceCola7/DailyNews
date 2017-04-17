package com.cxz.news.adapter.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.news.R;
import com.cxz.news.adapter.photos.holder.PhotosViewHolder;
import com.cxz.news.bean.Photos.PhotoInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chenxz on 2017/4/17.
 */
public class GankPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PhotoInfo> mPhotoInfos;
    private List<Integer> heightLists;//装产出的随机数

    public GankPhotosAdapter(){
        mPhotoInfos = new ArrayList<>();
        heightLists = new ArrayList<>();
    }

    public void setLists(List<PhotoInfo> photoInfos) {
        mPhotoInfos.clear();
        appendLists(photoInfos);
    }

    public void appendLists(List<PhotoInfo> photoInfos){
        mPhotoInfos.addAll(photoInfos);
        for (int i = 0; i < mPhotoInfos.size(); i++) {
            int height = new Random().nextInt(200) + 100;//[100,300)的随机数
            heightLists.add(height);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_recycler_item,null);
        return new PhotosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PhotoInfo photoInfo = mPhotoInfos.get(position);
        ((PhotosViewHolder)holder).bindData(photoInfo);
        ViewGroup.LayoutParams params = ((PhotosViewHolder)holder).getImageView().getLayoutParams();
        params.height=heightLists.get(position);
        ((PhotosViewHolder)holder).getImageView().setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return mPhotoInfos.size();
    }
}
