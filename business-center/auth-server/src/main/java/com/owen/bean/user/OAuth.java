package com.owen.bean.user;

import com.owen.bean.RepBaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Map;

/**
 * @ProjectName: cabin-platform
 * @Author: WuBean
 * @Description: 登录响应授权类
 * @Date: 2019/10/26 22:04
 */
@Data
@ApiModel(description="")
public class OAuth extends RepBaseBean {
    @ApiModelProperty(value = "授权信息类")
    OAuth2AccessToken oAuth2AccessToken;
    Map<String,String>map;
}
