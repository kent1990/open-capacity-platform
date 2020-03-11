package com.open.capacity.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FescarUserApplication {

    public static void main(String[] args) {
//		固定端口启动
        SpringApplication.run(FescarUserApplication.class, args);
    }
}
