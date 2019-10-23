package com.open.capacity.user.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "SentinelTest")
public class SysSentinelTest {

	/**
	 * 流控规则
	 * @return
	 */
	@GetMapping("/test/sentinelTest")
	public String test(){
		try(Entry entry = SphU.entry("user-center")){
			
			log.info("ok");
		} catch (BlockException e) {
			return "ko" ;
		}
		
		return "ok";
		
	}
	/**
	 * 降级规则
	 * @return
	 */
	@GetMapping("/test/sentinelResource")
	@SentinelResource(value ="user-center" ,blockHandler="doErrorTest1")
	public String test1(){
			
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("ok");
		 
		return "ok";
		
	}
	public String doErrorTest1(BlockException e){
		 
		return "ko";
		
	}
	
	
	/**
	 * 热点参数限流
	 */
	@GetMapping("/test/hotParamFlow")
	@ResponseBody
	public 	String hotParamFlow(@RequestParam("prodId") Long prodId,@RequestParam("ip") Long ip) {
	    Entry entry = null;
	    String retVal;
	    try{
	        // 只对参数prodId进行限流，参数ip不进行限制
	    	
	    	if(1L ==prodId){
	    		entry = SphU.entry("hotParam", EntryType.IN,1,prodId);
	    	}else{
	    		// 不传入任何参数
	    		entry = SphU.entry("hotParam", EntryType.IN,1);
	    	}
	        
	        retVal = "passed";
	    }catch(BlockException e){
	        retVal = "blocked";
	    }finally {
	        if(entry!=null){
	            entry.exit();
	        }
	    }
	    return retVal;
	}
}
