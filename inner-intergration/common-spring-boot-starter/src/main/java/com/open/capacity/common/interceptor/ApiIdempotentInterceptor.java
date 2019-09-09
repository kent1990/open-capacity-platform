package com.open.capacity.interceptor;

import com.open.capacity.common.annotation.ApiIdempotent;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@AllArgsConstructor
public class ApiIdempotentInterceptor implements HandlerInterceptor {


    private static final String TOKEN_NAME = "token";

    @Resource
    private RedisTemplate< String, Object> redisTemplate ;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // TODO: 2019-08-27 获取目标方法上的幂等注解
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if (methodAnnotation != null) {
            checkApiIdempotent(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
        }
        return true;
    }


    private void checkApiIdempotent(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                throw new IllegalArgumentException("无效的参数");
            }
        }

        if (!redisTemplate.hasKey(token)) {
            throw new IllegalArgumentException("不存在对应的参数");
        }

        Boolean bool = redisTemplate.delete(token);
        if (!bool) {
            throw new IllegalArgumentException("没有删除对应的token");
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
