package com.open.capacity.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.client.commons.PageResult;
import com.open.capacity.client.dto.GatewayFilterDefinition;
import com.open.capacity.client.dto.GatewayPredicateDefinition;
import com.open.capacity.client.dto.GatewayRouteDefinition;
import com.open.capacity.client.entity.GatewayRoutes;
import com.open.capacity.client.mapper.GatewayRoutesMapper;
import com.open.capacity.client.service.IDynamicRouteService;
import com.open.capacity.client.vo.GatewayRoutesVO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static com.open.capacity.client.routes.RedisRouteDefinitionRepository.GATEWAY_ROUTES_PREFIX;

@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware, IDynamicRouteService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Autowired
    private GatewayRoutesMapper gatewayRoutesMapper;


    /**
     * 初始化 转化对象
     */
    private static MapperFacade routeDefinitionMapper;
    private static MapperFacade routeVOMapper;
    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(GatewayRouteDefinition.class, GatewayRoutes.class)
                .exclude("filters")
                .exclude("predicates")
                .byDefault();
        routeDefinitionMapper = mapperFactory.getMapperFacade();

        //  routeVOMapper
        mapperFactory.classMap(GatewayRoutes.class, GatewayRoutesVO.class)
                .byDefault();
        routeVOMapper = mapperFactory.getMapperFacade();

    }


    /**
     *  给spring注册事件
     *      刷新路由
     */
    private void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * Set the ApplicationEventPublisher that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's afterPropertiesSet or a custom init-method.
     * Invoked before ApplicationContextAware's setApplicationContext.
     *
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

