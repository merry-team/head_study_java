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
import com.neteye.health.service.CompanyService;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 企业信息表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-10 18:00:34
 */
 
@Controller
@RequestMapping("/health/company")
public class CompanyController extends BaseController{
	@Autowired
	private CompanyService companyService;
	
	@GetMapping()
	@RequiresPermissions("health:company:company")
	String Company(){
	    return "health/company/company";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("health:company:company")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CompanyDO> companyList = companyService.list(query);
		int total = companyService.count(query);
		PageUtils pageUtils = new PageUtils(companyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("health:company:add")
	String add(){
	    return "health/company/add";
	}

	@GetMapping("/edit/{companyId}")
	@RequiresPermissions("health:company:edit")
	String edit(@PathVariable("companyId") Integer companyId,Model model){
		CompanyDO company = companyService.get(companyId);
		model.addAttribute("company", company);
	    return "health/company/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("health:company:add")
	public R save( CompanyDO company){
		Map map = new HashMap();
		map.put("companyName", company.getCompanyName());
		List<CompanyDO> list = companyService.list(map);
		//判重
		if(list != null && list.size()>0){
			return R.error("公司名称重复");
		}
		company.setCreateTime(new Date());
		company.setModifiedTime(new Date());
		company.setCreateUser(getUserId());
		company.setModifiedUser(getUserId());
		company.setStatus(1);
		if(companyService.save(company)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("health:company:edit")
	public R update( CompanyDO company){
		company.setModifiedUser(getUserId());
		company.setModifiedTime(new Date());
		companyService.update(company);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("health:company:remove")
	public R remove( Integer companyId){
//		if(companyService.remove(companyId)>0){
//		return R.ok();
//		}
//		return R.error();
		CompanyDO company = companyService.get(companyId);
		company.setModifiedTime(new Date());
		company.setModifiedUser(getUserId());
		company.setStatus(0);
		if(companyService.update(company)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("health:company:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] companyIds){
		companyService.batchRemove(companyIds);
		return R.ok();
	}
	
}
