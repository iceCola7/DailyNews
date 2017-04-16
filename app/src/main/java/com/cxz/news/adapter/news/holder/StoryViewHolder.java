package com.cxz.news.adapter.news.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxz.news.common.Constants;
import com.cxz.news.R;
import com.cxz.news.bean.news.Story;
import com.cxz.news.module.news.detail.NewsDetailActivity;

/**
 * Created by chenxz on 2017/4/5.
 */
public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final CardView mCardView;
    private final TextView tvTitle;
    private final ImageView ivStoryImage;
    private final ImageView ivMultiPic;
    private Story mStory;
    private Context mContext;

    public StoryViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.card);
        tvTitle = (TextView) itemView.findViewById(R.id.title);
        ivStoryImage = (ImageView) itemView.findViewById(R.id.image);
        ivMultiPic = (ImageView) itemView.findViewById(R.id.multiPic);
        mCardView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.card) {
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra(Constants.NEWS_EXTRA_ID,String.valueOf(mStory.getId()));
            mContext.startActivity(intent);
        }
    }

    public void bindStoryView(Story story) {
        mStory = story;
        tvTitle.setText(mStory.getTitle());
        String imageUrl = mStory.getImages() == null ? "" : mStory.getImages().get(0);
        if (TextUtils.isEmpty(imageUrl) || TextUtils.isEmpty(mStory.getMultiPic())) {
            ivMultiPic.setVisibility(View.GONE);
        } else if (Boolean.valueOf(mStory.getMultiPic())) {
            ivMultiPic.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(imageUrl)) {
            ivStoryImage.setVisibility(View.GONE);
        } else {
            ivStoryImage.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(imageUrl).centerCrop().into(ivStoryImage);
        }
    }
}
