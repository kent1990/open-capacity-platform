package com.open.capacity.social.connect.weixin.connect.mp;


import com.open.capacity.social.connect.weixin.api.WeixinMp;
import com.open.capacity.social.connect.weixin.api.impl.WeiXinMpImpl;
import com.open.capacity.social.connect.weixin.connect.WeiXinOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class WeiXinMpServiceProvider extends AbstractOAuth2ServiceProvider<WeixinMp> {

    private static final String WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
    private static final String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeiXinMpServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template(appId, appSecret, WEIXIN_URL_AUTHORIZE, WEIXIN_URL_ACCESS_TOKEN,true));
    }

    @Override
    public WeixinMp getApi(String accessToken) {
        return new WeiXinMpImpl(accessToken);
    }
}
