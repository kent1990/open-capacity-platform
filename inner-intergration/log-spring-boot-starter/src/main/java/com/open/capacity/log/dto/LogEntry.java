package com.open.capacity.log.dto;

import com.github.structlog4j.IToLog;

import lombok.Builder;
import lombok.Data;

/**
 * 审计日志
 * @author someday
 * @create 2020年04月02日
 * blog: https://blog.51cto.com/13005375 
 * code: https://gitee.com/owenwangwen/open-capacity-platform
 */
@Data
@Builder
public class LogEntry implements IToLog {
   
    private String webApp;
    private String username;
    private String token;
    private String serviceName ;
    private String serviceUri ;
    private String requestStr;
    private String responseStr;

    @Override
    public Object[] toLog() {
        return new Object[] {
                "auditlog", "true",
                "webApp", webApp,
                "username", username,
                "token", token,
                "serviceName", serviceName,
                "serviceUri", serviceUri,
                "token", token,
                "requestStr", requestStr,
                "responseStr", responseStr 
        };
    }
}