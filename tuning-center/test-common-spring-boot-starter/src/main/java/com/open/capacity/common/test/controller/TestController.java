package com.open.capacity.common.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.capacity.common.annotation.AccessLimit;
import com.open.capacity.common.util.SysUserUtil;

@RestController
public class TestController {

	 
	@AccessLimit(seconds=1,maxCount=2,needLogin=false)
	@GetMapping("/test")
	public String opt(){
		
		return "ok" ;
	}
	
	 
}
