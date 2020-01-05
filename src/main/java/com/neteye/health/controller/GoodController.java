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
import com.neteye.health.domain.GoodDO;
import com.neteye.health.service.GoodService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-12 20:29:04
 */
 
@Controller
@RequestMapping("/health/good")
public class GoodController {
	@Autowired
	private GoodService goodService;
	
	@GetMapping()
	@RequiresPermissions("health:good:good")
	String Good(){
	    return "health/good/good";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("health:good:good")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GoodDO> goodList = goodService.list(query);
		int total = goodService.count(query);
		PageUtils pageUtils = new PageUtils(goodList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("health:good:add")
	String add(){
	    return "health/good/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("health:good:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GoodDO good = goodService.get(id);
		model.addAttribute("good", good);
	    return "health/good/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("health:good:add")
	public R save( GoodDO good){
		Map map = new HashMap();
		map.put("goodName", good.getGoodName());
		List<GoodDO> list = goodService.list(map);
		//判重
		if(list != null && list.size()>0){
			return R.error("产品名称重复");
		}
		
		good.setIsOnSale(1); //上架
		good.setAddTime(new Date());
		if(goodService.save(good)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("health:good:edit")
	public R update( GoodDO good){
		goodService.update(good);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("health:good:remove")
	public R remove( Integer id){
//		if(goodService.remove(id)>0){
//		return R.ok();
//		}
//		return R.error();
		GoodDO good = goodService.get(id);
		good.setIsOnSale(0);
		if(goodService.update(good)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("health:good:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		goodService.batchRemove(ids);
		return R.ok();
	}
	
}
