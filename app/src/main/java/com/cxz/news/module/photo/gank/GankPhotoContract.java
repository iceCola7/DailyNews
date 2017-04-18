package com.cxz.news.module.photo.gank;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.Photos.GankPhotoInfo;

import java.util.List;

/**
 * Created by chenxz on 2017/4/16.
 */
public class GankPhotoContract {

    interface IView extends IBaseView {
        void updateItems(List<GankPhotoInfo> photoInfos);
        void updateMoreItems(List<GankPhotoInfo> photoInfos);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void loadGankPhotos(int pageSize);
    }

}
