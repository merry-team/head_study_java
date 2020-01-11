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
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.service.CourseService;
import com.neteye.xinzhizhu.service.VodsService;
import com.neteye.xinzhizhu.utils.MultimeLength;
import com.sharingcard.common.config.SharingCardConfig;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * vod
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:33:25
 */
 
@Controller
@RequestMapping("/xinzhizhu/vods")
public class VodsController extends BaseController{
	@Autowired
	private VodsService vodsService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SharingCardConfig sharingCardConfig;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:vods:vods")
	String Vods(){
	    return "xinzhizhu/vods/vods";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:vods:vods")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		System.out.println(params);
		Query query = new Query(params);
		List<VodsDO> vodsList = vodsService.list(query);
		int total = vodsService.count(query);
		PageUtils pageUtils = new PageUtils(vodsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:vods:add")
	String  add(Model model){
		Map<String, Object> query = new HashMap();
		query.put("status", 1);
		query.put("type", 0);
		List<CourseDO> courseList = courseService.list(query);
		model.addAttribute("courseList", courseList);
	    return "xinzhizhu/vods/add";
	}

	@GetMapping("/edit/{vodId}")
	@RequiresPermissions("xinzhizhu:vods:edit")
	String edit(@PathVariable("vodId") Integer vodId,Model model){
		VodsDO vods = vodsService.get(vodId);
		model.addAttribute("vods", vods);
		
		CourseDO course = courseService.get(vods.getCourseId());
		
		Map<String, Object> query = new HashMap();
		query.put("status", 1);
		query.put("type", course.getType());
		List<CourseDO> courseList = courseService.list(query);
		model.addAttribute("courseList", courseList);
	    return "xinzhizhu/vods/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:vods:add")
	public R save( VodsDO vods){
		vods.setStatus(1);
		vods.setAuthor( getName());
		vods.setCollectCount(0);
		vods.setCommentCount(0);
		vods.setCreatTime(new Date());
		vods.setReadCount(0);
		
		String fileName =sharingCardConfig.getUploadPath() + vods.getFileUrl().replace("/files/", "");
		long timelength = MultimeLength.getMultimeLength(fileName);
		vods.setVodTime(Integer.valueOf(timelength+""));
		
		if(vodsService.save(vods)>0){
			//如果是同时增加单课
			if(vods.getChoice()!=null && vods.getChoice().equals(1)) {
				//增加课程
				CourseDO oldcourse = courseService.get(vods.getCourseId());
				CourseDO course = new CourseDO();
				course.setCategoryId(oldcourse.getCategoryId());
				course.setCourseName(vods.getVodTitle());
				course.setContent(vods.getVodTitle());
				course.setImageUrl(vods.getImageUrl());
				course.setCoursePrice(vods.getCoursePrice());
				course.setStatus(1);
				course.setBuyCount(0);
				course.setCollectCount(0);
				course.setCreatetime(new Date());
				course.setReadCount(0);
				course.setType(1); //单课
				
				if(courseService.save(course)<=0){
					return R.error();
				}
				
				//增加视频
				VodsDO newvods = new VodsDO();
				newvods.setVodTitle(vods.getVodTitle());
				newvods.setContent(vods.getContent());
				newvods.setCourseId(course.getCourseId());
				newvods.setVodTime(vods.getVodTime());
				newvods.setFileUrl(vods.getFileUrl());
				newvods.setImageUrl(vods.getImageUrl());
				
				newvods.setStatus(1);
				newvods.setAuthor(getName());
				newvods.setCollectCount(0);
				newvods.setCommentCount(0);
				newvods.setCreatTime(new Date());
				newvods.setReadCount(0);
				if(vodsService.save(newvods)>0){
					return R.ok();
				}
				return R.error();
			}	
			return R.ok();
			
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:vods:edit")
	public R update( VodsDO vods){
		String fileName =sharingCardConfig.getUploadPath() + vods.getFileUrl().replace("/files/", "");
		long timelength = MultimeLength.getMultimeLength(fileName);
		vods.setVodTime(Integer.valueOf(timelength+""));
		vodsService.update(vods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:vods:remove")
	public R remove( Integer vodId){
//		if(vodsService.remove(vodId)>0){
//		return R.ok();
//		}
//		return R.error();
		VodsDO vods= vodsService.get(vodId);
		vods.setStatus(0);
		if(vodsService.update(vods)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:vods:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] vodIds){
		vodsService.batchRemove(vodIds);
		return R.ok();
	}
	
}
