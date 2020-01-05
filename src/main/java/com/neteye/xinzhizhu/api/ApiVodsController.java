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
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.domain.CollectDO;
import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.entity.OrderVo;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ApiOrderGoodsService;
import com.neteye.xinzhizhu.service.ApiOrderService;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.FmsService;
import com.neteye.xinzhizhu.service.VodsService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiPageUtils;
import com.neteye.xinzhizhu.utils.MultimeLength;
import com.neteye.xinzhizhu.utils.Query;
import com.neteye.xinzhizhu.utils.StringUtils;
import com.sharingcard.common.config.SharingCardConfig;
import com.sharingcard.common.utils.FileType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * fm
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */


@Api(tags = "vods接口文档")
@RestController
@RequestMapping("/api/vods")
public class ApiVodsController extends ApiBaseAction {
	@Autowired
	private VodsService vodsService;
	@Autowired
	private CategoryService categoryService;	
	@Autowired
	private ApiOrderService apiOrderService;
	@Autowired
	private SharingCardConfig sharingCardConfig;

	@ApiOperation(value = "获取vods列表")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(@RequestParam Map<String, Object> params) {
		Map<String, Object> resultObj = new HashMap();
		List<VodsDO> vodslist = new ArrayList();
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
		vodslist = vodsService.list(query);
		int total = vodsService.count(query);
		ApiPageUtils pageUtil = new ApiPageUtils(vodslist, total, query.getLimit(), query.getPage());

		return toResponsSuccess(pageUtil);
	}

    /**
     * 获取视频详情
     */
    @ApiOperation(value = "获取视频详情")
    @PostMapping("detail")
    public Object detail(@LoginUser UserVo loginUser,Integer id) {
    	loginUser = getUser();
        Map resultObj = new HashMap();
        //
        VodsDO vods = vodsService.get(id);
        if (null == vods || !vods.getStatus().equals(1) ) {
            return toResponsObject(400, "视频不存在", "");
        }else {//更新访问量
        	Integer ReadCount =  vods.getReadCount();
        	vods.setReadCount(1);
        	vods.setCollectCount(null);
        	vods.setCommentCount(null);
        	vodsService.updateplus(vods);
        	vods.setReadCount(ReadCount);
        }
        //查询用户是否订购
        Map<String, Object> map =new HashMap();
        map.put("pay_status", 2);
        map.put("user_id", loginUser.getUserId());
        map.put("parent_id", vods.getCourseId()); //视频对于的课程id
        List<OrderVo> orderlist =  apiOrderService.queryList(map);
        //判断是否购买
        if(orderlist != null && orderlist.size() > 0 ) {
        	resultObj.put("BuyFlag", 1); //已经购买
        }else {
        	resultObj.put("BuyFlag", 0);//没有购买
        }
        //判断文件类型
        String fileName =sharingCardConfig.getUploadPath() + vods.getFileUrl().replace("/files/", "");
        resultObj.put("FileType", FileType.fileType(fileName));
        resultObj.put("vods", vods);
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
