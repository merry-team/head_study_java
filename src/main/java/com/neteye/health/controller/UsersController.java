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
import com.neteye.health.domain.GoodDO;
import com.neteye.health.domain.UsersDO;
import com.neteye.health.service.CompanyService;
import com.neteye.health.service.GoodService;
import com.neteye.health.service.UsersService;
import com.neteye.xinzhizhu.utils.DateUtils;
import com.neteye.xinzhizhu.utils.IPUtils;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.HttpContextUtils;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;

/**
 * 用户表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-14 11:19:22
 */
 
@Controller
@RequestMapping("/health/users")
public class UsersController  extends BaseController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private GoodService goodService;
	
	
	@GetMapping()
	@RequiresPermissions("health:users:users")
	String Users(){
	    return "health/users/users";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("health:users:users")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UsersDO> usersList = usersService.list(query);
		int total = usersService.count(query);
		PageUtils pageUtils = new PageUtils(usersList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("health:users:add")
	String add(Model model){
		Map<String, Object> query = new HashMap();
		query.put("status", 1);
		query.put("type", 2);
		List<CompanyDO> companyList = companyService.list(query);
		model.addAttribute("companyList", companyList);
		query.clear();
		query.put("isOnSale", 1);
		List<GoodDO> goodList = goodService.list(query);
		model.addAttribute("goodList", goodList);
		
	    return "health/users/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("health:users:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		Map<String, Object> query = new HashMap();
		query.put("status", 1);
		query.put("type", 2);
		List<CompanyDO> companyList = companyService.list(query);
		model.addAttribute("companyList", companyList);
		query.clear();
		query.put("isOnSale", 1);
		List<GoodDO> goodList = goodService.list(query);
		model.addAttribute("goodList", goodList);
		UsersDO users = usersService.get(id);
		model.addAttribute("users", users);
	    return "health/users/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("health:users:add")
	public R save( UsersDO users,Integer goodId){
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
		if(usersService.save(users, goodId, ip)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("health:users:edit")
	public R update( UsersDO users){
		Map map = new HashMap();
		map.put("mobile", users.getMobile());
		List<UsersDO> list = usersService.list(map);
		//判重
		if(list != null && list.size()>0){
			for(int i=0; i<list.size(); i++) {
				if(!list.get(i).getId().equals(users.getId()))
					return R.error("手机号码已经注册");
			}
		}
		users.setIdcard(users.getIdcard().toLowerCase());
		users.setUsername(users.getMobile());
		users.setPassword(users.getIdcard().substring(users.getIdcard().length()-6, users.getIdcard().length()));
		Date b = DateUtils.parse(users.getIdcard().substring(6,14), "yyyyMMdd");
		if(b!=null) {
			users.setBirthday(b);
		}
		users.setNickname(users.getName());
		usersService.update(users);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("health:users:remove")
	public R remove( Integer id){
//		if(usersService.remove(id)>0){
//		return R.ok();
//		}
//		return R.error();
		UsersDO users = usersService.get(id);
		users.setStatus(0);
		if(usersService.update(users)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("health:users:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		usersService.batchRemove(ids);
		return R.ok();
	}
	
}
