package com.open.capacity.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.open.capacity.client.handler.SentinelExceptionHandler;

@Configuration
@SuppressWarnings("all")
public class GatewayFallbackConfig {
    
    /**
     * 限流出现Block(限制通过)时，调用处理方法，在这里指定返回内容
     * @return 返回对象
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelExceptionHandler();
    }

    

}
 