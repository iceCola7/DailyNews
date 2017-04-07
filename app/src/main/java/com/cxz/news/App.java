package com.cxz.news;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by chenxz on 2017/3/30.
 */
public class App extends Application {

    private static App mApp;
    private static RefWatcher mRefWatcher;

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

        initLeakCanary();

    }

    private void initLeakCanary() {
        mRefWatcher = LeakCanary.install(this);
    }
}
