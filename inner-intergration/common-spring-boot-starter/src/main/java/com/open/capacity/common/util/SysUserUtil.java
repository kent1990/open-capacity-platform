package com.open.capacity.common.util;

import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import com.open.capacity.common.token.SmsCodeAuthenticationToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.constant.UaaConstant;
import com.open.capacity.common.model.SysRole;

import cn.hutool.core.bean.BeanUtil;

/**
 * @author 作者 owen
 * @version 创建时间：2017年11月12日 上午22:57:51 获取用户信息
 */
@SuppressWarnings("all")
public class SysUserUtil {

    /**
     * 获取登陆的 LoginAppUser
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LoginAppUser getLoginAppUser() {

        // 当OAuth2AuthenticationProcessingFilter设置当前登录时，直接返回
        // 强认证时处理
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();

            Object principal = null;
            if (authentication instanceof UsernamePasswordAuthenticationToken ){
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                principal = authenticationToken.getPrincipal();
            }else if (authentication instanceof SmsCodeAuthenticationToken ){
                //短信sms方式
                SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
                principal =  authenticationToken.getPrincipal();
            }else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
                // 刷新token方式
                PreAuthenticatedAuthenticationToken authenticationToken = (PreAuthenticatedAuthenticationToken) authentication;
                principal = authenticationToken.getPrincipal();
            }

            if (principal instanceof LoginAppUser) {
                    return (LoginAppUser) principal;
            } else if (principal instanceof Map) {

                LoginAppUser loginAppUser = BeanUtil.mapToBean((Map) principal, LoginAppUser.class, true);
                Set<SysRole> roles = new HashSet<>();
                if (CollectionUtil.isNotEmpty(loginAppUser.getSysRoles())) {
                    for(Iterator<SysRole> it = loginAppUser.getSysRoles().iterator(); it.hasNext();){
                        SysRole role =  BeanUtil.mapToBean((Map) it.next() , SysRole.class, false);
                        roles.add(role) ;
                    }
                }
                loginAppUser.setSysRoles(roles);
                return loginAppUser;
            }
        }

        // 弱认证处理，当内部服务，不带token时，内部服务
        String accessToken = TokenUtil.getToken();
        if (accessToken != null) {
            RedisTemplate redisTemplate = SpringUtils.getBean(RedisTemplate.class);
            LoginAppUser loginAppUser = (LoginAppUser) redisTemplate.opsForValue().get(UaaConstant.TOKEN + ":" + accessToken);
            if (loginAppUser != null) {
                return loginAppUser;
            }
        }
        return null;
    }
}
