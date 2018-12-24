/**
 * 
 */
package com.open.capacity;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.nepxion.discovery.plugin.strategy.service.aop.RestTemplateStrategyInterceptor;
import com.open.capacity.annotation.EnableLogging;
import com.open.capacity.autoconfigure.port.PortApplicationEnvironmentPreparedEventListener;
import com.open.capacity.strategy.MyDiscoveryEnabledStrategy;
import com.open.capacity.strategy.MyDiscoveryListener;
import com.open.capacity.strategy.MyLoadBalanceListener;
import com.open.capacity.strategy.MyRegisterListener;
import com.open.capacity.strategy.MySubscriber;

/** 
* @author owen 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
* 类说明 
*/
@EnableLogging
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OpenAuthServerApp {
	
	 @Bean
	    @LoadBalanced
	    public RestTemplate restTemplate(RestTemplateStrategyInterceptor restTemplateStrategyInterceptor) {
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.setInterceptors(Collections.singletonList(restTemplateStrategyInterceptor));

	        return restTemplate;
	    }

	    @Bean
	    public MyRegisterListener myRegisterListener() {
	        return new MyRegisterListener();
	    }

	    @Bean
	    public MyDiscoveryListener myDiscoveryListener() {
	        return new MyDiscoveryListener();
	    }

	    @Bean
	    public MyLoadBalanceListener myLoadBalanceListener() {
	        return new MyLoadBalanceListener();
	    }

	    @Bean
	    public MySubscriber mySubscriber() {
	        return new MySubscriber();
	    }

	    @Bean
	    public MyDiscoveryEnabledStrategy myDiscoveryEnabledStrategy() {
	        return new MyDiscoveryEnabledStrategy();
	    }
	public static void main(String[] args) {
//		固定端口启动
//		SpringApplication.run(OpenAuthServerApp.class, args);
		
		//随机端口启动
		SpringApplication app = new SpringApplication(OpenAuthServerApp.class);
        app.addListeners(new PortApplicationEnvironmentPreparedEventListener());
        app.run(args);
		
	}

}
