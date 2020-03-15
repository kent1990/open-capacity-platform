package com.open.capacity.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.open.capacity.api.feign.UserFeignClient;
import com.open.capacity.common.util.RandomUtil;
import com.open.capacity.common.util.UUIDUtils;
import com.open.capacity.order.dao.OcpOrderMapper;
import com.open.capacity.order.entity.OcpOrder;
import com.open.capacity.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl extends ServiceImpl<OcpOrderMapper, OcpOrder> implements OrderService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    @GlobalTransactional
    public String create(String userId) throws IllegalAccessException {
        OcpOrder order = OcpOrder.builder()
                .userId(userId)
                .createTime(new Date())
                .orderSn(RandomUtil.generateOrderCode())
                .id(UUIDUtils.getGUID32())
                .build();
        this.save(order);

        userFeignClient.deduction(userId);

        return order.getId();
    }
}
