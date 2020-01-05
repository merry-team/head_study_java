package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.FmsDO;

import java.util.List;
import java.util.Map;

/**
 * fm 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */
public interface FmsService {
	
	FmsDO get(Integer fmId);
	
	List<FmsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FmsDO fms);
	
	int update(FmsDO fms);
	
	int remove(Integer fmId);
	
	int batchRemove(Integer[] fmIds);
	
	int updateplus(FmsDO fms);
}
