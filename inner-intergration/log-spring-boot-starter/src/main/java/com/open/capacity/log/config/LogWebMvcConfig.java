package com.open.capacity.log.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.open.capacity.log.interceptor.LogInterceptor;

/**
 * @author jacke
 * @create 2019年12月6日
 * 日志拦截器，排除对spring cloud gateway的影响 (WebMvcConfigurer)
 * 
 */
public class LogWebMvcConfig implements WebMvcConfigurer {
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
 
        /**
         * 自定义拦截器，添加拦截路径和排除拦截路径
         * addPathPatterns():添加需要拦截的路径
         * excludePathPatterns():添加不需要拦截的路径
         * 在括号中还可以使用集合的形式，如注释部分代码所示
         */
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**") ;
 
 
    }
}
