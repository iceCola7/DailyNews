package com.cxz.news.adapter.videos.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cxz.news.R;
import com.cxz.news.bean.videos.NeteastVideoSummary;
import com.cxz.news.common.Constants;
import com.cxz.news.module.video.detail.VideoDetailActivity;

/**
 * Created by chenxz on 2017/7/9.
 */

public class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tv_title;
    private ImageView iv_cover;
    private CardView mCardView;
    private Context mContext;
    private NeteastVideoSummary mVideo;

    public VideoHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.card);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
        mCardView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    public void bindData(NeteastVideoSummary data) {
        mVideo = data;
        tv_title.setText(data.getTitle());
        Glide.with(itemView.getContext()).load(data.getCover()).centerCrop().into(iv_cover);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.card) {
            Intent intent = new Intent(mContext, VideoDetailActivity.class);
            intent.putExtra(Constants.VIDEO_MP4_URL, mVideo.getMp4_url());
            intent.putExtra(Constants.VIDEO_TITLE, mVideo.getTitle());
            mContext.startActivity(intent);
        }
    }
}
