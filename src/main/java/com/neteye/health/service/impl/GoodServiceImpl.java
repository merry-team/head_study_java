package com.neteye.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.health.dao.GoodDao;
import com.neteye.health.domain.GoodDO;
import com.neteye.health.service.GoodService;



@Service
public class GoodServiceImpl implements GoodService {
	@Autowired
	private GoodDao goodDao;
	
	@Override
	public GoodDO get(Integer id){
		return goodDao.get(id);
	}
	
	@Override
	public List<GoodDO> list(Map<String, Object> map){
		return goodDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goodDao.count(map);
	}
	
	@Override
	public int save(GoodDO good){
		return goodDao.save(good);
	}
	
	@Override
	public int update(GoodDO good){
		return goodDao.update(good);
	}
	
	@Override
	public int remove(Integer id){
		return goodDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return goodDao.batchRemove(ids);
	}
	
}
