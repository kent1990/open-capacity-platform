package com.open.capacity.server.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.commons.PageResult;
import com.open.capacity.commons.Result;
import com.open.capacity.server.dao.SysClientDao;
import com.open.capacity.server.dao.SysClientServiceDao;
import com.open.capacity.server.dto.ClientDto;
import com.open.capacity.server.model.Client;
import com.open.capacity.server.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "oauth_client_details";

    @Autowired
    private SysClientDao sysClientDao;

    @Autowired
    private SysClientServiceDao sysClientServiceDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;


     
    @Override
    public Result saveOrUpdate(ClientDto clientDto) {
        clientDto.setClientSecret(passwordEncoder.encode(clientDto.getClientSecretStr()));

        if (clientDto.getId() != null) {// 修改
            sysClientDao.update(clientDto);
        } else {// 新增
            Client r = sysClientDao.getClient(clientDto.getClientId());
            if (r != null) {
                return Result.failed(clientDto.getClientId()+"已存在");
            }
            sysClientDao.save(clientDto);
        }
        return Result.succeed("操作成功");
    }

     

    @Override
    @Transactional
    public void deleteClient(Long id) {
        sysClientDao.delete(id);

        sysClientServiceDao.delete(id,null);

        log.debug("删除应用id:{}", id);
    }

	@Override
	public PageResult<Client> listRoles(Map<String, Object> params) {

        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);
        List<Client> list = sysClientDao.findList(params);
        PageInfo<Client> pageInfo = new PageInfo<>(list);
        return PageResult.<Client>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build()  ;

//		// TODO Auto-generated method stub
//		int total = clientDao.count(params);
//		List<Client> list = Collections.emptyList();
//
//		if (total > 0) {
//			PageUtil.pageParamConver(params, false);
//			list = clientDao.findList(params);
//
//		}
//		return PageResult.<Client>builder().data(list).code(0).count((long)total).build()  ;
	}
	public  Client getById(Long id) {
		return sysClientDao.getById(id);
	}

	@Override
	public List<Client> findList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sysClientDao.findList(params);
	}

	@Override
	public List<Client> listByUserId(Long userId) {
		// TODO Auto-generated method stub
		return sysClientDao.listByUserId(userId);
	}

}
