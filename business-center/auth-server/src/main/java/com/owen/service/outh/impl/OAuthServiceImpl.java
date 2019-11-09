package com.owen.service.outh.impl;

import com.owen.bean.user.OAuth;
import com.owen.bean.user.User;
import com.owen.common.code.OpenCode;
import com.owen.server.service.RedisClientDetailsService;
import com.owen.server.service.ValidateCodeService;
import com.owen.service.outh.OAuthService;
import com.owen.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OAuthServiceImpl implements OAuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ValidateCodeService validateCodeService;
//    @Override
//    public OAuth2AccessToken getUserTokenInfo(String username, String password, String clientId,String clientSecret, String deviceId) {
//        if (clientId == null || "".equals(clientId)) {
//            throw new UnapprovedClientAuthenticationException("请求头中无client_id信息");
//        }
//
//        if (clientSecret == null || "".equals(clientSecret)) {
//            throw new UnapprovedClientAuthenticationException("请求头中无client_secret信息");
//        }
//
//        RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);
//
//        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
//
//        if (clientDetails == null) {
//            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
//        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
//            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
//        }
//
//        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(),
//                "customer");
//
//        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        AuthenticationManager authenticationManager = SpringUtil.getBean(AuthenticationManager.class);
//
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
//
//        AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
//                .getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);
//
//        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
//                .createAccessToken(oAuth2Authentication);
//
//        oAuth2Authentication.setAuthenticated(true);
//
//        return oAuth2AccessToken;
//    }
    @Override
    public OAuth getUserTokenInfo(User user){
        //TODO 去OA检验账号
        OAuth oAuth=new OAuth();
        try {
            if (!((user.getValidCode()).equals(validateCodeService.getCode(user.getValidCodeId())))){
                oAuth.setRespcode(OpenCode.ERROR.getCode());
                oAuth.setRespmsg("validateCode is error");
                return oAuth;
            }
        }catch (Exception e){
            oAuth.setRespcode(OpenCode.ERROR.getCode());
            oAuth.setRespmsg("validateCode is invalid");
            return oAuth;
        }
        if (StringUtils.isBlank(user.getClientId())) {
            oAuth.setRespcode(OpenCode.ERROR.getCode());
            oAuth.setRespmsg("请求头中无client_id信息");
            return oAuth;
        }
        if (StringUtils.isBlank(user.getClientSecret())) {
            oAuth.setRespcode(OpenCode.ERROR.getCode());
            oAuth.setRespmsg("请求头中无client_secret信息");
            return oAuth;
        }
        RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);
        ClientDetails clientDetails =null;
        try {
            clientDetails = clientDetailsService.loadClientByClientId(user.getClientId());
        }catch (Exception e){
            oAuth.setRespcode(OpenCode.ERROR.getCode());
            oAuth.setRespmsg("clientId对应的信息异常");
            return oAuth;
        }

        if (clientDetails == null) {
            oAuth.setRespcode(OpenCode.ERROR.getCode());
            oAuth.setRespmsg("clientId对应的信息不存在");
            return oAuth;
        } else if (!passwordEncoder.matches(user.getClientSecret(), clientDetails.getClientSecret())) {
            oAuth.setRespcode(OpenCode.ERROR.getCode());
            oAuth.setRespmsg("clientSecret不匹配");
            return oAuth;
        }

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, user.getClientId(), clientDetails.getScope(),
                "password");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        AuthenticationManager authenticationManager = SpringUtil.getBean(AuthenticationManager.class);

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
                .getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);

        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
                .createAccessToken(oAuth2Authentication);
        oAuth2Authentication.setAuthenticated(true);
        oAuth.setOAuth2AccessToken(oAuth2AccessToken);
        oAuth.setRespcode(OpenCode.SUCCESS.getCode());
        oAuth.setRespmsg(OpenCode.SUCCESS.getMsg());
        return oAuth;
    }
}
