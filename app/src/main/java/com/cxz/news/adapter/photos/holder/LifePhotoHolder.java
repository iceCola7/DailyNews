package com.cxz.news.adapter.photos.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxz.news.R;
import com.cxz.news.bean.Photos.LifePhotoInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxz on 2017/4/18.
 */
public class LifePhotoHolder extends RecyclerView.ViewHolder {

    private TextView tv_title;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    private Context mContext;

    public LifePhotoHolder(View itemView) {
        super(itemView);
        tv_title = (TextView) itemView.findViewById(R.id.title);
        iv1 = (ImageView) itemView.findViewById(R.id.image1);
        iv2 = (ImageView) itemView.findViewById(R.id.image2);
        iv3 = (ImageView) itemView.findViewById(R.id.image3);
        mContext = itemView.getContext();
    }

    public void bindData(LifePhotoInfo lifePhotoInfo){
        tv_title.setText(lifePhotoInfo.getSetname());
        ImageView imageView;
        for (int i = 0; i < lifePhotoInfo.getPics().size(); i++) {
            imageView = i==0?iv1:i==1?iv2:i==2?iv3:null;
            if(imageView != null)
                Glide.with(mContext).load(lifePhotoInfo.getPics().get(i)).fitCenter().into(imageView);
        }
    }

}
