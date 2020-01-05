package com.neteye.health.dao;

import com.neteye.health.domain.WorktimeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 营业时间
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 14:36:54
 */
@Mapper
public interface WorktimeDao {

	WorktimeDO get(Integer workId);
	
	List<WorktimeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WorktimeDO worktime);
	
	int update(WorktimeDO worktime);
	
	int remove(Integer work_id);
	
	int batchRemove(Integer[] workIds);
}
