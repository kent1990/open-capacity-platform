package com.open.capacity.uaa.client.config;

import org.springframework.context.annotation.Bean;

import feign.Logger.Level;

public class GolbalFeignConfig {

	@Bean
	public Level levl(){
		return Level.FULL;
	}
}
