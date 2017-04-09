package com.cxz.news.module.news.detail;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.news.Story;

/**
 * Created by chenxz on 2017/4/7.
 */
public class NewsDetailContract {

    interface IView extends IBaseView{
        void updateStoryDetail(Story story);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void loadStoryDetailById(String storyId);
    }

}
