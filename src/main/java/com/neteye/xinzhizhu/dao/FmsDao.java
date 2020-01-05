package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * fm 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */
@Mapper
public interface FmsDao {

	FmsDO get(Integer fmId);
	
	List<FmsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FmsDO fms);
	
	int update(FmsDO fms);
	
	int remove(Integer fm_id);
	
	int batchRemove(Integer[] fmIds);	
	
	int updateplus(FmsDO fms);
}
