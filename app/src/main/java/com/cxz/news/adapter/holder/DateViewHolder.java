package com.cxz.news.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cxz.news.R;
import com.cxz.news.utils.DateUtils;

/**
 * Created by chenxz on 2017/4/5.
 */
public class DateViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvDate;

    public DateViewHolder(View itemView) {
        super(itemView);
        tvDate = (TextView) itemView.findViewById(R.id.date);
    }

    public void bindDate(String date) {
        tvDate.setText(getDate(date, itemView.getContext()));
    }

    public static String getDate(String date, Context context) {
        if (DateUtils.isToday(date)) {
            return context.getResources().getString(R.string.today_title);
        } else {
            return DateUtils.getMainPageDate(date);
        }
    }
}
