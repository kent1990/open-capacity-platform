package com.owen.rabbitmq.producer;

import com.owen.rabbitmq.common.DetailResponse;

public interface MessageProducer {


    DetailResponse send(Object message);

}