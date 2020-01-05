package com.neteye.xinzhizhu.controller;

import java.util.Date;
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
import com.neteye.xinzhizhu.service.CategoryService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:13:35
 */
 
@Controller
@RequestMapping("/xinzhizhu/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:category:category")
	String Category(){
	    return "xinzhizhu/category/category";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:category:category")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("status", 1);
        Query query = new Query(params);
		List<CategoryDO> categoryList = categoryService.list(query);
		int total = categoryService.count(query);
		PageUtils pageUtils = new PageUtils(categoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:category:add")
	String add(){
	    return "xinzhizhu/category/add";
	}

	@GetMapping("/edit/{categoryId}")
	@RequiresPermissions("xinzhizhu:category:edit")
	String edit(@PathVariable("categoryId") Integer categoryId,Model model){
		CategoryDO category = categoryService.get(categoryId);
		model.addAttribute("category", category);
	    return "xinzhizhu/category/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:category:add")
	public R save( CategoryDO category){
		category.setCreatetime(new Date());
		category.setStatus(1);
		if(categoryService.save(category)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:category:edit")
	public R update( CategoryDO category){
		categoryService.update(category);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:category:remove")
	public R remove( Integer categoryId){
//		if(categoryService.remove(categoryId)>0){
//		return R.ok();
//		}
		CategoryDO category = categoryService.get(categoryId);
		category.setStatus(0);
		if(categoryService.update(category)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:category:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] categoryIds){
		categoryService.batchRemove(categoryIds);
		return R.ok();
	}
	
}
