package com.jay.dispatcher_api;

import android.content.Context;

/**
 * 组件只需实现该接口
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2/18/21
 */
public interface IAppLife {

    
    int MAX_PRIORITY = 10;
    int MIN_PRIORITY = 1;
    int NORM_PRIORITY = 5;

    int getPriority();

    void onCreate(Context context);

    void onTerminate();


}
