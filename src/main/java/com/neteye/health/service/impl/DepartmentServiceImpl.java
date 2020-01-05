package com.neteye.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.health.dao.DepartmentDao;
import com.neteye.health.domain.DepartmentDO;
import com.neteye.health.service.DepartmentService;



@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public DepartmentDO get(Integer departmentId){
		return departmentDao.get(departmentId);
	}
	
	@Override
	public List<DepartmentDO> list(Map<String, Object> map){
		return departmentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return departmentDao.count(map);
	}
	
	@Override
	public int save(DepartmentDO department){
		return departmentDao.save(department);
	}
	
	@Override
	public int update(DepartmentDO department){
		return departmentDao.update(department);
	}
	
	@Override
	public int remove(Integer departmentId){
		return departmentDao.remove(departmentId);
	}
	
	@Override
	public int batchRemove(Integer[] departmentIds){
		return departmentDao.batchRemove(departmentIds);
	}
	
}
