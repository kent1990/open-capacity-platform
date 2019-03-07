package com.open.capacity.server.oauth2.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.grpc.StatusRuntimeException;


/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 异常通用处理
*/
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * IllegalArgumentException异常处理返回json
	 * 状态码:400
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> badRequestException(IllegalArgumentException exception) {
		Map<String, Object> data = new HashMap<>();
		data.put("resp_code", HttpStatus.BAD_REQUEST.value());
		data.put("resp_msg", exception.getMessage());

		return data;
	}
	/**
	 * AccessDeniedException异常处理返回json
	 * 状态码:403
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ AccessDeniedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> badMethodExpressException(AccessDeniedException exception) {
		Map<String, Object> data = new HashMap<>();
		data.put("resp_code", HttpStatus.FORBIDDEN.value());
		data.put("resp_msg", exception.getMessage());

		return data;
	}
	@ExceptionHandler({ StatusRuntimeException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> badRequestException(StatusRuntimeException exception) {
		Map<String, Object> data = new HashMap<>();
		data.put("resp_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
		data.put("resp_msg", exception.getMessage());

		return data;
	}
	 /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public  Map<String, Object> handleException(Exception e) {
    	Map<String, Object> data = new HashMap<>();
		data.put("resp_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
		data.put("resp_msg",  "未知异常");
		return data;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public  Map<String, Object> handleException(RuntimeException e) {
    	Map<String, Object> data = new HashMap<>();
		data.put("resp_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
		data.put("resp_msg",  "运行时异常");
		return data;
    }
    
}
