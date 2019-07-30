package com.open.capacity.social.connect.qq.config;

import com.open.capacity.social.connect.qq.connect.QQConnectionFactory;
import com.open.capacity.social.properties.QQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableConfigurationProperties(QQProperties.class)
public class QQAuthConfig extends SocialConfigurerAdapter {

    @Autowired
    private QQProperties qqProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret()));
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }
}
