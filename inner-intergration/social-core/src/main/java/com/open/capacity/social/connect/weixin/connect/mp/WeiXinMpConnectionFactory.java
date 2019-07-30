package com.open.capacity.social.connect.weixin.connect.mp;


import com.open.capacity.social.connect.weixin.api.WeixinMp;
import com.open.capacity.social.connect.weixin.connect.WeiXinAccessGrant;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class WeiXinMpConnectionFactory extends OAuth2ConnectionFactory<WeixinMp> {

    public WeiXinMpConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeiXinMpServiceProvider(appId, appSecret), new WeiXinMpAdapter());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeiXinAccessGrant) {
            return ((WeiXinAccessGrant) accessGrant).getOpenId();
        }
        return super.extractProviderUserId(accessGrant);
    }

    @Override
    public Connection<WeixinMp> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeixinMp> createConnection(ConnectionData data) {
        return new OAuth2Connection<>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private OAuth2ServiceProvider<WeixinMp> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeixinMp>) getServiceProvider();
    }

    private ApiAdapter<WeixinMp> getApiAdapter(String providerUserId) {
        return new WeiXinMpAdapter(providerUserId);
    }

}
