package com.open.capacity.social.config;
import com.open.capacity.social.processor.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;;
import javax.sql.DataSource;

@EnableSocial
@Configuration
@ComponentScan("com.open.capacity.sociall")
public class SpringSocialAutoConfiguration extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	private String filterProcessesUrl = "/auth";

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("sys_");
		if(connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}

	@Bean("springSocialConfiguration")
	public SpringSocialConfigurer springSocialConfigurer() {
		SpringSocialConfiguration configurer = new SpringSocialConfiguration(filterProcessesUrl);
		configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		return configurer;
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator)) {
		};
	}

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Bean
    public ConnectSupport connectSupport(){
		ConnectSupport connectSupport = new ConnectSupport();
		return connectSupport;
	}

}