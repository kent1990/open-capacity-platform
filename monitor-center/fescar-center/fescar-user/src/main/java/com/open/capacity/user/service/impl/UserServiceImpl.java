package com.open.capacity.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.open.capacity.common.web.Result;
import com.open.capacity.user.dao.OcpTqlMapper;
import com.open.capacity.user.entity.OcpTql;
import com.open.capacity.user.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<OcpTqlMapper, OcpTql> implements UserService {


    @Override
    public Result deductionAmount(String userId)  throws IllegalAccessException{

        //模拟超时异常，全局事务回滚
//        try {
//            Thread.sleep(30*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // TODO: 2020/3/11 条件构造器
        Wrapper<OcpTql> wrapper = new QueryWrapper<OcpTql>()
                .lambda().eq(OcpTql::getUserId,userId);

        OcpTql ocpTql = baseMapper.selectOne(wrapper);
        int i = ocpTql.getMoney() - 10;
        if (i >= 0){
            ocpTql.setMoney(i);
        }else {
            throw new IllegalAccessException("资金不足");
        }
        return Result.succeedWith(baseMapper.updateById(ocpTql),0,null);
    }
}
