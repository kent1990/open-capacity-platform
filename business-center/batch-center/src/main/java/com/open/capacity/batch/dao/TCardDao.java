package com.open.capacity.batch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TCardDao {
	public void batchInsert(List<? extends Map<String, String>> items) ;
	
	@Insert(" insert  /*+ append */ into BI_TCARD_INFO_IMP_BAK select * from BI_TCARD_INFO_IMP_T ")
	public void insert4Bak( ) ;
	
	@Update("truncate table BI_TCARD_INFO_IMP_T")
	void delete();
	
	void callProcedure(Map<String, Object> map);
}
