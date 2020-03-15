package com.open.capacity.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.open.capacity.order.entity.OcpOrder;

public interface OrderService  extends IService<OcpOrder> {


    String create(String userId) throws IllegalAccessException;

}
