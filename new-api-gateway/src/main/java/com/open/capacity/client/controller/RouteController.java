package com.open.capacity.client.controller;

import com.open.capacity.client.commons.PageResult;
import com.open.capacity.client.commons.Result;
import com.open.capacity.client.dto.GatewayRouteDefinition;
import com.open.capacity.client.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    //增加路由
    @PostMapping("/add")
    public Result add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return Result.succeed(dynamicRouteService.add(gatewayRouteDefinition));
    }

    //更新路由
    @PostMapping("/update")
    public Result update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        return Result.succeed(dynamicRouteService.update(gatewayRouteDefinition));
    }

    //删除路由
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return Result.succeed(dynamicRouteService.delete(id));
    }

    //获取全部数据
    @GetMapping("/findAll")
    public PageResult findAll(@RequestParam Map<String, Object> params){
        return dynamicRouteService.findAll(params);
    }

    //同步redis数据 从mysql中同步过去
    @GetMapping("/synchronization")
    public Result synchronization() {
        return Result.succeed(dynamicRouteService.synchronization());
    }


    //修改路由状态
    @GetMapping("/updateFlag")
    public Result updateFlag(@RequestParam Map<String, Object> params) {
        return Result.succeed(dynamicRouteService.updateFlag(params));
    }





}
