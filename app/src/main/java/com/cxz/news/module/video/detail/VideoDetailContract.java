package com.cxz.news.module.video.detail;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;

/**
 * Created by chenxz on 2017/7/9.
 */

public class VideoDetailContract {

    interface IView extends IBaseView{
        void playVideo(String url, String name);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void playVideo(String url, String name);
    }

}
