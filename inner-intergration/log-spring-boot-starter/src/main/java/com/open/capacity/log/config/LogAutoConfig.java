package com.open.capacity.log.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author owen
 * @create 2017年7月2日
 * 日志拦截器，排除对spring cloud gateway的影响 (WebMvcConfigurer)
 * 
 */
public class LogAutoConfig {
 
	@Bean
	@ConditionalOnClass(WebMvcConfigurer.class)
	public WebMvcConfigurer get() {
		return new LogWebMvcConfig();
	}
}
