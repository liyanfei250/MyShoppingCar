package com.example.lzq.mycar.application;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by jzj on 2016/4/19 0019.
 */
public class Application extends LitePalApplication {
    public static Context mAppContext;

    public static Context getmAppContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mAppContext = null;
    }
}
