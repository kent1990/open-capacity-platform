package com.open.capacity.es.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.capacity.es.dao.EsLogDao;
import com.open.capacity.es.entity.LogDocument;

@RestController
public class LogController {
	@Autowired
	public EsLogDao eslogDao;

	@GetMapping(value="/save")
	public LogDocument save() {
		LogDocument logDocument = new LogDocument();
		logDocument.setId(2L);
		logDocument.setUsername("admim");
		logDocument.setModule("oauth:/user/update");
		logDocument.setFlag(true);
		logDocument.setParams("sdf");
		logDocument.setRemark("ok");
		logDocument.setCreateTime(new Date(System.currentTimeMillis())); 
		LogDocument save = eslogDao.save(logDocument);
		return save ;
	}
	@GetMapping(value="/getById")
	public LogDocument search() {
		Optional<LogDocument> byId = eslogDao.findById(1L);
		LogDocument itemDocument = byId.get();
		return itemDocument ;
	}

	@GetMapping(value="/likeByName")
	public List<LogDocument> likeByName() {

		QueryBuilder query = QueryBuilders.matchQuery("module", "'oauth:");
		List<LogDocument> list = new ArrayList<>();
		Iterable<LogDocument> search = eslogDao.search(query);
		Iterator<LogDocument> iterator = search.iterator();
		while (iterator.hasNext()) {
			LogDocument next = iterator.next();
			list.add(next) ;
		}
		return list ;
	}
	@GetMapping(value="/getbyName")
	public List<LogDocument> searchByName() {

		QueryBuilder query = QueryBuilders.matchPhraseQuery("module", "'oauth:/user/login");
		List<LogDocument> list = new ArrayList<>();
		Iterable<LogDocument> search = eslogDao.search(query);
		Iterator<LogDocument> iterator = search.iterator();
		while (iterator.hasNext()) {
			LogDocument next = iterator.next();
			list.add(next) ;
		}
		return list ;
	}
}
