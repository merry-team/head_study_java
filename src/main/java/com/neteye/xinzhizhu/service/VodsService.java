package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.VodsDO;

import java.util.List;
import java.util.Map;

/**
 * vod
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:33:25
 */
public interface VodsService {
	
	VodsDO get(Integer vodId);
	
	List<VodsDO> list(Map<String, Object> map);
	
	List<VodsDO> listsingle(Map<String, Object> map);
	
	
	int count(Map<String, Object> map);
	
	int save(VodsDO vods);
	
	int update(VodsDO vods);
	
	int remove(Integer vodId);
	
	int batchRemove(Integer[] vodIds);
	
	int updateplus(VodsDO vods);
}
