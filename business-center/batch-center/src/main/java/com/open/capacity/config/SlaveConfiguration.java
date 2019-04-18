package com.open.capacity.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.open.capacity.entity.DeliverPost;
import com.open.capacity.item.DBWriterItem;
import com.open.capacity.item.DeliverPostProcessorItem;

/**
 * @create 2019年4月2日
 * Content :Slave从节点，真实业务处理
 */
@Configuration
public class SlaveConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobExplorer jobExplorer;

    @Autowired
    ApplicationContext applicationContext;
    
    @Autowired
	private DBWriterItem dbItemWriter;





    @Bean("slaveStep")
    public Step slaveStep(DeliverPostProcessorItem processorItem,
    		JdbcCursorItemReader reader) {
        CompositeItemProcessor itemProcessor = new CompositeItemProcessor();
        List<ItemProcessor> processorList = new ArrayList<>();
        processorList.add(processorItem);
        itemProcessor.setDelegates(processorList);
        return stepBuilderFactory.get("slaveStep")
                .<DeliverPost, DeliverPost>chunk(1000)//事务提交批次
                .reader(reader)
                .processor(itemProcessor)
                .writer(dbItemWriter)
                .build();
    }


}
