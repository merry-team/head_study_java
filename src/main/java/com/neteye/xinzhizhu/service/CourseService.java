package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.CourseDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:18:58
 */
public interface CourseService {
	
	CourseDO get(Integer courseId);
	
	List<CourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseDO course);
	
	int update(CourseDO course);
	
	int remove(Integer courseId);
	
	int batchRemove(Integer[] courseIds);
	
	int updateplus(CourseDO course);
}
