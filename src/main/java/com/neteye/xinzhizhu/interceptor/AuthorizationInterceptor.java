package com.neteye.xinzhizhu.interceptor;

import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.entity.TokenEntity;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.service.ApiUserService;
import com.neteye.xinzhizhu.service.TokenService;
import com.neteye.xinzhizhu.utils.ApiRRException;
import com.sharingcard.redis.util.RedisUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:38
 */
// 请求拦截器
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    protected RedisUtil redisUtil;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String LOGIN_TOKEN_KEY = "X-Neteye-Token";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 在方法被调用前执行,true为拦截，调用下一个拦截器
    /*
    request：在该参数中可以获取到和请求相关的信息。比如是否为get请求等。
    response：在该参数中可以获取对象的响应信息。
    handler：该参数中包含了对应方法的信息。比如：方法中的参数类型、参数的注解、方法的注解等信息。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("请求路径：{}", request.getRequestURI());
        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-Neteye-Token,X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new ApiRRException("请先登录", 401);
        }

        //查询token信息
        redisUtil.get(token);

        TokenEntity tokenEntity = (TokenEntity) redisUtil.get(token);
        if (tokenEntity == null)
            tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new ApiRRException("token失效，请重新登录", 401);

            //ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        } else {
            //数据插入测试：
            UserVo user = (UserVo) apiUserService.queryObject(tokenEntity.getUserId());
            redisUtil.set(token, tokenEntity);
            redisUtil.set(token + user.getUserId(), user);
        }


        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
