package com.neteye.xinzhizhu.service;

import com.neteye.xinzhizhu.domain.CategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:13:35
 */
public interface CategoryService {
	
	CategoryDO get(Integer categoryId);
	
	List<CategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CategoryDO category);
	
	int update(CategoryDO category);
	
	int remove(Integer categoryId);
	
	int batchRemove(Integer[] categoryIds);
}
