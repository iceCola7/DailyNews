package com.cxz.news.module.photo.life;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.Photos.PhotoInfo;

import java.util.List;

/**
 * Created by chenxz on 2017/4/16.
 */
public class LifePhotoContract {

    interface IView extends IBaseView {
        void updateItems(List<PhotoInfo> photoInfos);
        void updateMoreItems(List<PhotoInfo> photoInfos);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void loadGankPhotos(int pageSize);
    }

}
