package com.open.capacity.social.connect.weixin.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.social.exception.OcpSocialException;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class WeiXinOAuth2Template extends OAuth2Template {

    protected String appId;

    protected String appSecret;

    protected String authorizeUrl;

    protected String accessTokenUrl;

    protected Boolean weixinMp = false;

    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public WeiXinOAuth2Template(String appId, String appSecret, String authorizeUrl, String accessTokenUrl, Boolean weixinMp) {
        super(appId, appSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.appId = appId;
        this.appSecret = appSecret;
        this.authorizeUrl = authorizeUrl;
        this.accessTokenUrl = accessTokenUrl;
        this.weixinMp = weixinMp;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        StringBuilder accessTokenRequestUrl = new StringBuilder(accessTokenUrl);
        accessTokenRequestUrl.append("?appid=" + appId);
        accessTokenRequestUrl.append("&secret=" + appSecret);
        accessTokenRequestUrl.append("&code=" + authorizationCode);
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        return getAccessToken(accessTokenRequestUrl);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);
        refreshTokenUrl.append("?appid=" + appId);
        refreshTokenUrl.append("&grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token=" + refreshToken);
        return getAccessToken(refreshTokenUrl);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl){
        System.out.println(accessTokenRequestUrl.toString());
        String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);
        try {
            Map result = new ObjectMapper().readValue(response, Map.class);
            if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
                String errcode = MapUtils.getString(result, "errcode");
                String errmsg = MapUtils.getString(result, "errmsg");
                throw new OcpSocialException("获取access token失败, errcode:" + errcode + ", errmsg:" + errmsg);
            }
            WeiXinAccessGrant accessToken = new WeiXinAccessGrant(
                    MapUtils.getString(result, "access_token"),
                    MapUtils.getString(result, "scope"),
                    MapUtils.getString(result, "refresh_token"),
                    MapUtils.getLong(result, "expires_in"),
                    MapUtils.getString(result, "openid"));
            return accessToken;
        } catch (IOException e) {
            throw new OcpSocialException(e.getMessage());
        }
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        System.out.println(parameters);
        return buildAuthenticateUrl(parameters);
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        if(!weixinMp) {
            url = url + "&appid=" + appId + "&scope=snsapi_login";
        }else{
            url = url + "&appid=" + appId + "&scope=snsapi_userinfo";
        }
        return url;
    }
}
