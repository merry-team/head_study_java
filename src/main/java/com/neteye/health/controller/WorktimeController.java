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
import com.neteye.health.service.WorktimeService;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 营业时间
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 14:36:54
 */
 
@Controller
@RequestMapping("/health/worktime")
public class WorktimeController  extends BaseController{
	@Autowired
	private WorktimeService worktimeService;
	
	@GetMapping()
	@RequiresPermissions("health:worktime:worktime")
	String Worktime(){
	    return "health/worktime/worktime";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("health:worktime:worktime")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WorktimeDO> worktimeList = worktimeService.list(query);
		int total = worktimeService.count(query);
		PageUtils pageUtils = new PageUtils(worktimeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("health:worktime:add")
	String add(){
	    return "health/worktime/add";
	}

	@GetMapping("/edit/{workId}")
	@RequiresPermissions("health:worktime:edit")
	String edit(@PathVariable("workId") Integer workId,Model model){
		WorktimeDO worktime = worktimeService.get(workId);
		model.addAttribute("worktime", worktime);
	    return "health/worktime/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("health:worktime:add")
	public R save( WorktimeDO worktime){
		Map map = new HashMap();
		map.put("workTime", worktime.getWorkTime());
		List<WorktimeDO> list = worktimeService.list(map);
		//判重
		if(list != null && list.size()>0){
			return R.error("工作日设置重复");
		}
		worktime.setCreateTime(new Date());
		worktime.setModifiedTime(new Date());
		worktime.setCreateUser(getUserId());
		worktime.setModifiedUser(getUserId());
		worktime.setStatus(1);
		if(worktimeService.save(worktime)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("health:worktime:edit")
	public R update( WorktimeDO worktime){
		worktime.setModifiedTime(new Date());
		worktime.setModifiedUser(getUserId());
		worktimeService.update(worktime);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("health:worktime:remove")
	public R remove( Integer workId){
		WorktimeDO worktime = worktimeService.get(workId);
		worktime.setStatus(0);
		worktime.setModifiedTime(new Date());
		worktime.setModifiedUser(getUserId());
		if(worktimeService.update(worktime)>0){
			return R.ok();
		}
			return R.error();
//		if(worktimeService.remove(workId)>0){
//		return R.ok();
//		}
//		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("health:worktime:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] workIds){
		worktimeService.batchRemove(workIds);
		return R.ok();
	}
	
}
