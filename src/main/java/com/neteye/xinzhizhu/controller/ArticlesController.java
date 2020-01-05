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
import com.neteye.xinzhizhu.service.ArticlesService;
import com.neteye.xinzhizhu.service.CategoryService;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 文章
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:28:15
 */
 
@Controller
@RequestMapping("/xinzhizhu/articles")
public class ArticlesController extends BaseController{
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:articles:articles")
	String Articles(){
	    return "xinzhizhu/articles/articles";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:articles:articles")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ArticlesDO> articlesList = articlesService.list(query);
		int total = articlesService.count(query);
		PageUtils pageUtils = new PageUtils(articlesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:articles:add")
	String add(Model model){
		Map<String, Object> query = new HashMap();
		query.put("categoryType", 3);
		query.put("status", 1);
		List<CategoryDO> categoryList = categoryService.list(query);
		model.addAttribute("categoryList", categoryList);

	    return "xinzhizhu/articles/add";
	}

	@GetMapping("/edit/{articleId}")
	@RequiresPermissions("xinzhizhu:articles:edit")
	String edit(@PathVariable("articleId") Integer articleId,Model model){
		ArticlesDO articles = articlesService.get(articleId);
		model.addAttribute("articles", articles);
		Map<String, Object> query = new HashMap();
		query.put("categoryType", 3);
		query.put("status", 1);
		List<CategoryDO> categoryList = categoryService.list(query);
		model.addAttribute("categoryList", categoryList);

	    return "xinzhizhu/articles/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:articles:add")
	public R save( ArticlesDO articles){
		articles.setStatus(1);
		articles.setCreatTime(new Date());
		articles.setAuthor(getName());
		articles.setCollectCount(0);
		articles.setCommentCount(0);
		articles.setReadCount(0);
		if(articlesService.save(articles)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:articles:edit")
	public R update( ArticlesDO articles){
		articlesService.update(articles);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:articles:remove")
	public R remove( Integer articleId){
		ArticlesDO articles = articlesService.get(articleId);
		articles.setStatus(0);
		//if(articlesService.remove(articleId)>0){
		if(articlesService.update(articles)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:articles:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] articleIds){
		articlesService.batchRemove(articleIds);
		return R.ok();
	}
	
}
