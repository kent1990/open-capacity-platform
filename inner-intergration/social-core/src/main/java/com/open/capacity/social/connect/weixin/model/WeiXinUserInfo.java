package com.open.capacity.social.connect.weixin.model;

import lombok.Data;

@Data
public class WeiXinUserInfo {

    private String openid;
    private String nickname;
    private String headimgurl;
    private int sex;
    private String country;
    private String province;
    private Object privilege;
    private String city;
    private String language;
    private String unionid;
}
