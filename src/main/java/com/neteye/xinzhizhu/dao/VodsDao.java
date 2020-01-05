package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * vod
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:33:25
 */
@Mapper
public interface VodsDao {

	VodsDO get(Integer vodId);
	
	List<VodsDO> list(Map<String,Object> map);

	List<VodsDO> listsingle(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(VodsDO vods);
	
	int update(VodsDO vods);
	
	int remove(Integer vod_id);
	
	int batchRemove(Integer[] vodIds);
	
	int updateplus(VodsDO vods);
}
