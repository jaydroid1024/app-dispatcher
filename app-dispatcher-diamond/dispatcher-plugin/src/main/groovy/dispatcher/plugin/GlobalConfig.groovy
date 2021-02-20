package dispatcher.plugin

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */
class GlobalConfig {

//    /**
//     * 要生成的代理类的包名，该包名下不要有其他不相关的业务类
//     */
//    public static final String PROXY_CLASS_PACKAGE_NAME = "com.hm.iou.lifecycle.apt.proxy";
//
//    /**
//     * 生成代理类统一的后缀
//     */
//    public static final String PROXY_CLASS_SUFFIX = "$$Proxy";
//
//    /**
//     * 生成代理类统一的前缀
//     */
//    public static final String PROXY_CLASS_PREFIX = "Dispatcher$$";
//

    static final PROXY_CLASS_PREFIX = "Dispatcher\$\$"
    static final PROXY_CLASS_SUFFIX = "\$\$Proxy.class"

    static final PROXY_CLASS_PACKAGE_NAME = "com/hm/iou/lifecycle/apt/proxy"

    static final REGISTER_CLASS_FILE_NAME = "com/jay/dispatcher_api/AppDispatcherManager.class"

    static final INJECT_CLASS_NAME = "com/jay/dispatcher_api/AppDispatcherManager"
    static final INJECT_METHOD_NAME = "registerApplicationLifecycleCallbacks"
    static final INJECT_PARAMS_DESC = "(Ljava/lang/String;)V"

}
