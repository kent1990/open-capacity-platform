package com.owen.es.dao;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.owen.es.entity.ServiceLogDocument;

/**
 * @author zlt
 */
@Repository
public interface ServiceLogDao extends ElasticsearchRepository<ServiceLogDocument, String> {

}