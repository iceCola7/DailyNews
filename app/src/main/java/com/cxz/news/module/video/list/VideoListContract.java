package com.cxz.news.module.video.list;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.videos.NeteastVideoSummary;

import java.util.List;

/**
 * Created by chenxz on 2017/4/26.
 */
public class VideoListContract {

    interface IView extends IBaseView {
        void updateVideoList(List<NeteastVideoSummary> videoList);
        void updateMoreVideoList(List<NeteastVideoSummary> videoList);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void loadVideoList(String id, int startPage);
    }

}
