package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.CsOrderDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-27 10:43:37
 */
public interface CsOrderService {
	
	CsOrderDO get(Integer id);
	
	List<CsOrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CsOrderDO csOrder);
	
	int update(CsOrderDO csOrder);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
