package com.jay.dispatcher_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jaydroid
 * @version 1.0
 * @date 2/18/21
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface AppDispatcher {

}
