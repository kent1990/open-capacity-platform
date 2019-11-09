package com.owen.service.outh;

import com.owen.bean.user.OAuth;
import com.owen.bean.user.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuthService {
    //OAuth2AccessToken getUserTokenInfo(String username, String password, String clientId,String clientSecret, String deviceId);
    OAuth getUserTokenInfo(User user);
}
