package com.cxz.news.module.news;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.news.DailyStories;

/**
 * Created by chenxz on 2017/4/4.
 */
public class NewsMainContract {

    interface IView extends IBaseView{
        void updateLatestDailyStories(DailyStories dailyStories);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void loadLatestDailyStories();
    }

}
