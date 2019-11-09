package com.owen.common.web;


import com.owen.common.code.OpenCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  @author zcl
 *  @date 2019/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "")
public class AjaxResult<T> implements Serializable {

    /**
     * 返回码
     */
    @ApiModelProperty("返回码")
    private String code;

    /**
     * 返回信息
     */
    @ApiModelProperty("返回信息描述")
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    public static AjaxResult ok(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.SUCCESS.getCode();
        ajaxResult.message = OpenCode.SUCCESS.getMsg();
        return ajaxResult;
    }

    public static AjaxResult ok(String message){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.SUCCESS.getCode();
        ajaxResult.message = message;
        return ajaxResult;
    }

    public static AjaxResult ok(Object o){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.SUCCESS.getCode();
        ajaxResult.message = OpenCode.SUCCESS.getMsg();
        ajaxResult.data = o;
        return ajaxResult;
    }

    public static AjaxResult ok(Object o,String message){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.SUCCESS.getCode();
        ajaxResult.message = message;
        ajaxResult.data = o;
        return ajaxResult;
    }

    public static AjaxResult ok(String message,Object o){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.SUCCESS.getCode();
        ajaxResult.message = message;
        ajaxResult.data = o;
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.ERROR.getCode();
        ajaxResult.message = OpenCode.ERROR.getMsg();
        return ajaxResult;
    }

    public static AjaxResult error(String message){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.ERROR.getCode();
        ajaxResult.message = message;
        return ajaxResult;
    }

    public static AjaxResult error(String message,Object o){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = OpenCode.ERROR.getCode();
        ajaxResult.message = message;
        ajaxResult.data = o;
        return ajaxResult;
    }
}
