package com.neteye.health.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neteye.health.domain.CompanyDO;
import com.neteye.health.domain.DepartmentDO;
import com.neteye.health.domain.WorktimeDO;
import com.neteye.health.service.CompanyService;
import com.neteye.health.service.DepartmentService;
import com.neteye.health.service.WorktimeService;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 企业分公司
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 11:31:01
 */
 
@Controller
@RequestMapping("/health/department")
public class DepartmentController extends BaseController{
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private WorktimeService worktimeService;
	@Autowired
	private CompanyService companyService;
	
	@GetMapping()
	@RequiresPermissions("health:department:department")
	String Department(){
	    return "health/department/department";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("health:department:department")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DepartmentDO> departmentList = departmentService.list(query);
		int total = departmentService.count(query);
		PageUtils pageUtils = new PageUtils(departmentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("health:department:add")
	String add(Model model){
		Map<String, Object> query = new HashMap();
		query.put("status", 1);
		List<WorktimeDO> workList = worktimeService.list(query);
		model.addAttribute("workList", workList);
		
		query.put("type", 1);
		List<CompanyDO> companyList = companyService.list(query);
		model.addAttribute("companyList", companyList);
		
	    return "health/department/add";
	}

	@GetMapping("/edit/{departmentId}")
	@RequiresPermissions("health:department:edit")
	String edit(@PathVariable("departmentId") Integer departmentId,Model model){
		Map<String, Object> query = new HashMap();
		query.put("status", 1);
		List<WorktimeDO> workList = worktimeService.list(query);
		model.addAttribute("workList", workList);
		
		query.put("type", 1);
		List<CompanyDO> companyList = companyService.list(query);
		model.addAttribute("companyList", companyList);
		
		DepartmentDO department = departmentService.get(departmentId);
		model.addAttribute("department", department);
	    return "health/department/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("health:department:add")
	public R save( DepartmentDO department){
//		if(departmentService.save(department)>0){
//			return R.ok();
//		}
//		return R.error();
		Map map = new HashMap();
		map.put("departmentName", department.getDepartmentName());
		List<CompanyDO> list = departmentService.list(map);
		//判重
		if(list != null && list.size()>0){
			return R.error("分公司名称重复");
		}
		department.setCreateTime(new Date());
		department.setModifiedTime(new Date());
		department.setCreateUser(getUserId());
		department.setModifiedUser(getUserId());
		department.setStatus(1);
		if(departmentService.save(department)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("health:department:edit")
	public R update( DepartmentDO department){
		department.setModifiedTime(new Date());
		department.setModifiedUser(getUserId());
		departmentService.update(department);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("health:department:remove")
	public R remove( Integer departmentId){
//		if(departmentService.remove(departmentId)>0){
//		return R.ok();
//		}
//		return R.error();
		DepartmentDO department = departmentService.get(departmentId);
		department.setStatus(0);
		department.setModifiedTime(new Date());
		department.setModifiedUser(getUserId());
		if(departmentService.update(department)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("health:department:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] departmentIds){
		departmentService.batchRemove(departmentIds);
		return R.ok();
	}
	
}
