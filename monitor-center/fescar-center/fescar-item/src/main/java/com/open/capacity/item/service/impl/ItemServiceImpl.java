package com.open.capacity.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.open.capacity.common.web.Result;
import com.open.capacity.item.dao.OcpItemMapper;
import com.open.capacity.item.entity.OcpItem;
import com.open.capacity.item.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ServiceImpl<OcpItemMapper, OcpItem> implements ItemService {
    @Override
    public Result deductInventory(String productId) throws IllegalAccessException {
        // TODO: 2020/3/11 条件构造器
        Wrapper<OcpItem> wrapper = new QueryWrapper<OcpItem>()
                .lambda().eq(OcpItem::getProductId,productId);

        OcpItem ocpItem = baseMapper.selectOne(wrapper);
        int i = ocpItem.getResidue() - 10;
        if (i >= 0){
            ocpItem.setUsed(ocpItem.getUsed() + 10);
            ocpItem.setResidue(i);
        }else {
            throw new IllegalAccessException("库存不足");
        }
        return Result.succeedWith(baseMapper.updateById(ocpItem),0,null);
    }
}
