package com.jay.dispatcher_annotation;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */
public class DispatcherConfig {

    /**
     * 要生成的代理类的包名，该包名下不要有其他不相关的业务类
     */
    public static final String PROXY_CLASS_PACKAGE_NAME = "com.hm.iou.lifecycle.apt.proxy";

    /**
     * 生成代理类统一的后缀
     */
    public static final String PROXY_CLASS_SUFFIX = "$$Proxy";

    /**
     * 生成代理类统一的前缀
     */
    public static final String PROXY_CLASS_PREFIX = "Dispatcher$$";

}
