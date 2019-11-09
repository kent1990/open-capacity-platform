package com.owen.common.code;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by xieke on 2017/5/26.
 */
public interface Code {
    public String getMsg() ;
    public String getJson();
    public String getJson(JSONObject obj);
    public String getCode();
    public  Code[] codeValues();

}
