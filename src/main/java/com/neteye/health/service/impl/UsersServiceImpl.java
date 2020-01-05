package com.neteye.health.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neteye.health.dao.GoodDao;
import com.neteye.health.dao.OrdersDao;
import com.neteye.health.dao.UsersDao;
import com.neteye.health.domain.GoodDO;
import com.neteye.health.domain.OrdersDO;
import com.neteye.health.domain.UsersDO;
import com.neteye.health.service.UsersService;
import com.neteye.xinzhizhu.utils.CommonUtil;
import com.neteye.xinzhizhu.utils.DateUtils;




@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private GoodDao goodDao;
	@Autowired
	private OrdersDao ordersDao;
	
	@Override
	public UsersDO get(Integer id){
		return usersDao.get(id);
	}
	
	@Override
	public List<UsersDO> list(Map<String, Object> map){
		return usersDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return usersDao.count(map);
	}
	
	@Override
	public int save(UsersDO users){
		return usersDao.save(users);
	}
	
	@Override
	public int update(UsersDO users){
		return usersDao.update(users);
	}
	
	@Override
	public int remove(Integer id){
		return usersDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return usersDao.batchRemove(ids);
	}
	
	@Override
	public int save(UsersDO users,Integer goodId,String ip) {
		//保存用户信息
		users.setIdcard(users.getIdcard().toLowerCase());
		users.setUsername(users.getMobile());
		users.setPassword(users.getIdcard().substring(users.getIdcard().length()-6, users.getIdcard().length()));
		Date b = DateUtils.parse(users.getIdcard().substring(6,14), "yyyyMMdd");
		if(b!=null) {
			users.setBirthday(b);
		}
		
		users.setRegisterTime(new Date());
		users.setRegisterIp(ip);
		users.setNickname(users.getName());
		users.setStatus(1);
		usersDao.save(users);
		
		//保存订单信息
		GoodDO good = goodDao.get(goodId);
		if(good!=null) {
			OrdersDO orders = new OrdersDO();
			orders.setOrderSn(CommonUtil.generateOrderNumber()); //订单编号
			orders.setUserId(users.getId()); //会员Id
			orders.setOrderStatus(200); //订单状态
			orders.setPayType(1);//付费类型：
			orders.setPayStatus(2);//付款状态
			orders.setMobile(users.getMobile());//手机
			orders.setGoodId(goodId);
			orders.setGoodName(good.getGoodName());
			orders.setOrderPrice(good.getRetailPrice());
			orders.setGoodsPrice(good.getRetailPrice());
			orders.setAddTime(new Date());
			//orders.setConfirmTime(new Date());
			orders.setPayTime(new Date());
			orders.setBookingStatus(0);
			return ordersDao.save(orders);
		}		
		else {
			return 1;
		}
		
	}
}
