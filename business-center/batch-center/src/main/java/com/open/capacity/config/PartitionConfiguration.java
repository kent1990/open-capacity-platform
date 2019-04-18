package com.open.capacity.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.open.capacity.dao.DeliverPostRowMapper;
import com.open.capacity.entity.DeliverPost;

/**
 * @create 2019年4月2日
 * Content :数据分区
 */
@Configuration
public class PartitionConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobExplorer jobExplorer;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JobLauncher jobLauncher;
     
    
    @Autowired
    private DataSource dataSource ;
    
    private static final int GRID_SIZE = 10;
    
    @Autowired
    private Step slaveStep ;

     

    @Bean
    public PartitionHandler partitionHandler() throws Exception {
    	TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setStep(slaveStep);
        partitionHandler.setGridSize(GRID_SIZE);
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        partitionHandler.setTaskExecutor(taskExecutor);
        return partitionHandler;
    }

    @Bean(destroyMethod = "")
    @StepScope
    public JdbcCursorItemReader<DeliverPost> JdbcCursorItemReader(
            @Value("#{stepExecutionContext['current_thread']}") Long current_thread,
            @Value("#{stepExecutionContext['total_thread']}") Long total_thread) {
    	System.err.println("接收到分片参数["+total_thread+"->"+current_thread+"]");
    	
    	JdbcCursorItemReader<DeliverPost> reader = new JdbcCursorItemReader<>();
         reader.setDataSource(this.dataSource); // 设置数据源
         reader.setFetchSize(100); // 设置一次最大读取条数
         reader.setRowMapper(new DeliverPostRowMapper()); // 把数据库中的每条数据映射到Person对中
         reader.setSql("select order_id , post_id from oc_deliver_post_t where post_id is not null and post_id <> '0' and mod(substring(order_id , -4) ,? )= ( ? -1 )");
         
         reader.setPreparedStatementSetter(new PreparedStatementSetter() {
        	   public void setValues(PreparedStatement preparedStatement) throws SQLException {
        		      preparedStatement.setLong(1, total_thread);
        		      preparedStatement.setLong(2, current_thread);
        		  }
        		});
       
        
         
        return  reader;
    }

 

}
