package com.neteye.xinzhizhu.h5;

import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.domain.CategoryDO;
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
 * Category
 *
 * @author zhengbigbig
 * @email 780357902@qq.com
 * @date 2020-1-15
 */


@Api(tags = "Category接口文档")
@RestController
@RequestMapping("/h5/category")
public class H5CategoryController extends ApiBaseAction {
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
