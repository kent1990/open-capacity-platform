package com.owen.rabbitmq.comsumer;


import com.owen.rabbitmq.common.DetailResponse;

/**
 * Created by littlersmall on 16/5/12.
 */
public interface MessageConsumer {
    DetailResponse consume();
}
