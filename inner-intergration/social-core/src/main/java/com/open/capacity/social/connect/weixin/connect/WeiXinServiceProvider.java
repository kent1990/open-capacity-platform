package com.open.capacity.social.connect.weixin.connect;


import com.open.capacity.social.connect.weixin.api.WeiXin;
import com.open.capacity.social.connect.weixin.api.impl.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    private static final String WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    private static final String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeiXinServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template(appId, appSecret, WEIXIN_URL_AUTHORIZE, WEIXIN_URL_ACCESS_TOKEN,false));
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}
