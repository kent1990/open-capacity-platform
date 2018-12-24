package com.open.capacity.client.oauth2.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class VersionFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		context.addZuulRequestHeader("version",
				"{\"auth-server\":\"1.0\", \"user-center\":\"1.0\", \"eureka-server\":\"1.0;1.2\"}");

		return null;
	}
}