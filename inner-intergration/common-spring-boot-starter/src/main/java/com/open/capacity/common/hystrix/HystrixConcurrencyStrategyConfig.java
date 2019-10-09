package com.open.capacity.common.hystrix;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConcurrencyStrategyConfig {
 
	@Bean
	@ConditionalOnProperty(name = "feign.hystrix.enabled", havingValue="true")
	public RequestAttributeHystrixConcurrencyStrategy requestAttributeHystrixConcurrencyStrategy() {
		return new RequestAttributeHystrixConcurrencyStrategy();
	}
	
}
