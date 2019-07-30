package com.open.capacity.social.connect.weixin.connect;

import com.open.capacity.social.connect.weixin.model.WeiXinUserInfo;
import com.open.capacity.social.connect.weixin.api.WeiXin;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeiXinAdapter implements ApiAdapter<WeiXin> {

    private String openId;

    public WeiXinAdapter() {
    }

    public WeiXinAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeiXin api) {
        return false;
    }

    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo userInfo = api.getUserInfo(openId);
        values.setProviderUserId(userInfo.getOpenid());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        System.out.println(api);
        return null;
    }

    @Override
    public void updateStatus(WeiXin api, String message) {
        System.out.println(api);
        System.out.println(message);
    }
}
