package com.jay.app_dispatcher;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.jay.dispatcher_api.AppDispatcherManager;
import com.qlife.lib_app.appdelegate.AppLifeDispatcher;

public class MyApplication extends Application {


    private AppLifeDispatcher applicationDelegate;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        applicationDelegate = new AppLifeDispatcher(this);
        applicationDelegate.attachBaseContext(base);

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("AppDispatcher", "MyApplication, onCreate");
        AppDispatcherManager.init();
        AppDispatcherManager.onCreate(this);

        applicationDelegate.onCreate(this);

    }
}
