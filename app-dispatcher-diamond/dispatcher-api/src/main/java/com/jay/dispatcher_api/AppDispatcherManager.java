package com.jay.dispatcher_api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */
public class AppDispatcherManager {


    private static final List<IAppLife> appLifeList = new ArrayList<>();

    /**
     * 改方法内容会在编译时写入
     * （找到@AppLifecycle注解的类 对应的代理类后）调用registerApplicationLifecycleCallbacks
     */
    public static void init() {
        Log.d("AppDispatcher", "");
    }


    public static void registerApplicationLifecycleCallbacks(IAppLife appLife) {
        appLifeList.add(appLife);
    }

    public static void registerApplicationLifecycleCallbacks(String appLifecycleClassName) {
        if (TextUtils.isEmpty(appLifecycleClassName)) {
            return;
        }
        try {
            Object object = Class.forName(appLifecycleClassName).getConstructor().newInstance();
            if (object instanceof IAppLife) {
                registerApplicationLifecycleCallbacks((IAppLife) object);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void onCreate(Context context) {
        Log.d("AppDispatcher", "MyApplication, onCreate");
        Log.d("AppDispatcher", "MyApplication, appLifeList" + appLifeList);
        if (appLifeList.isEmpty()) {
            return;
        }
        Collections.sort(appLifeList, new AppLifeComparator());
        Log.d("AppDispatcher", "MyApplication, sorted  appLifeList" + appLifeList);
        for (IAppLife callbacks : appLifeList) {
            callbacks.onCreate(context);
        }
    }

//    public static void onTerminate() {
//        if (appLifeList.isEmpty()) {
//            return;
//        }
//        for (IAppLife callbacks : appLifeList) {
//            callbacks.onTerminate();
//        }
//    }

//    public static void onLowMemory() {
//        if (appLifeList.isEmpty()) {
//            return;
//        }
//        for (IAppLife callbacks : appLifeList) {
//            callbacks.onLowMemory();
//        }
//    }


    /**
     * 优先级比较器，优先级大的排在前面
     */
    private static class AppLifeComparator implements Comparator<IAppLife> {
        @Override
        public int compare(IAppLife o1, IAppLife o2) {
            int p1 = o1.getPriority();
            int p2 = o2.getPriority();
            return p2 - p1;
        }
    }
}
