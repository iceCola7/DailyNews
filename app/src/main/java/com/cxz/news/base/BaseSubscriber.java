package com.cxz.news.base;
import com.cxz.news.App;
import com.cxz.news.callback.RequestCallback;
import com.cxz.news.utils.NetUtil;
import com.cxz.news.utils.XLog;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by chenxz on 2017/3/31.
 * 把回调各个方法统一处理，并且这里对返回错误做了统一处理
 */
public class BaseSubscriber<T> extends Subscriber<T> {

    private RequestCallback<T> mRequestCallback;

    public BaseSubscriber(RequestCallback<T> requestCallback){
        this.mRequestCallback = requestCallback;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mRequestCallback != null){
            mRequestCallback.beforeRequest();
        }
    }

    @Override
    public void onCompleted() {
        if (mRequestCallback != null) {
            mRequestCallback.requestComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mRequestCallback != null) {
            String errorMsg = null;
            if (e instanceof HttpException) {
                switch (((HttpException) e).code()) {
                    case 403:
                        errorMsg = "没有权限访问此链接！";
                        break;
                    case 504:
                        if (!NetUtil.isConnected(App.getApp())) {
                            errorMsg = "没有联网哦！";
                        } else {
                            errorMsg = "网络连接超时！";
                        }
                        break;
                    default:
                        errorMsg = ((HttpException) e).message();
                        break;
                }
            } else if (e instanceof UnknownHostException) {
                errorMsg = "不知名主机！";
            } else if (e instanceof SocketTimeoutException) {
                errorMsg = "网络连接超时！";
            }else if (e instanceof JsonMappingException){
                errorMsg = "未知异常！";
            }
            XLog.e(e.toString());
            mRequestCallback.requestError(errorMsg);
        }
    }

    @Override
    public void onNext(T t) {
        if (mRequestCallback != null) {
            mRequestCallback.requestSuccess(t);
        }
    }
}
