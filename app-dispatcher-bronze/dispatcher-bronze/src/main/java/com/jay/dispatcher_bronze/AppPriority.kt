package com.jay.dispatcher_bronze

/**
 * Application 生命周期分发 优先级
 * 数字越大优先级越高，越早调用
 * @author wangxuejie
 * @version 1.0
 * @date 12/30/20
 */
object AppPriority {

    /**
     * 低优先级
     */
    const val LOW_DEFAULT = 1

    object LOW {
        const val LOW_I = 2
        const val LOW_II = 3
        const val LOW_III = 4
    }

    /**
     * 中优先级
     */
    const val MEDIUM_DEFAULT = 5

    object MEDIUM {
        const val MEDIUM_I = 6
        const val MEDIUM_II = 7
        const val MEDIUM_III = 8
    }

    /**
     * 高优先级
     */
    const val HIGH_DEFAULT = 9

    object HIGH {
        const val HIGH_I = 10
        const val HIGH_II = 11
        const val HIGH_III = 12
    }
}