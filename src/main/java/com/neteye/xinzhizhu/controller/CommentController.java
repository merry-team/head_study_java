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

import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.service.CommentService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 用户评论
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:41:38
 */
 
@Controller
@RequestMapping("/xinzhizhu/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:comment:comment")
	String Comment(){
	    return "xinzhizhu/comment/comment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:comment:comment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CommentDO> commentList = commentService.list(query);
		int total = commentService.count(query);
		PageUtils pageUtils = new PageUtils(commentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:comment:add")
	String add(){
	    return "xinzhizhu/comment/add";
	}

	@GetMapping("/edit/{commentId}")
	@RequiresPermissions("xinzhizhu:comment:edit")
	String edit(@PathVariable("commentId") Integer commentId,Model model){
		CommentDO comment = commentService.get(commentId);
		model.addAttribute("comment", comment);
	    return "xinzhizhu/comment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:comment:add")
	public R save( CommentDO comment){
		if(commentService.save(comment)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:comment:edit")
	public R update( CommentDO comment){
		commentService.update(comment);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:comment:remove")
	public R remove( Integer commentId){
		if(commentService.remove(commentId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:comment:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] commentIds){
		commentService.batchRemove(commentIds);
		return R.ok();
	}
	
}
