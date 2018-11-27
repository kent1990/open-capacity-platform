package com.open.capacity.eureka.client.config;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.open.capacity.eureka.client.filter.IpCilentFilter;
import com.open.capacity.eureka.client.filter.RequestAuthFilter;

@Configuration
public class EurekaClintConfig {
	@Bean
	public DiscoveryClientOptionalArgs discoveryClientOptionalArgs() {
	    DiscoveryClientOptionalArgs discoveryClientOptionalArgs = new DiscoveryClientOptionalArgs();
	    discoveryClientOptionalArgs.setAdditionalFilters(Collections.singletonList(new IpCilentFilter()));
	    return discoveryClientOptionalArgs;
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
	    FilterRegistrationBean registration = new FilterRegistrationBean(new RequestAuthFilter());
	    registration.addUrlPatterns("/*");
	    return registration;
	}
	 
}
