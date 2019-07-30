package com.open.capacity.social.connect.weixin.connect;

import lombok.*;
import org.springframework.social.oauth2.AccessGrant;

@Getter
@Setter
public class WeiXinAccessGrant extends AccessGrant {

    private String openId;

    public WeiXinAccessGrant(String accessToken) {
        super(accessToken);
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String openId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openId = openId;
    }
}
