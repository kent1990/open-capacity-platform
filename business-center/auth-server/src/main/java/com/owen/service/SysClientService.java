package com.owen.service;

import java.util.List;
import java.util.Map;

import com.owen.common.web.PageResult;
import com.owen.common.web.Result;
import com.owen.dto.SysClientDto;
import com.owen.model.SysClient;

public interface SysClientService {

	
	SysClient getById(Long id) ;
	 

    Result saveOrUpdate(SysClientDto clientDto);

    void deleteClient(Long id);
    
    public PageResult<SysClient> listRoles(Map<String, Object> params);
    
    List<SysClient> findList(Map<String, Object> params) ;
    
    List<SysClient> listByUserId(Long userId) ;


	Result updateEnabled(Map<String, Object> params);
    
}
