package com.neteye.health.service;

import com.neteye.health.domain.UsersDO;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-14 11:19:22
 */
public interface UsersService {
	
	UsersDO get(Integer id);
	
	List<UsersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UsersDO users);
	
	int update(UsersDO users);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int save(UsersDO users,Integer goodId,String ip);
}
