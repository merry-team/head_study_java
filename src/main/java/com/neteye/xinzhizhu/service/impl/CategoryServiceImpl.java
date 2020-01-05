package com.neteye.xinzhizhu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.neteye.xinzhizhu.dao.CategoryDao;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.service.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public CategoryDO get(Integer categoryId){
		return categoryDao.get(categoryId);
	}
	
	@Override
	public List<CategoryDO> list(Map<String, Object> map){
		return categoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return categoryDao.count(map);
	}
	
	@Override
	public int save(CategoryDO category){
		return categoryDao.save(category);
	}
	
	@Override
	public int update(CategoryDO category){
		return categoryDao.update(category);
	}
	
	@Override
	public int remove(Integer categoryId){
		return categoryDao.remove(categoryId);
	}
	
	@Override
	public int batchRemove(Integer[] categoryIds){
		return categoryDao.batchRemove(categoryIds);
	}
	
}
