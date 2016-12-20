package com.example.dllo.hodgepodge.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by shuaiwang on 16/12/20.
 */

public class MyApp extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    public static Context getContext() {
        return mContext;
    }
}
