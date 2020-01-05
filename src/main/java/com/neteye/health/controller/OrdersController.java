package com.neteye.health.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neteye.health.domain.OrdersDO;
import com.neteye.health.service.OrdersService;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;
import com.sharingcard.common.utils.DateUtils;

/**
 * 订单表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-18 12:38:24
 */
 
@Controller
@RequestMapping("/health/orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	@GetMapping()
	@RequiresPermissions("health:orders:orders")
	String Orders(){
	    return "health/orders/orders";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("health:orders:orders")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sort", "a.booking_date ");
		params.put("order", " desc ,a.confirm_time asc ");
        Query query = new Query(params);
		List<OrdersDO> ordersList = ordersService.list(query);
		int total = ordersService.count(query);
		PageUtils pageUtils = new PageUtils(ordersList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/export")
	@RequiresPermissions("health:orders:orders")
	public void export(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("sort", "a.booking_date ");
		params.put("order", " desc ,a.confirm_time asc ");
	    String fileName = "订单用户数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
//	    new ExportExcel("预约完成用户数据", Healthy.class).setDataList(page.getList()).write(response, fileName).dispose();
//		List<OrdersDO> ordersList = ordersService.list(query);
//		int total = ordersService.count(query);
//		PageUtils pageUtils = new PageUtils(ordersList, total);

	}	
	
	@GetMapping("/add")
	@RequiresPermissions("health:orders:add")
	String add(){
	    return "health/orders/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("health:orders:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrdersDO orders = ordersService.get(id);
		model.addAttribute("orders", orders);
	    return "health/orders/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("health:orders:add")
	public R save( OrdersDO orders){
		if(ordersService.save(orders)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("health:orders:edit")
	public R update( OrdersDO orders){
		ordersService.update(orders);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("health:orders:remove")
	public R remove( Integer id){
		if(ordersService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("health:orders:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		ordersService.batchRemove(ids);
		return R.ok();
	}
	
	
	/**
	 * 删除
	 */
	@PostMapping( "/booking")
	@ResponseBody
	@RequiresPermissions("health:orders:edit")
	public R booking( Integer id){
		if(ordersService.booking(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchBooking")
	@ResponseBody
	@RequiresPermissions("health:orders:edit")
	public R booking(@RequestParam("ids[]") Integer[] ids){
		ordersService.batchBooking(ids);
		return R.ok();
	}
	
}
