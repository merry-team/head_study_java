package com.neteye.xinzhizhu.h5;

import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.domain.*;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.*;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiRRException;
import com.neteye.xinzhizhu.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "用户收藏")
@RestController
@RequestMapping("/h5/collect")
public class H5CollectController extends ApiBaseAction {
    @Autowired
    private CollectService collectService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private FmsService fmsService;
    @Autowired
    private VodsService vodsService;

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "获取用户收藏")
    @GetMapping("list")
    public Object listuser(@LoginUser UserVo loginUser,
                           @RequestParam Map<String, Object> params,
                           String sort, String order) {
        //类型1：文章；2：课程；3：fm；4：vod
        try {
            loginUser = getUser();
            Map param = new HashMap();
            Map<String, Object> resultObj = new HashMap<>();
            param.put("userId", getUserId());
            if (StringUtils.isNullOrEmpty(params.get("page"))) {
                param.put("page", params.get("page"));

            }
            if (StringUtils.isNullOrEmpty(params.get("size"))) {
                param.put("limit", params.get("size"));

            }
            if (StringUtils.isNullOrEmpty(params.get("collectType"))) {
                param.put("collectType", params.get("collectType"));
            } else {
                param.put("collectType", 0);
            }
            if (!StringUtils.isNullOrEmpty(sort)) {
                param.put("sort", sort);
                param.put("order", "desc");
            }
            List<UserCollectDO> collectEntities = collectService.getCollectsByUserId(param);
            int count = collectService.countByUserId(param);
            resultObj.put("collects", collectEntities);
            resultObj.put("count", count);
            return toResponsSuccess(resultObj);
        } catch (ApiRRException e) {
            return toResponsObject(401, "请先登录", "");
        }
    }

    /**
     * 添加或者删除用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @GetMapping("addordelete")
    public Object addordelete(@LoginUser UserVo loginUser, @RequestParam Map<String, Object> params) {
        Integer typeId = Integer.valueOf((String) params.get("collectType"));
        Integer valueId = Integer.valueOf((String) params.get("objectId"));
        try {
            loginUser = getUser();
        } catch (ApiRRException e) {
            return toResponsObject(401, "请先登录", "");
        }
        Map param = new HashMap();
        param.put("userId", loginUser.getUserId());
        param.put("collectType", typeId);
        param.put("objectId", valueId);
        CollectDO collectEntity = collectService.getCollectByUniqueId(param);
        // 1：文章；2：课程；3：fm；4：vod
        Integer collectRes = null;
        String handleType = "add";
        // 先判断收藏存不存在
        if (null == collectEntity) {
            CollectDO resultEntity = new CollectDO();
            resultEntity.setCreatTime(new Date());
            resultEntity.setCollectType(typeId);
            resultEntity.setObjectId(valueId);
            resultEntity.setStatus(1);
            resultEntity.setUserId(loginUser.getUserId().intValue());
            //添加收藏
            collectRes = collectService.save(resultEntity);
        } else if(collectEntity.getStatus() == -1){
            // 已存在取消过但重新收藏
            collectEntity.setStatus(1);
            collectRes = collectService.update(collectEntity);
        } else {
            //取消收藏
            collectEntity.setStatus(-1);
            collectRes = collectService.update(collectEntity);
            //1：文章；2：课程；3：fm；4：vod
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap();
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return toResponsFail("操作失败");
    }

    /*
     * * 阅读
     */
    @ApiOperation(value = "阅读记录")
    @GetMapping("read")
    public Object read(Integer typeId, Integer objectId) {
        CollectDO collectEntity = new CollectDO();
        collectEntity.setCollectType(typeId);
        collectEntity.setObjectId(objectId);
        collectService.read(collectEntity);
        return toResponsSuccess("操作成功");
    }
}