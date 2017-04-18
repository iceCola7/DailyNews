package com.cxz.news.adapter.photos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxz.news.R;
import com.cxz.news.bean.Photos.GankPhotoInfo;

/**
 * Created by chenxz on 2017/4/17.
 */
public class PhotosViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public PhotosViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }

    public void bindData(GankPhotoInfo photoInfo){
        textView.setText(photoInfo.getDesc());
        Glide.with(itemView.getContext()).load(photoInfo.getUrl()).centerCrop().into(imageView);
    }

}
