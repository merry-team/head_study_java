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

import com.neteye.xinzhizhu.domain.CollectDO;
import com.neteye.xinzhizhu.service.CollectService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 用户收藏
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-16 22:28:58
 */
 
@Controller
@RequestMapping("/xinzhizhu/collect")
public class CollectController {
	@Autowired
	private CollectService collectService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:collect:collect")
	String Collect(){
	    return "xinzhizhu/collect/collect";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:collect:collect")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CollectDO> collectList = collectService.list(query);
		int total = collectService.count(query);
		PageUtils pageUtils = new PageUtils(collectList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:collect:add")
	String add(){
	    return "xinzhizhu/collect/add";
	}

	@GetMapping("/edit/{collecttId}")
	@RequiresPermissions("xinzhizhu:collect:edit")
	String edit(@PathVariable("collecttId") Integer collecttId,Model model){
		CollectDO collect = collectService.get(collecttId);
		model.addAttribute("collect", collect);
	    return "xinzhizhu/collect/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:collect:add")
	public R save( CollectDO collect){
		if(collectService.save(collect)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:collect:edit")
	public R update( CollectDO collect){
		collectService.update(collect);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:collect:remove")
	public R remove( Integer collecttId){
		if(collectService.remove(collecttId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:collect:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] collecttIds){
		collectService.batchRemove(collecttIds);
		return R.ok();
	}
	
}
