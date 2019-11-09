package com.owen.user.dao;

import java.util.List;
import java.util.Map;

import com.owen.common.model.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 * 用户管理
 */
@Mapper
public interface SysUserDao  extends BaseMapper<SysUser> {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into t_user(username, password, nickname, headImgUrl, phone, sex, enabled, type, createTime, updateTime) "
			+ "values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
	int save(SysUser sysUser);

	@Options(useGeneratedKeys = false, keyProperty = "id")
	@Insert("insert into t_user(id, username, password, nickname, phone, sex, enabled, type, createTime, updateTime) "
			+ "values(#{id}, #{username},#{password},  #{nickname}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
	int insertUser(SysUser sysUser);

	int updateByOps(SysUser sysUser);

	@Select("select * from t_user t where t.username = #{username}")
	SysUser findByUsername(String username);

	@Select("select * from t_user t where t.id = #{id}")
	SysUser findById(Long id);

	int count(Map<String, Object> params);

	List<SysUser> findList(Map<String, Object> params);
	
	
	@Select("select u.* from t_user u   where u.username = #{username}")
	SysUser findUserByUsername(String username);

	@Select("select u.* from t_user u   where u.phone = #{mobile}")
	SysUser findUserByMobile(String mobile);
	

}
