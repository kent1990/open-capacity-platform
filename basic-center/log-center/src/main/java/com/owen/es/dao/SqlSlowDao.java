package com.owen.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.owen.es.entity.SqlSlowDocument;

/**
 * ELK收集mysql慢查询日志数据
 */
@Component
public interface SqlSlowDao extends ElasticsearchRepository<SqlSlowDocument, String> {

}