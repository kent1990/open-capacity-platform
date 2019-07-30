package com.open.capacity.social.connect.weixin.api;


import com.open.capacity.social.connect.weixin.model.WeiXinUserInfo;

public interface WeiXin {

    WeiXinUserInfo getUserInfo(String openId);
}
