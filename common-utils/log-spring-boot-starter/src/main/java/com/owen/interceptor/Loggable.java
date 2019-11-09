package com.owen.interceptor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
/**
 *  @author zcl
 *  @date 2019/8/12
 */
public @interface Loggable {

    /**
     * 日志级别
     * @return
     */
    String value() default "INFO";
}
