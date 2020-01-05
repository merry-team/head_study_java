package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.AdPositionDao;
import com.neteye.xinzhizhu.domain.AdPositionDO;
import com.neteye.xinzhizhu.service.AdPositionService;



@Service
public class AdPositionServiceImpl implements AdPositionService {
	@Autowired
	private AdPositionDao adPositionDao;
	
	@Override
	public AdPositionDO get(Integer id){
		return adPositionDao.get(id);
	}
	
	@Override
	public List<AdPositionDO> list(Map<String, Object> map){
		return adPositionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return adPositionDao.count(map);
	}
	
	@Override
	public int save(AdPositionDO adPosition){
		return adPositionDao.save(adPosition);
	}
	
	@Override
	public int update(AdPositionDO adPosition){
		return adPositionDao.update(adPosition);
	}
	
	@Override
	public int remove(Integer id){
		return adPositionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return adPositionDao.batchRemove(ids);
	}
	
}
