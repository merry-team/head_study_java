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
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.FmsService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiPageUtils;
import com.neteye.xinzhizhu.utils.MultimeLength;
import com.neteye.xinzhizhu.utils.Query;
import com.neteye.xinzhizhu.utils.StringUtils;
import com.sharingcard.common.config.SharingCardConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * fm
 *
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */

@Api(tags = "fms接口文档")
@RestController
@RequestMapping("/api/fms")
public class ApiFmsController extends ApiBaseAction {
	@Autowired
	private FmsService fmsService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SharingCardConfig sharingCardConfig;

	@ApiOperation(value = "获取FM列表")
	@IgnoreAuth
	@PostMapping("list")
	public Object list(@RequestParam Map<String, Object> params) {
		List<FmsDO> fmsList = new ArrayList();
		params.put("page", StringUtils.isNullOrEmpty(params.get("page")) ? 1 : params.get("page"));
		params.put("limit", StringUtils.isNullOrEmpty(params.get("size")) ? 10 : params.get("size"));
		// 查询列表数据
		Query query = new Query(params);
		fmsList = fmsService.list(query);
		int total = fmsService.count(query);
		ApiPageUtils pageUtil = new ApiPageUtils(fmsList, total, query.getLimit(), query.getPage());

		return toResponsSuccess(pageUtil);
	}

	@ApiOperation(value = "获取FM列表")
	@IgnoreAuth
	@PostMapping("get")
	public Object get(@RequestParam Map<String, Object> params) {
		List<FmsDO> fmsList = new ArrayList();
		params.put("page", StringUtils.isNullOrEmpty(params.get("page")) ? 1 : params.get("page"));
		params.put("limit", StringUtils.isNullOrEmpty(params.get("size")) ? 10 : params.get("size"));
		// 查询列表数据
		Query query = new Query(params);
		fmsList = fmsService.list(query);
		int total = fmsService.count(query);
		ApiPageUtils pageUtil = new ApiPageUtils(fmsList, total, query.getLimit(), query.getPage());

		return toResponsSuccess(pageUtil);
	}

    /**
     * 获取FM详情
     */
    @ApiOperation(value = "获取文章详情")
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(Integer id) {
        Map resultObj = new HashMap();
        //
        FmsDO fms = fmsService.get(id);
        if (null == fms || !fms.getStatus().equals(1) ) {
            return toResponsObject(400, "FM不存在", "");
        }else {
        	fms.setCollectCount(null);
        	fms.setCommentCount(null);
        	Integer ReadCount =  fms.getReadCount();
        	fms.setReadCount(1);
        	fmsService.updateplus(fms);
        	fms.setReadCount(ReadCount);
        }
        resultObj.put("fms", fms);
        return toResponsSuccess(resultObj);
    }

//	@ResponseBody
//	@GetMapping("/list")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//      Query query = new Query(params);
//		List<FmsDO> fmsList = fmsService.list(query);
//		int total = fmsService.count(query);
//		PageUtils pageUtils = new PageUtils(fmsList, total);
//		return pageUtils;
//	}

}
