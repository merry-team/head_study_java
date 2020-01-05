package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.CsOrderDao;
import com.neteye.xinzhizhu.domain.CsOrderDO;
import com.neteye.xinzhizhu.service.CsOrderService;



@Service
public class CsOrderServiceImpl implements CsOrderService {
	@Autowired
	private CsOrderDao csOrderDao;
	
	@Override
	public CsOrderDO get(Integer id){
		return csOrderDao.get(id);
	}
	
	@Override
	public List<CsOrderDO> list(Map<String, Object> map){
		return csOrderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return csOrderDao.count(map);
	}
	
	@Override
	public int save(CsOrderDO csOrder){
		return csOrderDao.save(csOrder);
	}
	
	@Override
	public int update(CsOrderDO csOrder){
		return csOrderDao.update(csOrder);
	}
	
	@Override
	public int remove(Integer id){
		return csOrderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return csOrderDao.batchRemove(ids);
	}
	
}
