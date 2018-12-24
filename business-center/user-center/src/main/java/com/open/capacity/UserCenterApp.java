/**
 * 
 */
package com.open.capacity;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2018年4月5日 下午19:52:21
* 类说明 
*/
 
@Configuration
@EnableLogging
@EnableDiscoveryClient
@SpringBootApplication
public class UserCenterApp {
	
	public static void main(String[] args) {
//		固定端口启动
//		SpringApplication.run(UserCenterApp.class, args);
		
		//随机端口启动
		SpringApplication app = new SpringApplication(UserCenterApp.class);
        app.addListeners(new PortApplicationEnvironmentPreparedEventListener());
        app.run(args);
		
	}
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
}
