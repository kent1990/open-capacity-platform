package com.open.capacity.client.filter;

import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.open.capacity.common.constant.TraceConstant;
import com.open.capacity.log.util.LogUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * * 程序名 : AccessFilter 建立日期: 2018-09-09 作者 : someday 模块 : 网关 描述 : oauth校验 备注 :
 * version20180909001
 * <p>
 * 修改历史 序号 日期 修改人 修改原因
 */
@Slf4j
@Component
public class RequestFilter implements GlobalFilter, Ordered {


	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -500;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		 
		String traceId = MDC.get(TraceConstant.LOG_B3_TRACEID);
		MDC.put(TraceConstant.LOG_TRACE_ID, traceId);
		exchange.getRequest().mutate().header(TraceConstant.HTTP_HEADER_TRACE_ID, traceId ).build();
		
		log.info("request url = " + exchange.getRequest().getPath().value() + ", traceId = " + traceId);
		
        return chain.filter(exchange);

		
	}

	 

}
