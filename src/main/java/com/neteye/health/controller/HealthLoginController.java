package com.neteye.health.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.neteye.health.domain.CompanyDO;
import com.neteye.health.domain.DepartmentDO;
import com.neteye.health.domain.GoodDO;
import com.neteye.health.domain.OrdersDO;
import com.neteye.health.domain.UsersDO;
import com.neteye.health.domain.WorktimeDO;
import com.neteye.health.service.CompanyService;
import com.neteye.health.service.DepartmentService;
import com.neteye.health.service.GoodService;
import com.neteye.health.service.OrdersService;
import com.neteye.health.service.UsersService;
import com.neteye.health.service.WorktimeService;
import com.neteye.xinzhizhu.domain.CategoryDO;
import com.neteye.xinzhizhu.utils.ApiBaseAction;
import com.neteye.xinzhizhu.utils.DateUtils;
import com.neteye.xinzhizhu.utils.IPUtils;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.HttpContextUtils;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;
import com.sharingcard.redis.util.RedisUtil;

/**
 * 企业分公司
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 11:31:01
 */
 
@Controller
@RequestMapping("/healthlogin")
public class HealthLoginController  extends ApiBaseAction {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	protected RedisUtil redisUtil;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/{id}")
	String index(@PathVariable("id") Integer id,Model model){
		CompanyDO company = companyService.get(id);
		model.addAttribute("company", company);
		String sessionId = request.getSession().getId();
		redisUtil.set(sessionId+"id", id);
	    return "health/index";
	}
	
	@GetMapping()
	String i(){
		String sessionId = request.getSession().getId();
		Integer id =(Integer) redisUtil.get(sessionId+"id");
		if(id !=null) {
			return "redirect:/healthlogin/"+id;
		}else {
			return "health/index2";
		}
	}

	@GetMapping("/reg")
	String reg(Model model){
	    return "health/reg";
	}
	
	@ResponseBody
	@RequestMapping("/regsave")
	public R regsave( UsersDO users){
		Map map = new HashMap();
		map.put("mobile", users.getMobile());
		List<UsersDO> list = usersService.list(map);
		//判重
		if(list != null && list.size()>0){
			return R.error("手机号码已经注册");
		}
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);
        users.setLastLoginIp(ip);
        users.setLastLoginTime(new Date());
 
		//保存用户信息
		users.setIdcard(users.getIdcard().toLowerCase());
		users.setUsername(users.getMobile());
		users.setType(2); //自费
		users.setPassword(users.getIdcard().substring(users.getIdcard().length()-6, users.getIdcard().length()));
		Date b = DateUtils.parse(users.getIdcard().substring(6,14), "yyyyMMdd");
		if(b!=null) {
			users.setBirthday(b);
		}
		
		users.setRegisterTime(new Date());
		users.setRegisterIp(ip);
		users.setNickname(users.getName());
		String sessionId = request.getSession().getId();
		users.setCompanyId((Integer)redisUtil.get(sessionId+"id"));//公司
		users.setStatus(1);
		if(usersService.save(users)>0){
			redisUtil.set(sessionId,users);
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/booking")
	String booking(Model model){
		String sessionId = request.getSession().getId();
		UsersDO Users  = (UsersDO)redisUtil.get(sessionId);
		if(Users==null) {
			return "redirect:/healthlogin";
		}
		model.addAttribute("users", Users);
		//判断是否有没有预约的订单
		Map map = new HashMap();
		map.put("userId", Users.getId());
		map.put("payStatus", 2);
		map.put("bookingStatus", 0);		
		List<OrdersDO> list = ordersService.list(map);
		if(list!= null && list.size()>0) {
			return "health/booking"; //预约入口
		}else {
			return "redirect:/healthlogin/listordering"; //订购入口
		}
	}
	
	@GetMapping("/listordering")
	String listordering(Model model){
		String sessionId = request.getSession().getId();
		UsersDO Users  = (UsersDO)redisUtil.get(sessionId);
		if(Users==null) {
			return "redirect:/healthlogin";
		}
		model.addAttribute("users", Users);
		//判断是否有没有预约的订单
		Map<String, Object> params =  new HashMap<String, Object>();
    	params.put("isOnSale", 1);
    	params.put("userId", Users.getId());
    	params.put("gender", Users.getGender());
    	params.put("marry", Users.getMarry());
    	model.addAttribute("listgood", goodService.list(params));
    	return "health/listordering"; //订购入口	
	}
	
	@GetMapping("/ordering/{id}")
	String ordering(@PathVariable("id") Integer id,Model model){
		String sessionId = request.getSession().getId();
		UsersDO Users  = (UsersDO)redisUtil.get(sessionId);
		if(Users==null) {
			return "redirect:/healthlogin";
		}	
		model.addAttribute("users", Users);
    	model.addAttribute("good", goodService.get(id));
    	return "health/ordering"; //订购入口	
	}
	
	@ResponseBody
	@RequestMapping("/savebooking")
	public R update( OrdersDO orders){
		String sessionId = request.getSession().getId();
		UsersDO Users  = (UsersDO)redisUtil.get(sessionId);
		if(Users==null) {
			return R.error();
		}	
		Map map = new HashMap();
		map.put("userId", Users.getId());
		map.put("payStatus", 2);
		map.put("bookingStatus", 0);	
		List<OrdersDO> list = ordersService.list(map);
		if(list!= null && list.size()>0) {
			for(int i=0;i<list.size();i++) {
				OrdersDO order = list.get(i);
				order.setBookingDate(orders.getBookingDate());
				order.setBookingDepart(orders.getBookingDepart());
				order.setBookingStatus(1);
				ordersService.update(order);
			}
			redisUtil.del(sessionId);
			return R.ok();
		}
		return R.error();
		
	}
	
	@GetMapping("p/{id}")
	String p(@PathVariable("id") Integer id,Model model){
		GoodDO good = goodService.get(id);
		if(good!=null && good.getIsOnSale().equals(1)) {
			model.addAttribute("good", good);
			return "health/p";
		}
		else {
			return "health/index";
		}
	}
}
