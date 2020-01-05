package com.neteye.xinzhizhu.controller;

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

import com.neteye.xinzhizhu.domain.AdPositionDO;
import com.neteye.xinzhizhu.service.AdPositionService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-08-19 12:25:16
 */
 
@Controller
@RequestMapping("/xinzhizhu/adPosition")
public class AdPositionController {
	@Autowired
	private AdPositionService adPositionService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:adPosition:adPosition")
	String AdPosition(){
	    return "xinzhizhu/adPosition/adPosition";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:adPosition:adPosition")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AdPositionDO> adPositionList = adPositionService.list(query);
		int total = adPositionService.count(query);
		PageUtils pageUtils = new PageUtils(adPositionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:adPosition:add")
	String add(){
	    return "xinzhizhu/adPosition/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xinzhizhu:adPosition:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AdPositionDO adPosition = adPositionService.get(id);
		model.addAttribute("adPosition", adPosition);
	    return "xinzhizhu/adPosition/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:adPosition:add")
	public R save( AdPositionDO adPosition){
		if(adPositionService.save(adPosition)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:adPosition:edit")
	public R update( AdPositionDO adPosition){
		adPositionService.update(adPosition);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:adPosition:remove")
	public R remove( Integer id){
		if(adPositionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:adPosition:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		adPositionService.batchRemove(ids);
		return R.ok();
	}
	
}
