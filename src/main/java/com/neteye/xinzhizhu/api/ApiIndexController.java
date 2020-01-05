package com.neteye.xinzhizhu.api;

import com.github.pagehelper.PageHelper;
import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.domain.AdInfoDO;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.entity.*;
import com.neteye.xinzhizhu.service.*;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
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
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "首页接口文档")
@RestController
@RequestMapping("/api/index")
public class ApiIndexController extends ApiBaseAction {
    @Autowired
    private AdInfoService adInfoService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SharingCardConfig sharingCardConfig;
	@Autowired
	private VodsService vodsService;
	@Autowired
	private FmsService fmsService;
	@Autowired
	private ArticlesService articlesService;
    /**
     * 测试
     */
    @IgnoreAuth
    @PostMapping(value = "test")
    public Object test() {
        return toResponsMsgSuccess("请求成功yyy");
    }

    /**
     * app首页
     */
    @ApiOperation(value = "首页")
    @IgnoreAuth
    @PostMapping(value = "index")
    public Object index() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //banner
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("adPositionId", 1);
        param.put("enabled", 1);
        List<AdInfoDO> banner = adInfoService.list(param);
        resultObj.put("banner", banner);
        //分类8个
        param = new HashMap<String, Object>();
        param.put("status", 1);
        param.put("sort", "createtime");
        param.put("order", "desc");
        param.put("offset",0);
        param.put("limit", 8);
        List<CategoryDO>  category = categoryService.list(param);
        resultObj.put("category", category);
        //最新多课课程
        param = new HashMap<String, Object>();
        param.put("status", 1);
        param.put("type", "0"); 
       // param.put("sort", "read_count");
        param.put("sort", "course_id");         
        param.put("order", "desc");
        param.put("offset", 0);
        param.put("limit", 6);
        List<CourseDO>  course = courseService.list(param);
        resultObj.put("course", course);
        //热门视频
        param = new HashMap<String, Object>();
        //param.put("status", 1);
        param.put("sort", "read_count");
        param.put("order", "desc");
        param.put("offset", 0);
        param.put("limit", 6);
        List<VodsDO>  vods = vodsService.listsingle(param);
        resultObj.put("vods", vods);
        //热门FM
        param = new HashMap<String, Object>();
        //param.put("status", 1);
        param.put("sort", "read_count");
        param.put("order", "desc");
        param.put("offset", 0);
        param.put("limit", 6);
        List<FmsDO>  fms = fmsService.list(param);
        resultObj.put("fms", fms);
        //热门文章
        param = new HashMap<String, Object>();
        param.put("status", 1);
        param.put("sort", "read_count");
        param.put("order", "desc");
        param.put("offset", 0);
        param.put("limit", 6);
        List<ArticlesDO>  articles = articlesService.list(param);
        resultObj.put("articles", articles);
        return toResponsSuccess(resultObj);
    }


    /**
     * app首页
     */
    @ApiOperation(value = "广告")
    @IgnoreAuth
    @PostMapping(value = "ad")
    public Object ad(@RequestParam Map<String, Object> params) {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("enabled", 1);
        param.put("adPositionId", params.get("id"));
        List<AdInfoDO> banner = adInfoService.list(param);
        resultObj.put("banner", banner);	
    	 return toResponsSuccess(resultObj);
    }
}