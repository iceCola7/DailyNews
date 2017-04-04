package com.cxz.news;

import android.app.Application;

/**
 * Created by chenxz on 2017/3/30.
 */
public class App extends Application {

    private static App mApp;

    public static App getApp() {
        if (mApp == null){
            synchronized (App.class){
                if (mApp == null){
                    mApp = new App();
                }
            }
        }
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
