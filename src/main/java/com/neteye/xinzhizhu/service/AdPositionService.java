package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.AdPositionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-08-19 12:25:16
 */
public interface AdPositionService {
	
	AdPositionDO get(Integer id);
	
	List<AdPositionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AdPositionDO adPosition);
	
	int update(AdPositionDO adPosition);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
