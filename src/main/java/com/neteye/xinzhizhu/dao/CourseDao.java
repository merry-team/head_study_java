package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.CourseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:18:58
 */
@Mapper
public interface CourseDao {

	CourseDO get(Integer courseId);
	
	List<CourseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CourseDO course);
	
	int update(CourseDO course);
	
	int remove(Integer course_id);
	
	int batchRemove(Integer[] courseIds);
	
	int updateplus(CourseDO course);
}
