package com.open.capacity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import brave.Span;
import brave.Tracing;

@SpringBootApplication
@EnableEurekaClient
public class EsClientApp {


	public static void main(String[] args) {
		SpringApplication.run(EsClientApp.class, args);
	}
}
