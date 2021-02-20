package com.jay.app_dispatcher;

import android.content.Context;
import android.util.Log;

import com.jay.dispatcher_annotation.AppDispatcher;
import com.jay.dispatcher_api.IAppLife;

/**
 * Created by hjy on 2018/10/23.
 */
@AppDispatcher
public class ModuleA implements IAppLife {

    @Override
    public int getPriority() {
        return NORM_PRIORITY;
    }

    @Override
    public void onCreate(Context context) {
        Log.d("AppDispatcher", "ModuleA, onCreate");
    }

    @Override
    public void onTerminate() {
        Log.d("AppDispatcher", "ModuleA, onTerminate");
    }


}
