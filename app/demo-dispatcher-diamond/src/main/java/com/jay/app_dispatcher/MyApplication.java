package com.jay.app_dispatcher;

import android.app.Application;
import android.util.Log;

import com.jay.dispatcher_api.AppDispatcherManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("AppDispatcher", "MyApplication, onCreate");
        AppDispatcherManager.init();
        AppDispatcherManager.onCreate(this);

    }
}
