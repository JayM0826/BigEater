package com.bigeater.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:J on 2018/12/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreDb {

    // 待打印的参数序号，从0开始计数
    int paramIndex() default 0;
    boolean mtime() default true;
    boolean ctime() default false;
}
