package com.open.capacity.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.open.capacity.api.feign.ItemFeignClient;
import com.open.capacity.api.feign.UserFeignClient;
import com.open.capacity.common.util.RandomUtil;
import com.open.capacity.common.util.UUIDUtils;
import com.open.capacity.order.dao.OcpOrderMapper;
import com.open.capacity.order.entity.OcpOrder;
import com.open.capacity.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OcpOrderMapper, OcpOrder> implements OrderService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ItemFeignClient itemFeignClient;

    @Override
    @GlobalTransactional
    public String create(String userId,String productId) throws IllegalAccessException {
        log.info("-----> 开始新建订单");
        OcpOrder order = OcpOrder.builder()
                .userId(userId)
                .createTime(new Date())
                .orderSn(RandomUtil.generateOrderCode())
                .id(UUIDUtils.getGUID32())
                .build();
        this.save(order);
        log.info("-----> 结束新建订单");


        log.info("-----> 开始扣除库存");
        itemFeignClient.deductInventory(productId);
        log.info("-----> 结束扣除库存");


        log.info("-----> 开始扣除用户金额，如果用户金额不够直接报错，回滚数据");
        userFeignClient.deductionAmount(userId);

        return order.getId();
    }
}