//    @Override
//    public String add(RouteDefinition definition) {
//        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX + definition.getId(), JSONObject.toJSONString(definition));
//        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//        notifyChanged();
//        return "success";
//    }
//
//    @Override
//    public String update(RouteDefinition definition) {
//        redisTemplate.delete(GATEWAY_ROUTES_PREFIX + definition.getId());
//        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX + definition.getId(), JSONObject.toJSONString(definition));
//        return "success";
//        try {
//            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
//        } catch (Exception e) {
//            return "update fail,not find route  routeId: " + definition.getId();
//        }
//        try {
//            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//            notifyChanged();
//            return "success";
//        } catch (Exception e) {
//            return "update route  fail";
//        }
//    }




    /**
     * 新增路由
     *
     * @param gatewayRouteDefinition
     * @return
     */
    @Override
    public String add(GatewayRouteDefinition gatewayRouteDefinition) {
        GatewayRoutes gatewayRoutes = transformToGatewayRoutes(gatewayRouteDefinition);
        gatewayRoutes.setDelFlag(0);
        gatewayRoutes.setCreateTime(new Date());
        gatewayRoutes.setUpdateTime(new Date());
        gatewayRoutesMapper.insertSelective(gatewayRoutes);

        gatewayRouteDefinition.setId(gatewayRoutes.getId());

        /**
         * 新增到对应的数组
         */
        String s = redisTemplate.opsForValue().get(GATEWAY_ROUTES_PREFIX);
        List<GatewayRouteDefinition> gatewayRouteDefinitions = JSONArray.parseArray(s, GatewayRouteDefinition.class);
        gatewayRouteDefinitions.add(gatewayRouteDefinition);
        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX, JSONArray.toJSONString(gatewayRouteDefinitions));
        return gatewayRoutes.getId();
    }

    /**
     * 修改路由
     *
     * @param gatewayRouteDefinition
     * @return
     */
    @Override
    public String update(GatewayRouteDefinition gatewayRouteDefinition) {
        GatewayRoutes gatewayRoutes = transformToGatewayRoutes(gatewayRouteDefinition);
        gatewayRoutes.setCreateTime(new Date());
        gatewayRoutes.setUpdateTime(new Date());
        gatewayRoutesMapper.updateByPrimaryKeySelective(gatewayRoutes);

        /**
         * 修改对应路由
         */
        String s = redisTemplate.opsForValue().get(GATEWAY_ROUTES_PREFIX);
        List<GatewayRouteDefinition> gatewayRouteDefinitions = JSONArray.parseArray(s, GatewayRouteDefinition.class);
        List<GatewayRouteDefinition> collect = gatewayRouteDefinitions.stream().filter(definition -> {
            if (StringUtils.equals(definition.getId(), gatewayRoutes.getId())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        collect.add(gatewayRouteDefinition);
        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX, JSONArray.toJSONString(gatewayRouteDefinitions));
        return gatewayRouteDefinition.getId();
    }


    /**
     * 删除路由
     * @param id
     * @return
     */
    @Override
    public String delete(String id) {
        gatewayRoutesMapper.deleteByPrimaryKey(id);
        /**
         * 从redis删除
         */
        String s = redisTemplate.opsForValue().get(GATEWAY_ROUTES_PREFIX);
        List<GatewayRouteDefinition> gatewayRouteDefinitions = JSONArray.parseArray(s, GatewayRouteDefinition.class);
        List<GatewayRouteDefinition> collect = gatewayRouteDefinitions.stream().filter(definition -> {
            if (StringUtils.equals(definition.getId(),id)) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX, JSONArray.toJSONString(gatewayRouteDefinitions));


        redisTemplate.delete(GATEWAY_ROUTES_PREFIX + id);
        return "success";
//        try {
//            this.routeDefinitionWriter.delete(Mono.just(id));
//            notifyChanged();
//            return "delete success";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "delete fail";
//        }
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @Override
    public PageResult<GatewayRoutesVO> findAll(Map<String, Object> params) {
        PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);
        List<GatewayRoutes> alls = gatewayRoutesMapper.findAll(new HashMap());
        PageInfo<GatewayRoutesVO> pageInfo = new PageInfo<>(routeVOMapper.mapAsList(alls, GatewayRoutesVO.class));
        return PageResult.<GatewayRoutesVO>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    /**
     * @return
     */
    @Override
    public String synchronization() {
        HashMap map = new HashMap();
        map.put("delFlag", 0);
        List<GatewayRoutes> alls = gatewayRoutesMapper.findAll(map);

        List<GatewayRouteDefinition> gatewayRouteDefinitions = new ArrayList<>();
        for (GatewayRoutes route:   alls) {
            GatewayRouteDefinition gatewayRouteDefinition = GatewayRouteDefinition.builder()
                    .description(route.getDescription())
                    .id(route.getId())
                    .order(route.getOrder())
                    .uri(route.getUri())
                    .build();

            List<GatewayFilterDefinition> gatewayFilterDefinitions = JSONArray.parseArray(route.getFilters(), GatewayFilterDefinition.class);
            List<GatewayPredicateDefinition> gatewayPredicateDefinitions = JSONArray.parseArray(route.getPredicates(), GatewayPredicateDefinition.class);
            gatewayRouteDefinition.setPredicates(gatewayPredicateDefinitions);
            gatewayRouteDefinition.setFilters(gatewayFilterDefinitions);
            gatewayRouteDefinitions.add(gatewayRouteDefinition);

        }
        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX , JSONArray.toJSONString(gatewayRouteDefinitions));

        return "success";
    }

    /**
     * 更改路由状态
     *
     * @param params
     * @return
     */
    @Override
    public String updateFlag(Map<String, Object> params) {
        String id = MapUtils.getString(params, "id");
        Integer flag = MapUtils.getInteger(params, "flag");
        List<GatewayRouteDefinition> gatewayRouteDefinitions = new ArrayList<>();


        GatewayRoutes gatewayRoutes = gatewayRoutesMapper.selectByPrimaryKey(id);
        if (gatewayRoutes == null) {
            return "路由不存在";
        }

        if (flag == 1){
            redisTemplate.delete(GATEWAY_ROUTES_PREFIX + id);
        }else {
            GatewayRouteDefinition gatewayRouteDefinition = GatewayRouteDefinition.builder()
                    .description(gatewayRoutes.getDescription())
                    .id(gatewayRoutes.getId())
                    .order(gatewayRoutes.getOrder())
                    .uri(gatewayRoutes.getUri())
                    .build();

            List<GatewayFilterDefinition> gatewayFilterDefinitions = JSONArray.parseArray(gatewayRoutes.getFilters(), GatewayFilterDefinition.class);
            List<GatewayPredicateDefinition> gatewayPredicateDefinitions = JSONArray.parseArray(gatewayRoutes.getPredicates(), GatewayPredicateDefinition.class);
            gatewayRouteDefinition.setPredicates(gatewayPredicateDefinitions);
            gatewayRouteDefinition.setFilters(gatewayFilterDefinitions);
            gatewayRouteDefinitions.add(gatewayRouteDefinition);

        }
        redisTemplate.opsForValue().set(GATEWAY_ROUTES_PREFIX , JSONArray.toJSONString(gatewayRouteDefinitions));
        gatewayRoutes.setDelFlag(flag);
        gatewayRoutes.setUpdateTime(new Date());
        int i = gatewayRoutesMapper.updateByPrimaryKeySelective(gatewayRoutes);
        return i > 0 ? "更新成功": "更新失败";
    }

    /**
     * 转化路由对象  GatewayRoutes
     * @param gatewayRouteDefinition
     * @return
     */
    private GatewayRoutes transformToGatewayRoutes(GatewayRouteDefinition gatewayRouteDefinition){
        GatewayRoutes definition = new GatewayRoutes();
        routeDefinitionMapper.map(gatewayRouteDefinition,definition);
        //设置路由id
        if (!StringUtils.isNotBlank(definition.getId())){
            definition.setId(java.util.UUID.randomUUID().toString().toUpperCase().replace("-",""));
        }

        String filters = JSONArray.toJSONString(gatewayRouteDefinition.getFilters());
        String predicates = JSONArray.toJSONString(gatewayRouteDefinition.getPredicates());

        definition.setFilters(filters);
        definition.setPredicates(predicates);

        return definition;
    }

    /**
     * 测试方法 新建 一个路由
     */
//    @PostConstruct
    public void main() {
        RouteDefinition definition = new RouteDefinition();
        definition.setId("jd");
        URI uri = UriComponentsBuilder.fromUriString("lb://user-center").build().toUri();
//         URI uri = UriComponentsBuilder.fromHttpUrl("http://baidu.com").build().toUri();
        definition.setUri(uri);
        definition.setOrder(11111);

        //定义第一个断言
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");

        Map<String, String> predicateParams = new HashMap<>(8);
        predicateParams.put("pattern", "/jd/**");
        predicate.setArgs(predicateParams);
        //定义Filter
        FilterDefinition filter = new FilterDefinition();
        filter.setName("StripPrefix");
        Map<String, String> filterParams = new HashMap<>(8);
        //该_genkey_前缀是固定的，见org.springframework.cloud.gateway.support.NameUtils类
        filterParams.put("_genkey_0", "1");
        filter.setArgs(filterParams);

        definition.setFilters(Arrays.asList(filter));
        definition.setPredicates(Arrays.asList(predicate));

        System.out.println("definition:" + JSON.toJSONString(definition));
        redisTemplate.opsForHash().put(GATEWAY_ROUTES_PREFIX, "key", JSON.toJSONString(definition));
    }
}
