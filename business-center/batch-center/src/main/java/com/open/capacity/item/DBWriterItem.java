package com.open.capacity.item;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.open.capacity.dao.DeliverPostDao;
import com.open.capacity.entity.DeliverPost;

/**
 * @create 2019年4月2日
 * Content :数据输出item
 */
@Component
@StepScope
public class DBWriterItem<T> implements ItemWriter<T> {
	
	@Autowired
	private DeliverPostDao deliverPostDao ;
	
    @Override
    public void write(List<? extends T> list) throws Exception {
    	
    	
    	deliverPostDao.batchInsert((List<? extends DeliverPost>) list);

    }
}
