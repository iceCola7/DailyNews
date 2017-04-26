package com.cxz.news.adapter.photos.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxz.news.R;
import com.cxz.news.bean.Photos.BeautyPhotoInfo;

/**
 * Created by chenxz on 2017/4/26.
 */
public class BeautyPhotoHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView tv_title;
    private TextView tv_source;
    private Context mContext;

    public BeautyPhotoHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image1);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        tv_source = (TextView) itemView.findViewById(R.id.tv_source);
        mContext = itemView.getContext();
    }

    public void bindData(BeautyPhotoInfo beautyPhotoInfo){
        tv_title.setText(beautyPhotoInfo.getTitle());
        tv_source.setText(beautyPhotoInfo.getSource());
        Glide.with(mContext).load(beautyPhotoInfo.getImgsrc()).fitCenter().into(imageView);
    }

}
