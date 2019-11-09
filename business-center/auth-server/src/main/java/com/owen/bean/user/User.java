package com.owen.bean.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="")
public class User{
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "应用ID")
    private String clientId;
    @ApiModelProperty(value = "应用密钥")
    private String clientSecret;
    @ApiModelProperty(value = "验证码")
    private String validCode;
    @ApiModelProperty(value = "验证ID")
    private String validCodeId;


}
