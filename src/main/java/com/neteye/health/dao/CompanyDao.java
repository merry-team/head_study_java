package com.neteye.health.dao;

import com.neteye.health.domain.CompanyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 企业信息表
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-10 18:00:34
 */
@Mapper
public interface CompanyDao {

	CompanyDO get(Integer companyId);
	
	List<CompanyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(Integer company_id);
	
	int batchRemove(Integer[] companyIds);
}
