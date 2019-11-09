package com.owen.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: CatchLogCollect
 * Description:
 *
 * @author zcl
 * @since JDK 1.8
 */
@Configuration
@Aspect
public class CatchLogCollect {

    @Around("@annotation(com.owen.interceptor.Loggable)")
    public Object intercept(ProceedingJoinPoint point) throws Throwable {
        //获取截取类的logger
        Logger logger = LogManager.getLogger(point.getTarget().getClass());

        //获取方法
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        //获取方法名
        String method = point.getSignature().getName();
        //开始时间
        long startTimeMillis = System.currentTimeMillis();
        //消耗时间
        long costMillis = 0L;
        //被切面方法 执行之后返回的参数
        Object result = null;
        //被切面方法 执行过程中抛的异常
        Throwable exception = null;

        try {
            result = point.proceed();
        } catch (Throwable var14) {
            exception = var14;
        } finally {
            costMillis = System.currentTimeMillis() - startTimeMillis;
        }

        MethodLevelLogContent logContent = new MethodLevelLogContent(method, point.getArgs(), result, costMillis, exception);
        String s = JSON.toJSONString(logContent);
        if (exception == null ) {
            logger.log(Level.INFO,s);
        }

        if (exception != null) {
            logger.log(Level.ERROR, s, exception);
            throw exception;
        } else {
            return result;
        }
    }




}
