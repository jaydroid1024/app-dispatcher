# app-dispatcher
[app-dispatcher](https://github.com/jaydroid1024/app-dispatcher/tree/master) 是一款 Android 应用生命周期分发框架，基于组件化的设计思路，使用简单。



## How to

To get a Git project into your build:

### 倔强青铜版本：app-dispatcher-bronze 

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```



Step 2. Add the dependency to your app build.gradle

```groovy
dependencies {
   implementation 'com.github.jaydroid1024.app-dispatcher:dispatcher-bronze:$appDispatcherVersion'
}
```

Step 3. Add confuse code to yaour root proguard-rules.pro

```groovy
#组件化
# 保留app-dispatcher库
-keep class com.jay.dispatcher_bronze.**{*;}
# 保留实现了IAppLife 的所有 appLife类
-keep class * implements com.jay.dispatcher_bronze.IAppLife
```



### 永恒钻石版本：app-dispatcher-diamond

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```groovy
buildscript {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

...
  
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```



Step 2. Add the `dispatcher-plugin` to your  root build.gradle

```groovy
classpath "com.github.jaydroid1024.app-dispatcher:dispatcher-plugin:$appDispatcherVersion"
```

Apply plugin  in your  app  build.gradle

```groovy
plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

//引入插件applifecycle
apply plugin: 'com.hm.plugin.lifecycle'
```



Step 3. Add the dependency  to your  app  build.gradle

```groovy
annotationProcessor "com.github.jaydroid1024.app-dispatcher:dispatcher-apt:$appDispatcherVersion"
implementation "com.github.jaydroid1024.app-dispatcher:dispatcher-api:$appDispatcherVersion"
implementation "com.github.jaydroid1024.app-dispatcher:dispatcher-annotation:$appDispatcherVersion"
```





## Thanks

- [Android-AppLifecycleMgr](https://github.com/houjinyun/Android-AppLifecycleMgr)

- [AppInit](https://github.com/bingoogolapple/AppInit) 

