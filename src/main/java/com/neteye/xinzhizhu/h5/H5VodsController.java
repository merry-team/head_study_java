package com.neteye.xinzhizhu.h5;

import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.entity.OrderVo;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ApiOrderService;
import com.neteye.xinzhizhu.service.CategoryService;
import com.neteye.xinzhizhu.service.VodsService;
import com.neteye.xinzhizhu.utils.*;
import com.sharingcard.common.config.SharingCardConfig;
import com.sharingcard.common.utils.FileType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fm
 *
 * @author zhengbigbig
 * @email 780357902@qq.com
 * @date 2020-1-15
 */


@Api(tags = "vods接口文档")
@RestController
@RequestMapping("/h5/vods")
public class H5VodsController extends ApiBaseAction {
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
		List<VodsDO> vodslist = new ArrayList();
        params.put("page", StringUtils.isNullOrEmpty(params.get("page")) ? 1 : params.get("page"));
        params.put("limit", StringUtils.isNullOrEmpty(params.get("size")) ? 10 : params.get("size"));
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
    @ResponseBody
    public Object detail(@LoginUser UserVo loginUser,Integer id) {
        try {
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
        } catch (ApiRRException e){
            return toResponsObject(401, "请先登录", "");
        }

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
