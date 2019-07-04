package com.open.capacity.server.service;

import java.util.List;
import java.util.Map;

import com.open.capacity.commons.PageResult;
import com.open.capacity.commons.Result;
import com.open.capacity.server.dto.SysClientDto;
import com.open.capacity.server.model.SysClient;

public interface SysClientService {

	
	SysClient getById(Long id) ;
	 

    Result saveOrUpdate(SysClientDto clientDto);

    void deleteClient(Long id);
    
    public PageResult<SysClient> listRoles(Map<String, Object> params);
    
    List<SysClient> findList(Map<String, Object> params) ;
    
    List<SysClient> listByUserId(Long userId) ;


	Result updateEnabled(Map<String, Object> params);
    
}
