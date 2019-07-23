package com.open.capacity.client.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.open.capacity.client.service.SysClientServiceImpl;


@Component
@Order(1)
public class ScheduledTask implements CommandLineRunner  {
    private static Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Resource
    SysClientServiceImpl sysClientServiceImpl;
     
    //启动全部加载一次同步
    public void run(String... args) throws Exception { 
        log.info("同步数据库信息开始:");
        long startTimeLog = System.currentTimeMillis();
        sysClientServiceImpl.loadAllClientToCache();
        long endTimeLog = System.currentTimeMillis();
        log.info("同步数据库里的信息结束耗时(ms):{}", Long.valueOf(endTimeLog - startTimeLog));
    }
}