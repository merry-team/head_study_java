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
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.domain.CollectDO;
import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ArticlesService;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.CourseService;
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
@RequestMapping("/api/course")
public class ApiCourseController extends ApiBaseAction {
	@Autowired
	private CourseService courseService;
	@Autowired
	private SharingCardConfig sharingCardConfig;

	@ApiOperation(value = "获取courses列表")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultObj = new HashMap();
		List<CourseDO> courselist = new ArrayList();
        if (StringUtils.isNullOrEmpty(params.get("page"))) {
        	params.put("page", 1);
         }
        if (StringUtils.isNullOrEmpty(params.get("size"))) {
        	params.put("size", 10);
         }
        params.put("limit", params.get("size"));
		// 查询列表数据
		Query query = new Query(params);
		courselist = courseService.list(query);
		int total = courseService.count(query);
		ApiPageUtils pageUtil = new ApiPageUtils(courselist, total, query.getLimit(), query.getPage());

		return toResponsSuccess(pageUtil);
	}

    /**
     * 获取课程详情
     */
    @ApiOperation(value = "获取课程详情")
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(Integer id) {
        Map resultObj = new HashMap();
        //
        CourseDO course = courseService.get(id);
        if (null == course || !course.getStatus().equals(1) ) {
            return toResponsObject(400, "课程不存在", "");
        }else {
        	course.setCollectCount(null);
        	course.setBuyCount(null);
        	Integer ReadCount =  course.getReadCount();
        	course.setReadCount(1);
        	courseService.updateplus(course);
        	course.setReadCount(ReadCount);
        }
        resultObj.put("course", course);
        return toResponsSuccess(resultObj);
    }
}
