package com.open.capacity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.open.capacity.entity.DeliverPost;

@Mapper
public interface DeliverPostDao {
	public void batchInsert(List<? extends DeliverPost> items) ;

	@Update("truncate table oc_deliver_post_detail_t")
	public void delete();
	
 
}
