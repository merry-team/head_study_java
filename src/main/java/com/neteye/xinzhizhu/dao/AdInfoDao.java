package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.AdInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-08-19 16:05:39
 */
@Mapper
public interface AdInfoDao {

	AdInfoDO get(Integer id);
	
	List<AdInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AdInfoDO adInfo);
	
	int update(AdInfoDO adInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
