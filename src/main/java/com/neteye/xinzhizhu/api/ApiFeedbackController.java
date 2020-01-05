package com.neteye.xinzhizhu.api;

import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.entity.FeedbackVo;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ApiFeedbackService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiFeedbackController <br>
 */
@Api(tags = "反馈")
@RestController
@RequestMapping("/api/feedback")
public class ApiFeedbackController extends ApiBaseAction {
    @Autowired
    private ApiFeedbackService feedbackService;

    /**
     * 添加反馈
     */
    @ApiOperation(value = "添加反馈")
    @PostMapping("save")
    public Object save(@LoginUser UserVo loginUser,@RequestParam Map<String, Object> params) {
    	loginUser = getUser();
        if (null != params) {
            FeedbackVo feedbackVo = new FeedbackVo();
            feedbackVo.setUserId(getUserId().intValue());
            feedbackVo.setUserName(loginUser.getUsername());
            feedbackVo.setMobile((String)params.get("mobile"));
            feedbackVo.setFeedType(Integer.valueOf((String)params.get("index")));
            feedbackVo.setStatus(1);
            feedbackVo.setContent((String)params.get("content"));
            feedbackVo.setAddTime(new Date());
            feedbackService.save(feedbackVo);
            return super.toResponsSuccess("感谢你的反馈");
        }
        return super.toResponsFail("反馈失败");
    }
}