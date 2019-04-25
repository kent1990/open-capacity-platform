package com.open.capacity;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.open.capacity.support.ThirdServiceProp;

@EnableScheduling
@SpringBootApplication
@EnableBatchProcessing
@EnableConfigurationProperties(ThirdServiceProp.class)
public class BatchCenterApp {
	public static void main(String[] args) {
		// 固定端口
		// SpringApplication.run(FileCenterApp.class, args);

		SpringApplication app = new SpringApplication(BatchCenterApp.class);
		ConfigurableApplicationContext context = app.run(args);
	}
}
 