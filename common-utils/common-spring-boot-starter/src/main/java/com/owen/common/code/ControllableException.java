/*
 * ControllableException.java
 *
 * Created on 2002年10月29日, 上午10:05
 */

package com.owen.common.code;

/**
 * Title: 可控异常 <br>
 * Description: 区分其它的不可控的异常 <br>
 * Copyright: Copyright (c) 2002 <br>
 * Company: sand <br>
 * 
 * @author xie.ke
 * @version 1.0
 */

public class ControllableException extends RuntimeException {

	// 类型可能是信息，也可能是警告
	public static final String INFO = "INFO";//提示

	public static final String ALARM = "ALARM";//警告
	
	public static final String ERROR = "ERROR";//警告
	
	public static final String OPENERROR = "OPENERROR";//警告
	
	public static final String LOGIN = "LOGIN";//警告
	
	public static final String EXPIRED = "EXPIRED"; //超时
	
	public static final String NOPERMISSION = "NOPERMISSION"; //没有权限
	
	public static final String SUCCESS = "SUCCESS"; //没有权限

	protected String type = INFO;
	protected String code="0";
	protected String codeStr="respCode"; //code返回字符串
	public String getCodeStr() {
		return codeStr;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}

	protected String msgStr="respMsg"; //code返回字符串
	public String getMsgStr() {
		return msgStr;
	}

	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;

	}

	protected String url="";
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Creates a new instance of <code>NoSuchTableException</code> without
	 * detail message.
	 */
	public ControllableException() {
	}

	/**
	 * Constructs an instance of <code>NoSuchTableException</code> with the
	 * specified detail message.
	 * 
	 * @param msg
	 *            the detail message.
	 */
	public ControllableException(String msg) {
		super(msg);
	//	this.type=ALARM; //默认是警告
	}

	public ControllableException(String msg, String url) {
		super(msg);
		this.url = url;
	}

	public String getType() {
		return type;
	}
}
