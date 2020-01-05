package com.neteye.xinzhizhu.controller;

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

import com.neteye.xinzhizhu.domain.AdInfoDO;
import com.neteye.xinzhizhu.domain.AdPositionDO;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.service.AdInfoService;
import com.neteye.xinzhizhu.service.AdPositionService;
import com.neteye.xinzhizhu.utils.DateUtils;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-08-19 16:05:39
 */
 
@Controller
@RequestMapping("/xinzhizhu/adInfo")
public class AdInfoController {
	@Autowired
	private AdInfoService adInfoService;
	@Autowired
	private AdPositionService adPositionService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:adInfo:adInfo")
	String AdInfo(){
	    return "xinzhizhu/adInfo/adInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:adInfo:adInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AdInfoDO> adInfoList = adInfoService.list(query);
		int total = adInfoService.count(query);
		PageUtils pageUtils = new PageUtils(adInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:adInfo:add")
	String add(Model model){
		Map<String, Object> query = new HashMap();
		List<AdPositionDO> adpostionList = adPositionService.list(query);
		model.addAttribute("adpostionList", adpostionList);
	    return "xinzhizhu/adInfo/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("xinzhizhu:adInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		Map<String, Object> query = new HashMap();
		List<AdPositionDO> adpostionList = adPositionService.list(query);
		model.addAttribute("adpostionList", adpostionList);
		AdInfoDO adInfo = adInfoService.get(id);
		model.addAttribute("adInfo", adInfo);
		model.addAttribute("EndTime", DateUtils.format(adInfo.getEndTime()));
	    return "xinzhizhu/adInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:adInfo:add")
	public R save( AdInfoDO adInfo){
		adInfo.setMediaType(1);
		adInfo.setEnabled(1);
		if(adInfoService.save(adInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:adInfo:edit")
	public R update( AdInfoDO adInfo){
		adInfoService.update(adInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:adInfo:remove")
	public R remove( Integer id){
//		if(adInfoService.remove(id)>0){
//		return R.ok();
//		}
//		return R.error();
		AdInfoDO adInfo= adInfoService.get(id);
		adInfo.setEnabled(0);;
		if(adInfoService.update(adInfo)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:adInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		adInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
