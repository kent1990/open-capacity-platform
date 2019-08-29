package com.open.capacity.uaa.provider;

import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.uaa.feign.UserFeignClient;
import com.open.capacity.uaa.server.token.SmsCodeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeAuthenticationProvider implements  AuthenticationProvider {

    @Autowired
    private UserFeignClient userClient;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken token = (SmsCodeAuthenticationToken) authentication;

        LoginAppUser loginAppUser = userClient.findByMobile((String) token.getPrincipal());

        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(loginAppUser, loginAppUser.getAuthorities());
        // 需要把未认证中的一些信息copy到已认证的token中
        authenticationResult.setDetails(token);
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

}
