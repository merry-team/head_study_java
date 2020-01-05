package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.CourseDao;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.service.CourseService;



@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public CourseDO get(Integer courseId){
		return courseDao.get(courseId);
	}
	
	@Override
	public List<CourseDO> list(Map<String, Object> map){
		return courseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseDao.count(map);
	}
	
	@Override
	public int save(CourseDO course){
		return courseDao.save(course);
	}
	
	@Override
	public int update(CourseDO course){
		return courseDao.update(course);
	}
	
	@Override
	public int remove(Integer courseId){
		return courseDao.remove(courseId);
	}
	
	@Override
	public int batchRemove(Integer[] courseIds){
		return courseDao.batchRemove(courseIds);
	}
	
	@Override
	public int updateplus(CourseDO course){
		return courseDao.updateplus(course);
	}	

}
