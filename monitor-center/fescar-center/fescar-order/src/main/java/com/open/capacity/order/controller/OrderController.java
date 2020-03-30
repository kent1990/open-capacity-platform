package com.open.capacity.order.controller;

import com.open.capacity.common.web.Result;
import com.open.capacity.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public Result create(@NotNull String userId, @NotNull String productId, HttpServletRequest request) throws IllegalAccessException {
        String orderId = orderService.create(userId,productId);
        return Result.succeed(orderId);
    }
}
