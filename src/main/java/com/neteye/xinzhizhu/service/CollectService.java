package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.CollectDO;

import java.util.List;
import java.util.Map;

/**
 * 用户收藏
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-16 22:28:58
 */
public interface CollectService {
	
	CollectDO get(Integer collecttId);
	
	List<CollectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CollectDO collect);
	
	int update(CollectDO collect);
	
	int remove(Integer collecttId);
	
	int batchRemove(Integer[] collecttIds);
	
	int read(CollectDO collect);
}
