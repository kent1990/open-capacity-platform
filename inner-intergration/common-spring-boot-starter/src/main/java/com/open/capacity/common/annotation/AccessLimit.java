package com.open.capacity.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
* @author ���� owen 
* @version ����ʱ�䣺2017��11��12�� ����22:57:51
* ������Ӧ������
* blog: https://blog.51cto.com/13005375 
* code: https://gitee.com/owenwangwen/open-capacity-platform
*/
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
	int seconds();
	int maxCount();
	boolean needLogin() default true;
}
