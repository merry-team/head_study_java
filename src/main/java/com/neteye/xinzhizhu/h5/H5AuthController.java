package com.neteye.xinzhizhu.h5;

import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.annotation.LoginUser;
import com.neteye.xinzhizhu.entity.TokenEntity;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ApiUserService;
import com.neteye.xinzhizhu.service.TokenService;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.ApiRRException;
import com.neteye.xinzhizhu.utils.CharUtil;
import com.neteye.xinzhizhu.utils.CommonUtil;
import com.qiniu.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * API登录授权
 *
 * @author zhengbigbig
 * @email 780357902@qq.com
 * @date 2020-1-15
 */
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/h5")
public class H5AuthController extends ApiBaseAction {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ApiUserService userService;
    @Autowired
    private TokenService tokenService;

    @Value("${oauth.wx.APPID}")
    private String APPID;

    @Value("${oauth.wx.APPSECRET}")
    private String APPSECRET;

    /**
     * 鉴权
     */

    @GetMapping("auth")
    @IgnoreAuth
    @ApiOperation(value = "获取当前登录情况")
    public Object auth() {
        Map<String, Object> resultObj = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            UserVo userVo = userService.queryObject((Long) authentication.getPrincipal());
            resultObj.put("token", authentication.getCredentials());
            resultObj.put("userInfo", userVo);
            return toResponsSuccess(resultObj);
        } else {
            return toResponsObject(401, "用户未登录", "");
        }
    }


    @ApiOperation(value = "微信授权登录")
    @IgnoreAuth
    @GetMapping("login_weixin")
    public Object loginWeixin(@RequestParam Map<String, Object> params) {
        SecurityContextHolder.clearContext();
        String code = "";
        String openIdUrl = "";
        String openid = "";
        String access_token = "";
        String refresh_token = "";
        if (!StringUtils.isNullOrEmpty((String) params.get("code"))) {
            code = (String) params.get("code");
        } else {
            return toResponsFail("登录失败");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();

        //获取openid
        //通过自定义工具类组合出小程序需要的登录凭证 code
        openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        logger.debug("》》》组合token为：" + openIdUrl);
        JSONObject sessionData = CommonUtil.httpsRequest(openIdUrl, "GET", null);
        openid = sessionData.getString("openid");
        access_token = sessionData.getString("access_token");
        refresh_token = sessionData.getString("refresh_token");
        if (null == sessionData || StringUtils.isNullOrEmpty(openid)) {
            return toResponsFail("登录失败");
        }

        // 检验授权凭证是否有效,无效则刷新，重新赋值openid access_token
        String accessUrl = "https://api.weixin.qq.com/sns/auth?access_token=" + access_token + "&openid=" + openid;
        JSONObject accessData = CommonUtil.httpsRequest(accessUrl, "GET", null);
        if (null == accessData || accessData.getString("errmsg").contains("invalid")) {
            String refreshAccessUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APPID +
                    "&grant_type=refresh_token&refresh_token=" + refresh_token;
            JSONObject refreshAccessData = CommonUtil.httpsRequest(refreshAccessUrl, "GET", null);
            String errmsg = refreshAccessData.getString("errmsg");
            if ((null == refreshAccessData) || (!StringUtils.isNullOrEmpty(errmsg) && errmsg.contains("invalid"))) {
                return toResponsFail("登录失败");
            } else {
                openid = refreshAccessData.getString("openid");
                access_token = refreshAccessData.getString("access_token");
            }
        }

        // 判断数据库是否已存，若没存，则创建
        UserVo userVo;
        Date nowTime = new Date();
        userVo = userService.queryByOpenId(openid);
        if (null == userVo) {
            // 拉取用户信息
            String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token +
                    "&openid=" + openid + "&lang=zh_CN";
            JSONObject userInfoData = CommonUtil.httpsRequest(getUserInfoUrl, "GET", null);
            String errmsg = userInfoData.getString("errmsg");
            if ((null == userInfoData) || (!StringUtils.isNullOrEmpty(errmsg) && errmsg.contains("invalid"))) {
                return toResponsFail("登录失败");
            }
            userVo = new UserVo();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(openid);
            userVo.setRegister_time(nowTime);
            userVo.setRegister_ip(this.getClientIp());
            userVo.setLast_login_ip(userVo.getRegister_ip());
            userVo.setLast_login_time(userVo.getRegister_time());
            userVo.setWeixin_openid(openid);
            userVo.setAvatar(userInfoData.getString("headimgurl"));
            //性别 0：未知、1：男、2：女
            userVo.setGender(Integer.parseInt(userInfoData.getString("sex")));
            userVo.setNickname(userInfoData.getString("nickname"));
            userService.save(userVo);
            userVo = userService.queryByOpenId(openid);
        } else {
            userVo.setLast_login_ip(this.getClientIp());
            userVo.setLast_login_time(nowTime);
            userService.update(userVo);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userVo.getUserId());
        String token = MapUtils.getString(tokenMap, "token");

        if (StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }

        UsernamePasswordAuthenticationToken token2 =
                new UsernamePasswordAuthenticationToken(userVo.getUserId(), token, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(token2);
        resultObj.put("token", token);
        resultObj.put("userInfo", userVo);
        resultObj.put("userId", userVo.getUserId());
        return toResponsSuccess(resultObj);
    }

}
