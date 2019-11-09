package com.owen.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.owen.es.entity.NinxLogDocument;

/**
 * @author zlt
 */
@Repository
public interface NginxLogDao extends ElasticsearchRepository<NinxLogDocument, String> {

}