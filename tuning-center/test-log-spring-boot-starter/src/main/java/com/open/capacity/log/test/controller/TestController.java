package com.open.capacity.log.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.capacity.log.annotation.LogAnnotation;

@RestController
public class TestController {

	 
	
	@GetMapping("/test")
	@LogAnnotation(module="test",recordRequestParam=true)
	public String opt(){
		return "ok" ;
	}
	
	 
}
