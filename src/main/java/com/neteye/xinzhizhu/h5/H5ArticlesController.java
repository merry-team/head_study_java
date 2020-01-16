package com.neteye.xinzhizhu.h5;

import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.service.ArticlesService;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiPageUtils;
import com.neteye.xinzhizhu.utils.Query;
import com.neteye.xinzhizhu.utils.StringUtils;
import com.sharingcard.common.config.SharingCardConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Articles
 *
 * @author zhengbigbig
 * @email 780357902@qq.com
 * @date 2020-1-15
 */


@Api(tags = "Articles接口文档")
@RestController
@RequestMapping("/h5/articles")
public class H5ArticlesController extends ApiBaseAction {
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
		List<ArticlesDO> articleslist = new ArrayList();
		params.put("page", StringUtils.isNullOrEmpty(params.get("page")) ? 1 : params.get("page"));
		params.put("limit", StringUtils.isNullOrEmpty(params.get("size")) ? 10 : params.get("size"));
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
