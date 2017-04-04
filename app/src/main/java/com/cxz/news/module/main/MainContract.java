package com.cxz.news.module.main;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;

/**
 * Created by chenxz on 2017/3/30.
 */
public interface MainContract {

    interface IView extends IBaseView {
    }

    interface IPresenter extends IBasePresenter<IView> {
    }

}
