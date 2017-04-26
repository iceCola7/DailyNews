package com.cxz.news.module.photo.beauty;

import com.cxz.news.base.IBasePresenter;
import com.cxz.news.base.IBaseView;
import com.cxz.news.bean.Photos.BeautyPhotoInfo;
import com.cxz.news.bean.Photos.BeautyPhotos;

import java.util.List;

/**
 * Created by chenxz on 2017/4/25.
 */
public class BeautyPhotoContract {

    interface IView extends IBaseView {
        void updateItems(List<BeautyPhotoInfo> beautyPhotoInfos);
        void updateMoreItems(List<BeautyPhotoInfo> beautyPhotoInfos);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void loadBeautyPhotos(int pageSize);
    }

}
