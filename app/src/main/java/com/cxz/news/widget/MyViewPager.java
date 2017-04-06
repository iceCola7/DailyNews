package com.cxz.news.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.cxz.news.utils.XLog;

/**
 * Created by chenxz on 2017/4/5.
 */
public class MyViewPager extends ViewPager {
    private static final int WHAT_SCROLL = 0;
    private final long mDelayTime = 5000;

    private boolean mIsAutoScroll;

    public MyViewPager(Context context) {
        super(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startAutoScroll() {
        mIsAutoScroll = true;
        sendScrollMessage(mDelayTime);
    }

    public void stopAutoScroll(){
        mIsAutoScroll = false;
        handler.removeMessages(WHAT_SCROLL);
    }

    public boolean isAutoScrolling(){
        return mIsAutoScroll;
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_SCROLL) {
                scrollOnce();
                sendScrollMessage(mDelayTime);
            }
        }
    };

    private void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        int count;
        if (adapter == null || (count = adapter.getCount()) < 1) {
            stopAutoScroll();
            return;
        }
        if (currentItem < count) {
            currentItem++;
        }
        if (currentItem == count) {
            currentItem = 0;
        }
        XLog.i("currentItem : " + currentItem);
        setCurrentItem(currentItem);
    }

    private void sendScrollMessage(long delayTimeMills) {
        if (mIsAutoScroll) {
            handler.removeMessages(WHAT_SCROLL);
            handler.sendEmptyMessageDelayed(WHAT_SCROLL, delayTimeMills);
        }
    }
}
