package com.open.capacity.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.open.capacity.common.web.Result;
import com.open.capacity.item.entity.OcpItem;

public interface ItemService extends IService<OcpItem> {

    Result deductInventory(String productId) throws IllegalAccessException;

}
