package com.neteye.xinzhizhu.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
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
 * Category
 *
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */


@Api(tags = "Category接口文档")
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController extends ApiBaseAction {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SharingCardConfig sharingCardConfig;

    @ApiOperation(value = "获取分类列表")
    @IgnoreAuth
    @PostMapping("list")

    public Object list(@RequestParam Map<String, Object> params) {
        List<CategoryDO> categorylist = new ArrayList();
        params.put("page", StringUtils.isNullOrEmpty(params.get("page")) ? 1 : params.get("page"));
        params.put("limit", StringUtils.isNullOrEmpty(params.get("size")) ? 10 : params.get("size"));
        params.put("status", 1);
        Query query = new Query(params);
        // 查询列表数据
        categorylist = categoryService.list(query);
        int total = categoryService.count(query);
        ApiPageUtils pageUtil = new ApiPageUtils(categorylist, total, query.getLimit(), query.getPage());

        return toResponsSuccess(pageUtil);
    }

    /**
     *
     */
    @ApiOperation(value = "分类数量")
    @PostMapping("count")
    public Object count(Integer typeId, Integer valueId) {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("status", 1);
        Integer allCount = categoryService.count(param);
        //
        resultObj.put("allCount", allCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * 获取分类详情
     */
    @ApiOperation(value = "获取分类详情")
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(Integer id) {
        Map resultObj = new HashMap();
        //
        CategoryDO category = categoryService.get(id);
        if (null == category || !category.getStatus().equals(1)) {
            return toResponsObject(400, "分类不存在", "");
        }
        resultObj.put("category", category);
        return toResponsSuccess(resultObj);
    }
}
