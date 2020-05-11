package com.open.capacity.log.monitor;

import com.github.structlog4j.ILogger;
import com.github.structlog4j.SLoggerFactory;
/**
 *  业务结构化日志
 */
public class BizLog {
	private static final ILogger logger =  SLoggerFactory.getLogger(BizLog.class);

    public static void info(String message,Object...params ) {
    	logger.info(message, params);
    }

    public static void debug(String message,Object...params ) {
    	logger.debug(message, params);
    }
    
    public static void trace(String message,Object...params ) {
    	logger.trace(message, params);
    }
    

	
}
