package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.FmsDao;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.service.FmsService;



@Service
public class FmsServiceImpl implements FmsService {
	@Autowired
	private FmsDao fmsDao;
	
	@Override
	public FmsDO get(Integer fmId){
		return fmsDao.get(fmId);
	}
	
	@Override
	public List<FmsDO> list(Map<String, Object> map){
		return fmsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fmsDao.count(map);
	}
	
	@Override
	public int save(FmsDO fms){
		return fmsDao.save(fms);
	}
	
	@Override
	public int update(FmsDO fms){
		return fmsDao.update(fms);
	}
	
	@Override
	public int remove(Integer fmId){
		return fmsDao.remove(fmId);
	}
	
	@Override
	public int batchRemove(Integer[] fmIds){
		return fmsDao.batchRemove(fmIds);
	}
	
	@Override
	public int updateplus(FmsDO fms){
		return fmsDao.updateplus(fms);
	}
}
