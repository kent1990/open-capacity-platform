package com.open.capacity.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public Authentication currentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/users")
    public Authentication user(Authentication user) {
        return user;
    }

    @RequestMapping("/dashboard/message")
	public Map<String, Object> dashboard() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
		return Collections.<String, Object> singletonMap("message", auth.getPrincipal());
	}

	@RequestMapping("/dashboard/user")
	public Principal user(Principal user) {
		return user;
	}
    
    
}
