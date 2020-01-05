package com.neteye.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.health.dao.OrdersDao;
import com.neteye.health.domain.OrdersDO;
import com.neteye.health.service.OrdersService;



@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersDao ordersDao;
	
	@Override
	public OrdersDO get(Integer id){
		return ordersDao.get(id);
	}
	
	@Override
	public List<OrdersDO> list(Map<String, Object> map){
		return ordersDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ordersDao.count(map);
	}
	
	@Override
	public int save(OrdersDO orders){
		return ordersDao.save(orders);
	}
	
	@Override
	public int update(OrdersDO orders){
		return ordersDao.update(orders);
	}
	
	@Override
	public int remove(Integer id){
		return ordersDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return ordersDao.batchRemove(ids);
	}
	
	@Override
	public int booking(Integer id){
		return ordersDao.booking(id);
	}
	
	@Override
	public int batchBooking(Integer[] ids){
		return ordersDao.batchBooking(ids);
	}
}
