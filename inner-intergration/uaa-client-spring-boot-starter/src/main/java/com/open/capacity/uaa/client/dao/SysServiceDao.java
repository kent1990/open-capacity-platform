package com.open.capacity.uaa.client.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 作者 owen 
 * @version 创建时间：2018年4月5日 下午19:52:21 类说明
 * 查询应用绑定的资源权限
 * blog: https://blog.51cto.com/13005375 
 * code: https://gitee.com/owenwangwen/open-capacity-platform
 */
@Mapper
@SuppressWarnings("all")
public interface SysServiceDao {

 

	@Select("select p.* from sys_service p inner join sys_client_service rp on p.id = rp.serviceId where rp.clientId = #{clientId} order by p.sort")
	List<Map> listByClientId(Long clientId);
 
 
}
