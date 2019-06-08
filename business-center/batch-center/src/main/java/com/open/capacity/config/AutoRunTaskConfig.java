package com.open.capacity.config;

import java.util.Date;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

@Component
@Configuration
public class AutoRunTaskConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoRunTaskConfig.class);

	private static final long SECOND = 1000;
	
	private static final String LOCK ="batch-center";
	
	
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;
	
	@Autowired
	private HazelcastInstance hazelcastInstance;

	/**
	 * 固定间隔60秒，可以引用变量 fixedRate：以每次开始时间作为测量，间隔固定时间
	 */
	@Scheduled(cron = "${aop.cron}"  )
	public void orderSync() {
		Lock lock = hazelcastInstance.getLock(LOCK) ;
		LOGGER.info("任务:batch-center触发" );
		if (lock.tryLock()) {
			try {
				jobLauncher.run(job,
						new JobParametersBuilder().addString("batch-center", "batch-center")
								.addDate("date", new Date()).toJobParameters());
			} catch (JobExecutionAlreadyRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JobRestartException e) {
			} catch (JobInstanceAlreadyCompleteException e) {
			} catch (JobParametersInvalidException e) {
			} finally {
				lock.unlock();
			}
		}
		LOGGER.info("任务:batch-center结束");
	}
	 
}
