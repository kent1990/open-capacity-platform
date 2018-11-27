package com.open.capacity.batch;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.SimplePartitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.validation.BindException;

import com.open.capacity.batch.dao.TCardDao;
import com.open.capacity.batch.job.BatchIncrementer;
import com.open.capacity.batch.job.JobListener;
import com.open.capacity.batch.writer.DbItemWriter;

@Configuration
@EnableBatchProcessing
public class TCardJobFlow {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	 
	@Autowired
	private DbItemWriter dbItemWriter;
	
	@Resource
	private TCardDao tCardDao ;
	 
	@Bean
	public Job jobfileDemo() {

		JobListener jobListener = new JobListener() ;
		jobListener.settCardDao(tCardDao);
		return jobBuilderFactory.get("TCardJobFlow1" + LocalDate.now().toString()).start(partitionPrintStep())
				
				.incrementer(new BatchIncrementer()).listener(jobListener).build();

	}

	 
	@Bean
	public Step masterStep() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		FlatFileItemReader<Map<String, String>> reader = new FlatFileItemReader<Map<String, String>>();
		reader.setEncoding("UTF-8");
		reader.setResource(new ClassPathResource("T_CARD_CALL_NET_201808.txt"));
		DelimitedLineTokenizer tokennizer = new DelimitedLineTokenizer("\t");
		tokennizer.setNames(new String[] { "MONTH_PART", "EPARCHY_CODE", "USER_ID",
				"SERVICE_ID",
				"CALL_TYPE",
				"NET_TYPE", "DURATION", "CALL_TIME" });
		
		
		
		DefaultLineMapper<Map<String,String>> mapper = new DefaultLineMapper<>();
		
		mapper.setLineTokenizer(tokennizer);
		
		mapper.setFieldSetMapper(new FieldSetMapper<Map<String,String>>() {
			
			@Override
			public Map<String, String> mapFieldSet(FieldSet fieldSet) throws BindException {
				Map<String, String> map = new  HashMap<>();
				map.put("MONTH_PART", fieldSet.readString("MONTH_PART")) ;
				map.put("EPARCHY_CODE", fieldSet.readString("EPARCHY_CODE")) ;
				map.put("USER_ID", fieldSet.readString("USER_ID")) ;
				map.put("SERVICE_ID", fieldSet.readString("SERVICE_ID")) ;
				map.put("CALL_TYPE", fieldSet.readString("CALL_TYPE")) ;
				map.put("NET_TYPE", fieldSet.readString("NET_TYPE")) ;
				map.put("DURATION", fieldSet.readString("DURATION")) ;
				map.put("CALL_TIME", fieldSet.readString("CALL_TIME")) ;
				return map;
			}
		});
		mapper.afterPropertiesSet();
		reader.setLineMapper(mapper);
		
		return stepBuilderFactory.get("masterStep").<Map<String, String>, Map<String, String>>chunk(1000)
				.reader(reader)
				// .processor(new ItemProcessor<Map<String, String>,Map<String,
				// String>>(){
				//
				// @Override
				// public Map<String, String> process(Map<String, String> item)
				// throws Exception {
				// item.put("NET_TYPE" , new
				// String(item.get("NET_TYPE").getBytes("ISO8859"), "utf-8"));
				// item.put("CALL_TYPE" , new
				// String(item.get("CALL_TYPE").getBytes("ISO8859"), "utf-8"));
				// return item;
				// }
				//
				// })
				.writer(dbItemWriter).build();
	}

	@Bean
    public Step partitionPrintStep() {
        SimplePartitioner partitioner = new SimplePartitioner();
        MultiResourcePartitioner multiResourcePartitioner = new MultiResourcePartitioner();
        //partitioner.se
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(1);
        taskExecutorPartitionHandler.setTaskExecutor(taskExecutor);
        taskExecutorPartitionHandler.setStep(masterStep());
        return stepBuilderFactory.get("partitionPrintStep").partitioner("slaveStep", partitioner).partitionHandler(taskExecutorPartitionHandler).build();
    }
	
	
}
