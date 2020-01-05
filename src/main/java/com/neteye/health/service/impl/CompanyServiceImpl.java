package com.neteye.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.health.dao.CompanyDao;
import com.neteye.health.domain.CompanyDO;
import com.neteye.health.service.CompanyService;



@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public CompanyDO get(Integer companyId){
		return companyDao.get(companyId);
	}
	
	@Override
	public List<CompanyDO> list(Map<String, Object> map){
		return companyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return companyDao.count(map);
	}
	
	@Override
	public int save(CompanyDO company){
		return companyDao.save(company);
	}
	
	@Override
	public int update(CompanyDO company){
		return companyDao.update(company);
	}
	
	@Override
	public int remove(Integer companyId){
		return companyDao.remove(companyId);
	}
	
	@Override
	public int batchRemove(Integer[] companyIds){
		return companyDao.batchRemove(companyIds);
	}
	
}
