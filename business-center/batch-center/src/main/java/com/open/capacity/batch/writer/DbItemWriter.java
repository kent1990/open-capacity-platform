package com.open.capacity.batch.writer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.open.capacity.batch.dao.TCardDao;

@Component
@StepScope
public class DbItemWriter implements ItemWriter<Map<String, String>> {

	@Resource
	private TCardDao tCardDao ;
	
	
	@Override
	public void write(List<? extends Map<String, String>> items) throws Exception {
		 
//		items.forEach(n -> {
//			// 如果等级一致
//			n.forEach((k,v)->{
//				try {
//					System.out.print( new String(v.getBytes("UTF-8"),"UTF-8")+"\t");
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			});
//			System.out.println();
//		});
		
		tCardDao.batchInsert(items);
//		System.out.println(items);
		
		
	}
}
