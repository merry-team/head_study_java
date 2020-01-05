package com.neteye.health.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neteye.health.domain.UsersDO;
import com.neteye.health.service.CompanyService;
import com.neteye.health.service.DepartmentService;
import com.neteye.health.service.GoodService;
import com.neteye.health.service.UsersService;
import com.neteye.xinzhizhu.annotation.IgnoreAuth;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.sharingcard.redis.util.RedisUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/health")
public class ApiHealth extends ApiBaseAction {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private UsersService usersService;
	@Autowired
	protected RedisUtil redisUtil;
	@Autowired
	private CompanyService companyService;
    /**
     * 获取用户的体检机构址接口
     */
	@IgnoreAuth
    @ApiOperation(value = "获取用户的体检机构址接口", response = Map.class)
    @PostMapping("listdepart")
    public Object listdepart(@RequestParam Map<String, Object> params) {
		// 查询列表数据
    	/*
    	 * params 
    	 * companyId
    	 * provinceId
    	 * cityId
    	 * bookDate
    	 */
    	params.put("status", 1);
		return toResponsSuccess(departmentService.list(params));
    }
    
	@IgnoreAuth
    @ApiOperation(value = "获取用户的体检机构址接口", response = Map.class)
    @PostMapping("listcompany")
    public Object listcompany(@RequestParam Map<String, Object> params) {
    	params.put("status", 1);
    	params.put("type", 1);
		return toResponsSuccess(companyService.list(params));
    }
	
    /**
     * 获取用户的体检机构址接口
     */
	@IgnoreAuth
    @ApiOperation(value = "获取体检产品", response = Map.class)
    @PostMapping("listgood")
    public Object listgood(@RequestParam Map<String, Object> params) {
		// 查询列表数据
    	/*
    	 * gender
    	 * marry
    	 */
    	String sessionId = request.getRequestedSessionId();
    	UsersDO Users  =(UsersDO) redisUtil.get(sessionId);
    	if(Users!=null) {
	    	params.put("isOnSale", 1);
	    	params.put("userId", Users.getId());
	    	params.put("gender", Users.getGender());
	    	params.put("marry", Users.getMarry());
			return toResponsSuccess(goodService.list(params));
    	}else {
    		return toResponsFail("登录失败");
    	}
    } 
	
    /**
     * 获取用户的体检机构址接口
     */
	@IgnoreAuth
    @ApiOperation(value = "体检用户登录", response = Map.class)
    @PostMapping("login")
    public Object login(@RequestParam Map<String, Object> params) {
    	params.put("status", 1);
    	if(params.get("username")!=null && params.get("password")!=null) {
	    	List<UsersDO> listUsers = usersService.list(params);
	    	if(listUsers!=null && listUsers.size()>0)
	    	{
	    		String sessionId = request.getSession().getId();
	    		UsersDO Users  = listUsers.get(0);
	    		redisUtil.set(sessionId, Users);
	    		
	    		return toResponsSuccess(Users.getNickname());
	    	}
	    	return toResponsFail("用户名或者密码错误");
    	}
    	else{
    		return toResponsFail("登录失败");
    	}
		
    } 
}

