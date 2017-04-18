package com.cxz.news.module.photo.life;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.Photos.LifePhotoInfo;

import java.util.List;

/**
 * Created by chenxz on 2017/4/18.
 */
public class LifePhotoContract {

    interface IView extends IBaseView{
        void updateItems(List<LifePhotoInfo> lifePhotoInfos);

        void updateMoreItems(List<LifePhotoInfo> lifePhotoInfos);
    }

    interface IPresenter extends IBasePresenter<IView>{
        void loadLifePhotos();

        void loadMoreLifePhotos(String setId);
    }

}
