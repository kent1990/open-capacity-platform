package com.open.capacity.log.dto;

import java.util.Optional;

import org.slf4j.MDC;

import com.github.structlog4j.IToLog;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.constant.TraceConstant;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.util.TokenUtil;

import lombok.Builder;
import lombok.Data;

/**
 * 业务日志
 * @author someday
 * @create 2020年04月02日
 * blog: https://blog.51cto.com/13005375 
 * code: https://gitee.com/owenwangwen/open-capacity-platform
 */
@Data
@Builder
public class LogEntry implements IToLog {
   
    private String transId;
    private String token ;
    private String username ;
    private String msg ;
    private String error ;
    @Override
    public Object[] toLog() {
        return new Object[] {
                "transId",  Optional.ofNullable(MDC.get(TraceConstant.LOG_B3_TRACEID)).orElse(""),
                "token" , Optional.ofNullable(TokenUtil.getToken()).orElse("") ,
                "username", Optional.ofNullable(SysUserUtil.getLoginAppUser()).orElse(new LoginAppUser()).getUsername(),
                "msg" , msg ,
                "error",error
                
        };
    }
}