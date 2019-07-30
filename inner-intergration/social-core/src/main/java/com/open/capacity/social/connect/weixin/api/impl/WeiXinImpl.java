package com.open.capacity.social.connect.weixin.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.social.connect.weixin.model.WeiXinUserInfo;
import com.open.capacity.social.connect.weixin.api.WeiXin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private static final String WEIXIN_URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WeiXinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeiXinUserInfo getUserInfo(String openId) {
        String url = WEIXIN_URL_GET_USER_INFO + openId;

        String result = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(result, "errcode")) {
            return null;
        }
        WeiXinUserInfo userInfo = null;

        try {
            userInfo = new ObjectMapper().readValue(result, WeiXinUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
