package com.open.capacity.batch.job;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.open.capacity.batch.dao.TCardDao;

/**
 * Created by kl on 2018/3/14.
 * Content :job执行监听器
 */
public class JobListener implements JobExecutionListener {
	
	private TCardDao tCardDao ;
	
	

    public TCardDao gettCardDao() {
		return tCardDao;
	}

	public void settCardDao(TCardDao tCardDao) {
		this.tCardDao = tCardDao;
	}

	Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public void beforeJob(JobExecution jobExecution) {
           logger.error("Job准备执行,JobName:{}",jobExecution.getJobInstance().getJobName());
           logger.error("准备备份数据:{}","BI_TCARD_INFO_IMP_T-->BI_TCARD_INFO_IMP_BAK");
           tCardDao.insert4Bak();
           logger.error("准备清理数据:{}","BI_TCARD_INFO_IMP_T");
           tCardDao.delete();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        Map map = new HashMap<String, String>();
        tCardDao.callProcedure(map);
        logger.error("Job转化,执行结果:{}", map.get("ON_RET_CODE")  );
        logger.error("Job执行完毕,执行结果:{}",jobExecution.getExitStatus());
    }
}
