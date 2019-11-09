package com.owen.rabbitmq.producer;



import com.owen.rabbitmq.common.DetailResponse;


public interface MessageProcess<T> {
    DetailResponse process(T message);
}
