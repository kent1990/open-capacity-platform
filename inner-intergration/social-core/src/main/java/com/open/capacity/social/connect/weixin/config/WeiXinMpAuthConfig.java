package com.open.capacity.social.connect.weixin.config;

import com.open.capacity.social.connect.weixin.connect.mp.WeiXinMpConnectionFactory;
import com.open.capacity.social.properties.WeixinMpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(WeixinMpProperties.class)
public class WeiXinMpAuthConfig extends SocialConfigurerAdapter {

    @Autowired
    private WeixinMpProperties weixinMpProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        WeiXinMpConnectionFactory weiXinMpConnectionFactory = new WeiXinMpConnectionFactory(weixinMpProperties.getProviderId(), weixinMpProperties.getAppId(), weixinMpProperties.getAppSecret());
        connectionFactoryConfigurer.addConnectionFactory(weiXinMpConnectionFactory);
    }
}
