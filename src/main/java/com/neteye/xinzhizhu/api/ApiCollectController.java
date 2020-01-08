package com.neteye.xinzhizhu.api;

import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.domain.ArticlesDO;
import com.neteye.xinzhizhu.domain.CollectDO;
import com.neteye.xinzhizhu.domain.CourseDO;
import com.neteye.xinzhizhu.domain.FmsDO;
import com.neteye.xinzhizhu.domain.VodsDO;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ArticlesService;
import com.neteye.xinzhizhu.service.CollectService;
import com.neteye.xinzhizhu.service.CourseService;
import com.neteye.xinzhizhu.service.FmsService;
import com.neteye.xinzhizhu.service.VodsService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/collect")
public class ApiCollectController extends ApiBaseAction {
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
    @PostMapping("listuser")
    public Object listuser(@LoginUser UserVo loginUser, Integer typeId) {
    	loginUser = getUser();
        Map param = new HashMap();
        param.put("userId", getUserId());
        param.put("collectType", typeId);
        List<CollectDO> collectEntities = collectService.list(param);
        return toResponsSuccess(collectEntities);
    }

    /**
       * 获取收藏
     */
    @ApiOperation(value = "获取收藏")
    @PostMapping("list")
    @IgnoreAuth
    public Object list(Integer typeId, Integer valueId, Integer showType,
            @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
            String sort, String order) {
        Map param = new HashMap();
        param.put("collecttId", typeId);
        param.put("objectId", valueId);
        param.put("page", page);
        param.put("limit", size);
        if (StringUtils.isNullOrEmpty(sort)) {
        	param.put("sort", "creat_time");
            param.put("order", "desc");
        } else {
        	param.put("sort", sort);
            param.put("order", "desc");
        }
        List<CollectDO> collectEntities = collectService.list(param);
        return toResponsSuccess(collectEntities);
    }
    /**
      *  	添加或者删除用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @PostMapping("addordelete")
    public Object addordelete(@LoginUser UserVo loginUser,@RequestParam Map<String, Object> params) {
//        JSONObject jsonParam = getJsonRequest();
//        Integer typeId = jsonParam.getInteger("typeId");
//        Integer valueId = jsonParam.getInteger("valueId");
      Integer typeId =Integer.valueOf((String) params.get("typeId"));
      Integer valueId =Integer.valueOf((String) params.get("valueId"));
      loginUser = getUser();
        Map param = new HashMap();
        param.put("userId", loginUser.getUserId());
        param.put("collectType", typeId);
        param.put("objectId", valueId);
        List<CollectDO> collectEntities = collectService.list(param);
        //
        Integer collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
        	CollectDO collectEntity = new CollectDO();
            collectEntity.setCreatTime(new Date());
            collectEntity.setCollectType(typeId);
            collectEntity.setObjectId(valueId);
            collectEntity.setStatus(1);
            collectEntity.setUserId(loginUser.getUserId().intValue());
            //添加收藏
            collectRes = collectService.save(collectEntity);
            //修改明细
            //1：文章；2：课程；3：fm；4：vod
            if(typeId.equals(1)) {
            	ArticlesDO articles = new ArticlesDO();
            	articles.setArticleId(valueId);
            	articles.setCollectCount(1);
            	articlesService.updateplus(articles);
            }else if(typeId.equals(2)) {
            	CourseDO course = new CourseDO();
            	course.setCategoryId(valueId);
            	course.setCollectCount(1);
            	courseService.updateplus(course);
            }else if(typeId.equals(3)) {
            	FmsDO fms =  new FmsDO();
            	fms.setFmId(valueId);
            	fms.setCollectCount(1);
            	fmsService.updateplus(fms);
            }else if(typeId.equals(4)) {
            	VodsDO vods =  new VodsDO();
            	vods.setVodId(valueId);
            	vods.setCollectCount(1);
            	vodsService.updateplus(vods);     	
            }
        } else {
            //取消收藏
        	CollectDO collectEntity = collectEntities.get(0);
        	collectEntity.setStatus(-1);
        	collectRes = collectService.update(collectEntity);
            //collectRes = collectService.remove(collectEntities.get(0).getCollecttId());
            //1：文章；2：课程；3：fm；4：vod
            if(typeId.equals(1)) {
            	ArticlesDO articles = new ArticlesDO();
            	articles.setArticleId(valueId);
            	articles.setCollectCount(-1);
            	articlesService.updateplus(articles);
            }else if(typeId.equals(2)) {
            	CourseDO course = new CourseDO();
            	course.setCategoryId(valueId);
            	course.setCollectCount(-1);
            	courseService.updateplus(course);
            }else if(typeId.equals(3)) {
            	FmsDO fms =  new FmsDO();
            	fms.setFmId(valueId);
            	fms.setCollectCount(-1);
            	fmsService.updateplus(fms);
            }else if(typeId.equals(4)) {
            	VodsDO vods =  new VodsDO();
            	vods.setVodId(valueId);
            	vods.setCollectCount(-1);
            	vodsService.updateplus(vods);     	
            }
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap();
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return toResponsFail("操作失败");
    }
    
    /**
     * 获取收藏数
     */
    @ApiOperation(value = "获取收藏数")
    @PostMapping("count")
    public Object count(Integer typeId, Integer valueId) {

        Map param = new HashMap();
        param.put("objectId", valueId);
        param.put("collectType", typeId);
        param.put("status", 1);
        Integer allCount = collectService.count(param);
        param.clear();
        param.put("allCount", allCount);
        return toResponsSuccess(param);
    }

     /*
      * * 阅读
     */
    @ApiOperation(value = "阅读记录")
    @PostMapping("read")
    public Object read(Integer typeId, Integer valueId) {
    	CollectDO collectEntity = new CollectDO();
        collectEntity.setCollectType(typeId);
        collectEntity.setObjectId(valueId);
        collectService.read(collectEntity);
        return toResponsSuccess("操作成功");
    }
}