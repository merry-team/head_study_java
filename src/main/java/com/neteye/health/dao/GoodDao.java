package com.neteye.health.dao;

import com.neteye.health.domain.GoodDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-12 20:29:04
 */
@Mapper
public interface GoodDao {

	GoodDO get(Integer id);
	
	List<GoodDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GoodDO good);
	
	int update(GoodDO good);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
