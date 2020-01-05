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

import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.CourseService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:18:58
 */
 
@Controller
@RequestMapping("/xinzhizhu/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CategoryService categoryService;
	@GetMapping()
	@RequiresPermissions("xinzhizhu:course:course")
	String Course(){
	    return "xinzhizhu/course/course";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:course:course")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseDO> courseList = courseService.list(query);
		int total = courseService.count(query);
		PageUtils pageUtils = new PageUtils(courseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:course:add")
	String add(Model model){
		Map<String, Object> query = new HashMap();
		query.put("categoryType", 1);
		query.put("status", 1);
		List<CategoryDO> categoryList = categoryService.list(query);
		model.addAttribute("categoryList", categoryList);
	    return "xinzhizhu/course/add";
	}

	@GetMapping("/edit/{courseId}")
	@RequiresPermissions("xinzhizhu:course:edit")
	String edit(@PathVariable("courseId") Integer courseId,Model model){
		Map<String, Object> query = new HashMap();
		query.put("categoryType", 1);
		query.put("status", 1);
		List<CategoryDO> categoryList = categoryService.list(query);
		model.addAttribute("categoryList", categoryList);
		CourseDO course = courseService.get(courseId);
		model.addAttribute("course", course);
	    return "xinzhizhu/course/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:course:add")
	public R save( CourseDO course){
		course.setStatus(1);
		course.setBuyCount(0);
		course.setCollectCount(0);
		course.setCreatetime(new Date());
		course.setReadCount(0);
		course.setType(0);
		if(courseService.save(course)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:course:edit")
	public R update( CourseDO course){
		courseService.update(course);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:course:remove")
	public R remove( Integer courseId){
//		if(courseService.remove(courseId)>0){
//		return R.ok();
//		}
//		return R.error();
		CourseDO ourse= courseService.get(courseId);
		ourse.setStatus(0);
		if(courseService.update(ourse)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:course:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] courseIds){
		courseService.batchRemove(courseIds);
		return R.ok();
	}
	
}
