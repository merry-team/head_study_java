package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.CollectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户收藏
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-16 22:28:58
 */
@Mapper
public interface CollectDao {

	CollectDO get(Integer collecttId);
	
	List<CollectDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CollectDO collect);
	
	int update(CollectDO collect);
	
	int remove(Integer collectt_id);
	
	int batchRemove(Integer[] collecttIds);
}
