package com.open.capacity.rabbitmq.config;

import com.open.capacity.rabbitmq.producer.RabbitMQProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Coder编程
 * @version V1.0
 * @Title: RabbitMQAutoConfigure
 * @Package: com.open.capacity.rabbitmq.config
 * @Description: TODO
 * @date 2019/8/25  21:03
 **/

@Configuration
@ConditionalOnClass(RabbitMQProducer.class)
@EnableConfigurationProperties(RabbitMQProperties.class)
public class RabbitMQAutoConfigure {
    @Bean
    public RabbitTemplate rabbitTemplate(){

        return new RabbitTemplate();
    }
    //TODO
}
