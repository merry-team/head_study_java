package com.neteye.health.dao;

import com.neteye.health.domain.DepartmentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 企业分公司
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 11:31:01
 */
@Mapper
public interface DepartmentDao {

	DepartmentDO get(Integer departmentId);
	
	List<DepartmentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DepartmentDO department);
	
	int update(DepartmentDO department);
	
	int remove(Integer department_id);
	
	int batchRemove(Integer[] departmentIds);
}
