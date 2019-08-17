package com.open.capacity.common.auth.details;

import java.io.Serializable;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;


/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
* 客户端应用信息
 */
public class DefaultClientDetails extends BaseClientDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4996423520248249518L;
	//限流标识
	private long ifLimit ;
	//限流次数
	private long limitCount ;

}
