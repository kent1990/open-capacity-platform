package com.owen.interceptor;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.UUID;

/**
 *  @author zcl
 *  @date 2019/8/12
 */
public class MethodLevelLogContent {

    /**
     * traceId  用于跟线程
     */
    private String traceId;

    /**
     * 被切面方法名
     */
    private String methodName;

    /**
     * 方法入参
     */
    private Object[] input;

    /**
     * 方法出参 返回参数
     */
    private Object output;

    /**
     * 总消耗时间
     */
    private long costMillis;

    /**
     * 异常
     */
    private Throwable exception;

    public MethodLevelLogContent(String methodName, Object[] input, Object output, long costMillis, Throwable exception) {
        String traceIdThread = ThreadContext.get("traceId");
        if (Strings.isBlank(traceIdThread)){
            String uuid = UUID.randomUUID().toString();
            ThreadContext.put("traceId",uuid);
            traceId=uuid;
        }else {
            traceId = traceIdThread;
        }
        this.methodName = methodName;
        this.input = input;
        this.output = output;
        this.costMillis = costMillis;
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "{" +
                "traceId='" + traceId + '\'' +
                ", methodName='" + methodName + '\'' +
                ", input=" + Arrays.toString(input) +
                ", output=" + output +
                ", costMillis=" + costMillis +
                ", exception=" + exception +
                '}';
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getInput() {
        return input;
    }

    public void setInput(Object[] input) {
        this.input = input;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public long getCostMillis() {
        return costMillis;
    }

    public void setCostMillis(long costMillis) {
        this.costMillis = costMillis;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
