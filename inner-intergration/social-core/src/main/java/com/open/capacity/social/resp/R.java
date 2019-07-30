package com.open.capacity.social.resp;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author westboy
 * @date 2019/4/13 13:58
 * @description: TODO
 */

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 2L;

    @Getter
    @Setter
    @Builder.Default
    private Integer code  = 2000;

    @Getter
    @Setter
    @Builder.Default
    private String message = "ok";

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static R ok(){
       return R.builder().build();
    }


    public static R error(){
        return R.builder().code(3000).message("error").build();
    }

    public static R ok(Object data){
        return R.builder().data(data).build();
    }


    public static R error(Object data){
        return R.builder().data(data).code(3000).message("error").build();
    }

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.message = msg;
    }

    public R(Throwable e) {
        super();
        this.message = e.getMessage();
        this.code = 3000;
    }

    public String toJSONString(){
        return JSON.toJSONString(this);
    }

}
