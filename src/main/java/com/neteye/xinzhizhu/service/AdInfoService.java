package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.AdInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-08-19 16:05:39
 */
public interface AdInfoService {
	
	AdInfoDO get(Integer id);
	
	List<AdInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AdInfoDO adInfo);
	
	int update(AdInfoDO adInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
