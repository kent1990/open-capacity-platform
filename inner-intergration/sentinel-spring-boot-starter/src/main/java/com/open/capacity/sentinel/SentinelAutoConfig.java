package com.open.capacity.sentinel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.open.capacity.common.web.Result;

import cn.hutool.json.JSONUtil;

/**
 * Sentinel配置类
 */
public class SentinelAutoConfig {
	
	@PostConstruct
    public void init() {
        WebCallbackManager.setUrlBlockHandler(new DefaultUrlBlockHandler());
        //若希望对 HTTP ,黑白名单，请求按照来源限流，则可以自己实现  RequestOriginParser  接口从 HTTP 请求中解析 origin 并注册至 
//        WebCallbackManager.setRequestOriginParser(new RequestOriginParser() {
//            @Override
//            public String parseOrigin(HttpServletRequest request) {
//                return request.getRemoteAddr();
//            }
//        });
    }

    /**
     * 新增流控规则，自定义url处理异常
     * 限流、熔断统一处理类
     */
    public class DefaultUrlBlockHandler implements UrlBlockHandler {
        @Override
        public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        	
        	
        	httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        	httpServletResponse.getWriter().print(JSONUtil.toJsonStr(("{\"code\":403,\"message\":\"sentinel限流了\"}".getBytes(StandardCharsets.UTF_8))));
        }
    }
}
