package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.AdInfoDao;
import com.neteye.xinzhizhu.domain.AdInfoDO;
import com.neteye.xinzhizhu.service.AdInfoService;



@Service
public class AdInfoServiceImpl implements AdInfoService {
	@Autowired
	private AdInfoDao adInfoDao;
	
	@Override
	public AdInfoDO get(Integer id){
		return adInfoDao.get(id);
	}
	
	@Override
	public List<AdInfoDO> list(Map<String, Object> map){
		return adInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return adInfoDao.count(map);
	}
	
	@Override
	public int save(AdInfoDO adInfo){
		return adInfoDao.save(adInfo);
	}
	
	@Override
	public int update(AdInfoDO adInfo){
		return adInfoDao.update(adInfo);
	}
	
	@Override
	public int remove(Integer id){
		return adInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return adInfoDao.batchRemove(ids);
	}
	
}
