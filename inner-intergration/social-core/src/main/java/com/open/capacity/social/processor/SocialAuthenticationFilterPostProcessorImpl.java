package com.open.capacity.social.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SocialAuthenticationFilterPostProcessorImpl implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler socialAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler socialAuthenticationFailureHandler;

    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setSignupUrl(null);
        socialAuthenticationFilter.setAuthenticationSuccessHandler(socialAuthenticationSuccessHandler);
        socialAuthenticationFilter.setAuthenticationFailureHandler(socialAuthenticationFailureHandler);

    }
}