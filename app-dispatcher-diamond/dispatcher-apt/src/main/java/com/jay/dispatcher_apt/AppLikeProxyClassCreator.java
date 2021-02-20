package com.jay.dispatcher_apt;

import com.jay.dispatcher_annotation.DispatcherConfig;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */
public class AppLikeProxyClassCreator {

    private Elements mElementUtils;
    private TypeElement mTypeElement;
    private String mProxyClassSimpleName;

    public AppLikeProxyClassCreator(Elements elements, TypeElement typeElement) {
        mElementUtils = elements;
        mTypeElement = typeElement;
        //代理类的名称，用到了之前定义过的前缀、后缀
        mProxyClassSimpleName = DispatcherConfig.PROXY_CLASS_PREFIX +
                mTypeElement.getSimpleName().toString() +
                DispatcherConfig.PROXY_CLASS_SUFFIX;
    }

    /**
     * 获取要生成的代理类的完整类名
     *
     * @return
     */
    public String getProxyClassFullName() {
        return DispatcherConfig.PROXY_CLASS_PACKAGE_NAME + "." + mProxyClassSimpleName;
    }

    /**
     * 生成java代码，其实就是手动拼接，没有什么技术含量，比较繁琐，且容易出错
     * 可以采用第三方框架javapoet来实现，看自己需求了
     */
    public String generateJavaCode() {
        StringBuilder sb = new StringBuilder();
        //设置包名
        sb.append("package ").append(DispatcherConfig.PROXY_CLASS_PACKAGE_NAME).append(";\n\n");

        //设置import部分
        sb.append("import android.content.Context;\n");
        sb.append("import com.jay.dispatcher_api.IAppLife;\n");
        sb.append("import ").append(mTypeElement.getQualifiedName()).append(";\n\n");

        sb.append("public class ").append(mProxyClassSimpleName)
                .append(" implements ").append("IAppLife ").append(" {\n\n");

        //设置变量
        sb.append("  private ").append(mTypeElement.getSimpleName().toString()).append(" mAppLike;\n\n");

        //构造函数
        sb.append("  public ").append(mProxyClassSimpleName).append("() {\n");
        sb.append("     mAppLike = new ").append(mTypeElement.getSimpleName().toString()).append("();\n");
        sb.append("  }\n\n");

        //onCreate()方法
        sb.append("  public void onCreate(Context context) {\n");
        sb.append("     mAppLike.onCreate(context);\n");
        sb.append("  }\n\n");

        //getPriority()方法
        sb.append("  public int getPriority() {\n");
        sb.append("     return mAppLike.getPriority();\n");
        sb.append("  }\n\n");

        //onTerminate方法
        sb.append("  public void onTerminate() {\n");
        sb.append("      mAppLike.onTerminate();\n");
        sb.append("  }\n\n");


        sb.append("\n}");
        return sb.toString();
    }

}
