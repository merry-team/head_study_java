package com.neteye.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.health.dao.WorktimeDao;
import com.neteye.health.domain.WorktimeDO;
import com.neteye.health.service.WorktimeService;



@Service
public class WorktimeServiceImpl implements WorktimeService {
	@Autowired
	private WorktimeDao worktimeDao;
	
	@Override
	public WorktimeDO get(Integer workId){
		return worktimeDao.get(workId);
	}
	
	@Override
	public List<WorktimeDO> list(Map<String, Object> map){
		return worktimeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return worktimeDao.count(map);
	}
	
	@Override
	public int save(WorktimeDO worktime){
		return worktimeDao.save(worktime);
	}
	
	@Override
	public int update(WorktimeDO worktime){
		return worktimeDao.update(worktime);
	}
	
	@Override
	public int remove(Integer workId){
		return worktimeDao.remove(workId);
	}
	
	@Override
	public int batchRemove(Integer[] workIds){
		return worktimeDao.batchRemove(workIds);
	}
	
}
