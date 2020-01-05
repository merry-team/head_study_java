package com.neteye.health.service;

import com.neteye.health.domain.OrdersDO;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-18 12:38:24
 */
public interface OrdersService {
	
	OrdersDO get(Integer id);
	
	List<OrdersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrdersDO orders);
	
	int update(OrdersDO orders);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int booking(Integer id);
	
	int batchBooking(Integer[] ids);
}
