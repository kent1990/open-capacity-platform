package com.owen.bean;

import com.owen.common.code.OpenCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: cabin-platform
 * @Author: WuBean
 * @Description: Java基础父类类
 * @Date: 2019/10/26 21:46
 */
@Data
@ApiModel(description="")
public class RepBaseBean implements Serializable {
    @ApiModelProperty(value = "响应码(0000成功或其它)")
    private String respcode;
    @ApiModelProperty(value = "响应消息(成功或其它)")
    private String respmsg;
}
