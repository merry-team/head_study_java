package com.neteye.xinzhizhu.dao;

import com.neteye.xinzhizhu.domain.CategoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:13:35
 */
@Mapper
public interface CategoryDao {

	CategoryDO get(Integer categoryId);
	
	List<CategoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CategoryDO category);
	
	int update(CategoryDO category);
	
	int remove(Integer category_id);
	
	int batchRemove(Integer[] categoryIds);
}
