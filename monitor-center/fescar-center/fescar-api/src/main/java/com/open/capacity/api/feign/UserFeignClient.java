package com.open.capacity.api.feign;

import com.open.capacity.common.web.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("fescar-user")
public interface UserFeignClient {


    /**
     * 扣款接口 Feign 方式调用
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deduction", method = RequestMethod.GET)
    Result deduction(@RequestParam("userId") String userId) throws IllegalAccessException;


}
