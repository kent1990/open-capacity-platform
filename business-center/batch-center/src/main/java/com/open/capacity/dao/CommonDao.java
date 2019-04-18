package com.open.capacity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.open.capacity.entity.DeliverPost;

@Mapper
public interface CommonDao {

	@Select("SELECT url_add FROM  oc_http_param_t WHERE connect_id = #{connectId,jdbcType=VARCHAR}")
	public String getHttpUrl(String connectId) ;
	
 
}
