package com.neteye.xinzhizhu.controller;

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

import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.FmsService;
import com.neteye.xinzhizhu.utils.MultimeLength;
import com.sharingcard.common.config.SharingCardConfig;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * fm 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */
 
@Controller
@RequestMapping("/xinzhizhu/fms")
public class FmsController extends BaseController{
	@Autowired
	private FmsService fmsService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SharingCardConfig sharingCardConfig;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:fms:fms")
	String Fms(){
	    return "xinzhizhu/fms/fms";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:fms:fms")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FmsDO> fmsList = fmsService.list(query);
		int total = fmsService.count(query);
		PageUtils pageUtils = new PageUtils(fmsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:fms:add")
	String  add(Model model){
		Map<String, Object> query = new HashMap();
		query.put("categoryType", 2);
		query.put("status", 1);
		List<CategoryDO> categoryList = categoryService.list(query);
		model.addAttribute("categoryList", categoryList);
	    return "xinzhizhu/fms/add";
	}

	@GetMapping("/edit/{fmId}")
	@RequiresPermissions("xinzhizhu:fms:edit")
	String edit(@PathVariable("fmId") Integer fmId,Model model){
		FmsDO fms = fmsService.get(fmId);
		model.addAttribute("fms", fms);
		Map<String, Object> query = new HashMap();
		query.put("categoryType", 2);
		query.put("status", 1);
		List<CategoryDO> categoryList = categoryService.list(query);
		model.addAttribute("categoryList", categoryList);
	    return "xinzhizhu/fms/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:fms:add")
	public R save( FmsDO fms){
		
		fms.setStatus(1);
		fms.setAuthor( getName());
		fms.setCollectCount(0);
		fms.setCommentCount(0);
		fms.setCreatTime(new Date());
		fms.setReadCount(0);
		
		String fileName =sharingCardConfig.getUploadPath() + fms.getFileUrl().replace("/files/", "");
		long timelength = MultimeLength.getMultimeLength(fileName);
		fms.setFmTime(Integer.valueOf(timelength+""));
				
		if(fmsService.save(fms)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:fms:edit")
	public R update( FmsDO fms){
		String fileName =sharingCardConfig.getUploadPath() + fms.getFileUrl().replace("/files/", "");
		long timelength = MultimeLength.getMultimeLength(fileName);
		fms.setFmTime(Integer.valueOf(timelength+""));
		fmsService.update(fms);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:fms:remove")
	public R remove( Integer fmId){
//		if(fmsService.remove(fmId)>0){
//		return R.ok();
//		}
//		return R.error();
		FmsDO fms= fmsService.get(fmId);
		fms.setStatus(0);
		if(fmsService.update(fms)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:fms:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] fmIds){
		fmsService.batchRemove(fmIds);
		return R.ok();
	}
	
}
