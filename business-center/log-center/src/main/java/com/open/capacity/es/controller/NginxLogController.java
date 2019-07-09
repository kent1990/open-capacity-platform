package com.open.capacity.es.controller;

import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.commons.PageResult;
import com.open.capacity.es.dao.NginxLogDao;
import com.open.capacity.es.entity.NinxLogDocument;
/**
 * nginx日志查询
 */
@RestController
public class NginxLogController {
	
	
	private static Logger log = LoggerFactory.getLogger(NginxLogController.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	 
	@Autowired
	public NginxLogDao nginxLogDao;

	 
	@GetMapping(value="/nginxLog")
	public PageResult<NinxLogDocument>    getPage(@RequestParam Map<String, Object> params) throws JsonProcessingException {
		
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		String searchKey = (String)params.get("searchKey");
		String searchValue = (String)params.get("searchValue");
		
		// 分页参数
		Pageable pageable = PageRequest.of(1, 1000 , Sort.Direction.DESC,"@timestamp");
		SearchQuery query = new NativeSearchQueryBuilder().withQuery(builder).withPageable(pageable).build();
		Page<NinxLogDocument> result = nginxLogDao.search(query);
		
		
		
		return PageResult.<NinxLogDocument>builder().data(result.getContent()).code(0).count(1000L).build() ;
	}
}
