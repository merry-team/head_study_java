package com.neteye.health.service;

import com.neteye.health.domain.CompanyDO;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-10 18:00:34
 */
public interface CompanyService {
	
	CompanyDO get(Integer companyId);
	
	List<CompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(Integer companyId);
	
	int batchRemove(Integer[] companyIds);
}
