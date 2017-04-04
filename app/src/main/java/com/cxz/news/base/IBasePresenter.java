package com.cxz.news.base;

/**
 * Created by chenxz on 2017/3/30.
 */
public interface IBasePresenter<T extends IBaseView> {

    void attachView(T view);

    void subscribe();

    void unsubscribe();

}
