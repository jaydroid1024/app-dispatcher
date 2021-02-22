package com.jay.app_dispatcher

import android.app.Application
import android.util.Log
import com.jay.dispatcher_bronze.AppPriority
import com.jay.dispatcher_bronze.IAppLife

/**
 * LibNetApp,反射调用
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class LibNetApp : IAppLife {

    override fun onCreate(application: Application, processName: String) {
        Log.d(TAG, "LibNetApp,onCreate")
        init(application)
    }

    private fun init(application: Application) {
        app = application
        instance = this
    }


    /**
     * 设置该appLife的优先级，必须设置，否则不会回调
     */
    override fun onPriority(): Int {
        return AppPriority.HIGH_DEFAULT
    }

    companion object {
        private const val TAG = "AppDispatcher"
        private lateinit var instance: LibNetApp
        private lateinit var app: Application

        fun getInstance(): LibNetApp {
            return instance
        }

        fun getApp(): Application {
            return app
        }
    }
}
