package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.VodsDao;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.service.VodsService;



@Service
public class VodsServiceImpl implements VodsService {
	@Autowired
	private VodsDao vodsDao;
	
	@Override
	public VodsDO get(Integer vodId){
		return vodsDao.get(vodId);
	}
	
	@Override
	public List<VodsDO> list(Map<String, Object> map){
		return vodsDao.list(map);
	}
	
	@Override
	public List<VodsDO> listsingle(Map<String, Object> map){
		return vodsDao.listsingle(map);
	}	
	
	@Override
	public int count(Map<String, Object> map){
		return vodsDao.count(map);
	}
	
	@Override
	public int save(VodsDO vods){
		return vodsDao.save(vods);
	}
	
	@Override
	public int update(VodsDO vods){
		return vodsDao.update(vods);
	}
	
	@Override
	public int remove(Integer vodId){
		return vodsDao.remove(vodId);
	}
	
	@Override
	public int batchRemove(Integer[] vodIds){
		return vodsDao.batchRemove(vodIds);
	}
	
	@Override
	public int updateplus(VodsDO vods){
		return vodsDao.updateplus(vods);
	}
}
