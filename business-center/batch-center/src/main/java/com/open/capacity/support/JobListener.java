package com.open.capacity.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.open.capacity.dao.DeliverPostDao;
 
/**
 * @create Created by kl 2019年4月2日
 * Content :job执行监听器
 */
public class JobListener implements JobExecutionListener {

    Logger logger= LoggerFactory.getLogger(getClass());
    
    private DeliverPostDao deliverPostDao ;

    public DeliverPostDao getDeliverPostDao() {
		return deliverPostDao;
	}

	public void setDeliverPostDao(DeliverPostDao deliverPostDao) {
		this.deliverPostDao = deliverPostDao;
	}

	@Override
    public void beforeJob(JobExecution jobExecution) {
           logger.error("Job准备执行,JobName:{}",jobExecution.getJobInstance().getJobName());
           logger.error("准备清理数据:{}","oc_deliver_post_detail_t");
           deliverPostDao.delete();

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        logger.error("EndOfDayJob执行完毕,执行结果:{}",jobExecution.getExitStatus());
    }
}
