package com.neteye.xinzhizhu.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.entity.*;
import com.neteye.xinzhizhu.service.*;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiPageUtils;
import com.neteye.xinzhizhu.utils.Base64;
import com.neteye.xinzhizhu.utils.CharUtil;
import com.neteye.xinzhizhu.utils.Query;
import com.neteye.xinzhizhu.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController extends ApiBaseAction {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ApiUserService userService;

    /**
     * 发表评论
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("post")
    public Object post(@LoginUser UserVo loginUser) {
    	loginUser = getUser();
        Map resultObj = new HashMap();
        //
        JSONObject jsonParam = getJsonRequest();
        Integer commentType = jsonParam.getInteger("typeId");
        Integer valueId = jsonParam.getInteger("valueId");
        String comment = jsonParam.getString("comment");
        CommentDO commentEntity = new CommentDO();
        commentEntity.setCommentType(commentType);
        commentEntity.setObjectId(valueId);
        commentEntity.setComment(comment);
        commentEntity.setStatus(0);
        //
        commentEntity.setCreatTime(new Date());
        commentEntity.setUserId(loginUser.getUserId().intValue());
        commentEntity.setComment(Base64.encode(commentEntity.getComment()));
        Integer insertId = commentService.save(commentEntity);
        //
         if (insertId > 0) {
            return toResponsObject(0, "评论添加成功", resultObj);
        } else {
            return toResponsFail("评论保存失败");
        }
    }

    /**
     */
    @ApiOperation(value = "评论数量")
    @IgnoreAuth
    @PostMapping("count")
    public Object count(Integer typeId, Integer valueId) {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("commentType", typeId);
        param.put("objectId", valueId);
        param.put("status", 0);
        Integer allCount = commentService.count(param);
        //
        resultObj.put("allCount", allCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * @param typeId
     * @param valueId
     * @param showType 选择评论的类型 0 全部， 1 只显示图片
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "选择评论类型")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(Integer typeId, Integer valueId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {
        Map<String, Object> resultObj = new HashMap();
        List<CommentDO> commentList = new ArrayList();
        Map param = new HashMap();
        param.put("commentType", typeId);
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
         //查询列表数据
        Query query = new Query(param);
        commentList = commentService.list(query);
        int total = commentService.count(query);
        ApiPageUtils pageUtil = new ApiPageUtils(commentList, total, query.getLimit(), query.getPage());

        return toResponsSuccess(pageUtil);
    }
}