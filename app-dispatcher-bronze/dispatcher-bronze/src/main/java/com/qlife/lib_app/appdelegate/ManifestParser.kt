package com.qlife.lib_app.appdelegate

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import java.util.*

/**
 * 获取metaData信息并解析 IAppLife
 *
 * <meta-data
 * android:name="com.jaydroid.conponent_base.app.BaseApp"
 * android:value="IModuleConfig" />
 */
class ManifestParser(private val context: Context) {

    /**
     *  解析在 manifest 文件中配置的 APP
     *
     * @return
     */
    fun parseAppFromManifest(): ArrayList<IAppLife> {
        val modules = ArrayList<IAppLife>()
        try {
            val appInfo = context.packageManager.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            )
            if (appInfo.metaData != null) {
                //会对其中value为IModuleConfig的meta-data进行解析，并通过反射生成实例
                for (name in appInfo.metaData.keySet()) {
                    val value = appInfo.metaData.get(name)
                    if (MODULE_VALUE == value) {
                        val app = generateApp(name)
                        if (app != null) {
                            modules.add(app)
                        }
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(IAppLife.TAG, "parseAppFromManifest: 解析Application失败" + e.localizedMessage)
            throw RuntimeException("解析Application失败", e)
        }
        return modules
    }

    /**
     * 通过类名生成实例
     * @param className
     * @return
     */
    private fun generateApp(className: String): IAppLife? {
        val clazz: Class<*>
        val appLife: IAppLife?
        try {
            clazz = Class.forName(className)
            appLife = clazz.newInstance() as? IAppLife
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException(e)
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
        return appLife
    }

    companion object {
        private const val MODULE_VALUE = "IModuleConfig"

    }
}