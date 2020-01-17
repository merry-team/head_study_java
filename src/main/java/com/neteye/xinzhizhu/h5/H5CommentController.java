package com.neteye.xinzhizhu.h5;

import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.domain.CommentDO;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ApiUserService;
import com.neteye.xinzhizhu.service.CommentService;
import com.neteye.xinzhizhu.utils.Base64;
import com.neteye.xinzhizhu.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.neteye.xinzhizhu.utils.Base64.decode;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "评论功能")
@RestController
@RequestMapping("/h5/comment")
public class H5CommentController extends ApiBaseAction {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ApiUserService userService;

    /**
     * 发表评论
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("post")
    public Object post(@LoginUser UserVo loginUser, Integer typeId,
                       Integer objectId, @RequestBody Map<String,Object> request) {
        try {
            loginUser = getUser();
        } catch (ApiRRException e) {
            return toResponsObject(401, "请先登录", "");
        }

        CommentDO commentEntity = new CommentDO();
        commentEntity.setCommentType(typeId);
        commentEntity.setObjectId(objectId);
        commentEntity.setComment((String) request.get("comment"));
        commentEntity.setStatus(1);
        //
        commentEntity.setCreatTime(new Date());
        commentEntity.setUserId(loginUser.getUserId().intValue());
        commentEntity.setComment(Base64.encode(commentEntity.getComment()));
        Integer insertId = commentService.save(commentEntity);
        //
         if (insertId > 0) {
            return toResponsObject(0, "评论成功",null);
        } else {
            return toResponsFail("评论失败");
        }
    }


    /**
     * @param typeId
     * @param objectId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "选择评论类型")
    @IgnoreAuth
    @GetMapping("list")
    public Object list(Integer typeId, Integer objectId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {
        Map param = new HashMap();
        param.put("commentType", typeId);
        param.put("objectId", objectId);
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
        List<CommentDO> commentList = commentService.list(query);
        List<CommentDO> result = commentList.stream().map(commentDO -> {
            commentDO.setComment(decode(commentDO.getComment()));
            return commentDO;
        }).collect(Collectors.toList());
        int total = commentService.count(query);
        ApiPageUtils pageUtil = new ApiPageUtils(result, total, query.getLimit(), query.getPage());

        return toResponsSuccess(pageUtil);
    }
}