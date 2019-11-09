package com.owen.service.impl;

import com.owen.common.auth.details.LoginAppUser;
import com.owen.common.util.StringUtils;
import com.owen.annotation.LogAnnotation;
import com.owen.feign.UserFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @LogAnnotation(module="auth-server",recordRequestParam=false)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginAppUser loginAppUser = null;

        if (StringUtils.isPhone(username)){
            loginAppUser = userFeignClient.findByMobile(username);
        }else {
            //      后续考虑集成spring socail,支持多种类型登录
            loginAppUser = userFeignClient.findByUsername(username);   			  //方式1  feign调用       对外feign resttemplate
//        loginAppUser = userLoginGrpc.findByUsername(username);		  //方式2  gprc调用		对内grpc dubbo
        }

        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }
        //密码以OA校验为准，兼容Spring中校验规则，采取此策略
        return loginAppUser;
    }


     
}
