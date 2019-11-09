package com.owen.redis.util;

 
public interface RedisSubscribeCallback {
    void callback(String msg);
}
