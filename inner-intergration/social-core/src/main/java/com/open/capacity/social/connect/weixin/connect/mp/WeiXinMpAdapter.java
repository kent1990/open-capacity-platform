package com.open.capacity.social.connect.weixin.connect.mp;

import com.open.capacity.social.connect.weixin.api.WeixinMp;
import com.open.capacity.social.connect.weixin.model.WeiXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeiXinMpAdapter implements ApiAdapter<WeixinMp> {

    private String openId;

    public WeiXinMpAdapter() {
    }

    public WeiXinMpAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeixinMp api) {
        return false;
    }

    @Override
    public void setConnectionValues(WeixinMp api, ConnectionValues values) {
        WeiXinUserInfo userInfo = api.getUserInfo(openId);
        values.setProviderUserId(userInfo.getOpenid());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeixinMp api) {
        System.out.println(api);
        return null;
    }

    @Override
    public void updateStatus(WeixinMp api, String message) {
        System.out.println(api);
        System.out.println(message);
    }
}
