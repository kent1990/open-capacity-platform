package com.open.capacity.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Coder编程
 * @version V1.0
 * @Title: RabbitMQProperties
 * @Package: com.open.capacity.rabbitmq.config
 * @Description: 是否开启
 * @date 2019/8/25  21:02
 **/

@ConfigurationProperties("ocp.fast.rabbitmq")
public class RabbitMQProperties {

    private boolean enable;
}
