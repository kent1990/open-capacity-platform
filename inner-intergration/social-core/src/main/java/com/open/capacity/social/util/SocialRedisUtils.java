package com.open.capacity.social.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SocialRedisUtils {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;


    public void saveConnectionData(String key, ConnectionData connectionData) {
        redisUtil.setEx(buildRedisKey(key), JSON.toJSONString(connectionData), 10, TimeUnit.MINUTES);
    }

    public void doPostSignUp(String key,String userId){
        String redisKey = buildAndCheckRedisKey(key);
        ConnectionData connectionData = JSON.parseObject(redisUtil.get(redisKey),ConnectionData.class);
        Connection<?> connection = connectionFactoryLocator
                .getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        usersConnectionRepository
                .createConnectionRepository(userId)
                .addConnection(connection);
        redisUtil.delete(redisKey);
    }

    private String buildRedisKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("设置ID：key 不为空");
        }
        return "ocp_social:connect." + key;
    }

    private String buildAndCheckRedisKey(String key) {
        String redisKey = buildRedisKey(key) ;
        if (!redisUtil.hasKey(redisKey)){
            throw new RuntimeException("无法找到缓存的第三方社交账号信息");
        }
        return redisKey;
    }
}