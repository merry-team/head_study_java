package com.neteye.health.service;

import com.neteye.health.domain.WorktimeDO;

import java.util.List;
import java.util.Map;

/**
 * 营业时间
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 14:36:54
 */
public interface WorktimeService {
	
	WorktimeDO get(Integer workId);
	
	List<WorktimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WorktimeDO worktime);
	
	int update(WorktimeDO worktime);
	
	int remove(Integer workId);
	
	int batchRemove(Integer[] workIds);
}
