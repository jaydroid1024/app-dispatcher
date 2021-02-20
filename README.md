# app-dispatcher
app-dispatcher 是一款 Android 应用生命周期分发框架，基于组件化的设计思路，使用简单。



## How to

To get a Git project into your build:

### 倔强青铜版本：app-dispatcher-bronze 

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```



Step 2. Add the dependency

```groovy
dependencies {
   implementation 'com.github.jaydroid1024.app-dispatcher:dispatcher-bronze:v1.0.5'
}
```



### 永恒钻石版本：app-dispatcher-diamond

