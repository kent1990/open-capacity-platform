package com.open.capacity.social.connect.weixin.config;

import com.open.capacity.social.connect.weixin.connect.WeiXinConnectionFactory;
import com.open.capacity.social.properties.WeixinProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(WeixinProperties.class)
public class WeiXinAuthConfig extends SocialConfigurerAdapter {

    @Autowired
    private WeixinProperties weixinProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        WeiXinConnectionFactory weiXinConnectionFactory = new WeiXinConnectionFactory(weixinProperties.getProviderId(), weixinProperties.getAppId(), weixinProperties.getAppSecret());
        connectionFactoryConfigurer.addConnectionFactory(weiXinConnectionFactory);
    }
}
