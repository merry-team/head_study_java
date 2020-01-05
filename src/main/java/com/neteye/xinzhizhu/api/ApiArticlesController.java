package com.neteye.xinzhizhu.api;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.domain.CollectDO;
import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.service.ArticlesService;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.FmsService;
import com.neteye.xinzhizhu.service.VodsService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiPageUtils;
import com.neteye.xinzhizhu.utils.MultimeLength;
import com.neteye.xinzhizhu.utils.Query;
import com.neteye.xinzhizhu.utils.StringUtils;
import com.sharingcard.common.config.SharingCardConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Articles
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */


@Api(tags = "Articles接口文档")
@RestController
@RequestMapping("/api/articles")
public class ApiArticlesController extends ApiBaseAction {
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SharingCardConfig sharingCardConfig;

	@ApiOperation(value = "获取articles列表")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultObj = new HashMap();
		List<ArticlesDO> articleslist = new ArrayList();
        if (StringUtils.isNullOrEmpty(params.get("page"))) {
        	params.put("page", 1);
         }
        if (StringUtils.isNullOrEmpty(params.get("size"))) {
        	params.put("size", 10);
        	params.put("limit", 10);
         }
        params.put("limit", params.get("size"));
		// 查询列表数据
		Query query = new Query(params);
		articleslist = articlesService.list(query);
		int total = articlesService.count(query);
		ApiPageUtils pageUtil = new ApiPageUtils(articleslist, total, query.getLimit(), query.getPage());

		return toResponsSuccess(pageUtil);
	}

    /**
     * 获取文章详情
     */
    @ApiOperation(value = "获取文章详情")
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(Integer id) {
        Map resultObj = new HashMap();
        //
        ArticlesDO articles = articlesService.get(id);
        if (null == articles || !articles.getStatus().equals(1) ) {
            return toResponsObject(400, "文章不存在", "");
        }else {
        	articles.setCollectCount(null);
        	articles.setCommentCount(null);
        	Integer ReadCount =  articles.getReadCount();
        	articles.setReadCount(1);
        	articlesService.updateplus(articles);
        	articles.setReadCount(ReadCount);
        }
        resultObj.put("articles", articles);
        return toResponsSuccess(resultObj);
    }
}
