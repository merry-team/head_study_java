package com.neteye.health.dao;

import com.neteye.health.domain.UsersDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-14 11:19:22
 */
@Mapper
public interface UsersDao {

	UsersDO get(Integer id);
	
	List<UsersDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UsersDO users);
	
	int update(UsersDO users);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
