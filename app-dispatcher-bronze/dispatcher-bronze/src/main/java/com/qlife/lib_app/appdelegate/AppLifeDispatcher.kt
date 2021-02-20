package com.qlife.lib_app.appdelegate

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import java.util.*

/**
 * Application 生命周期分发代理类
 *
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-15 10:57
 */
class AppLifeDispatcher(var context: Context) : IAppLife {

    private var appLifeList: ArrayList<IAppLife>? = null
    private val processName = getProcessName(context, android.os.Process.myPid())
    private val packageName = getAppPackageName(context)

    init {
        Log.e(IAppLife.TAG, "============= 进程: $processName ============= ")
        Log.e(IAppLife.TAG, "============= 包名: $packageName ============= ")
        //初始化Manifest文件解析器，用于解析组件在自己的 Manifest 文件配置的 App 代理类
        appLifeList = ManifestParser(context).parseAppFromManifest()
        Log.d(IAppLife.TAG, " 优先级划分：LOW：[1,2,3,4]  MEDIUM：[5,6,7,8]  HIGH：[9,10,11,12]")
        Collections.sort(appLifeList, AppLifeComparator())
        Log.d(IAppLife.TAG, " 按照优先级分组完成: " + appLifeList.toString())
    }


    fun attachBaseContext(context: Context) {
        attachBaseContext(context, processName)
    }

    override fun attachBaseContext(context: Context, processName: String) {
        Log.d(IAppLife.TAG, "开始分发 attachBaseContext() 方法")
        appLifeList?.forEach { it.attachBaseContext(context, processName) }
    }


    fun onCreate(application: Application) {
        onCreate(application, processName)
    }

    override fun onCreate(application: Application, processName: String) {
        Log.d(IAppLife.TAG, "开始分发 onCreate() 方法")
        appLifeList?.forEach { it.onCreate(application, processName) }
    }

    override fun onTerminate(application: Application) {
        appLifeList?.forEach { it.onTerminate(application) }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        appLifeList?.forEach { it.onConfigurationChanged(newConfig) }
    }

    override fun onLowMemory() {
        appLifeList?.forEach { it.onLowMemory() }
    }


    override fun onTrimMemory(level: Int) {
        appLifeList?.forEach { it.onTrimMemory(level) }
    }

    override fun onPriority(): Int {
        return -1
    }


    /**
     * 获取当前进程名称
     *
     * @return 当前进程名称
     */
    private fun getProcessName(context: Context, pid: Int): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return Application.getProcessName()
        }
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = am.runningAppProcesses ?: return "null"
        for (runningAppProcessInfo in runningApps) {
            if (runningAppProcessInfo.pid == pid) {
                return runningAppProcessInfo.processName
            }
        }
        return "null"
    }

    /**
     * 获取应用程序包名
     *
     * @param context 上下文
     * @return 包名
     */
    private fun getAppPackageName(context: Context): String {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.packageName
        } catch (e: PackageManager.NameNotFoundException) {

            e.printStackTrace()
        }
        return "null"
    }

    /**
     * AppLife 优先级比较器，优先级大的排在前面
     */
    private class AppLifeComparator : Comparator<IAppLife> {
        override fun compare(o1: IAppLife, o2: IAppLife): Int {
            val p1: Int = o1.onPriority()
            val p2: Int = o2.onPriority()
            return p2 - p1
        }
    }
}
