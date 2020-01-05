package com.neteye.xinzhizhu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.neteye.health.domain.OrdersDO;
import com.neteye.xinzhizhu.domain.CsOrderDO;
import com.neteye.xinzhizhu.service.CsOrderService;
import com.sharingcard.common.utils.DateUtils;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;
import com.sharingcard.common.utils.excel.ExportExcel;

/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-27 10:43:37
 */
 
@Controller
@RequestMapping("/xinzhizhu/csOrder")
public class CsOrderController {
	@Autowired
	private CsOrderService csOrderService;
	
	@GetMapping()
	@RequiresPermissions("xinzhizhu:csOrder:csOrder")
	String CsOrder(){
	    return "xinzhizhu/csOrder/csOrder";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xinzhizhu:csOrder:csOrder")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CsOrderDO> csOrderList = csOrderService.list(query);
		int total = csOrderService.count(query);
		PageUtils pageUtils = new PageUtils(csOrderList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/export")
	@RequiresPermissions("xinzhizhu:csOrder:csOrder")
	public void export(@RequestParam Map<String, Object> params,HttpServletResponse response){
		//查询列表数据
	    String fileName = "订单用户数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	    List<CsOrderDO> ordersList = csOrderService.list(params);
	    try {
			new ExportExcel("订单用户数据", CsOrderDO.class).setDataList(ordersList).write(response, fileName).dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@GetMapping("/add")
	@RequiresPermissions("xinzhizhu:csOrder:add")
	String add(){
	    return "xinzhizhu/csOrder/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("xinzhizhu:csOrder:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CsOrderDO csOrder = csOrderService.get(id);
		model.addAttribute("csOrder", csOrder);
	    return "xinzhizhu/csOrder/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xinzhizhu:csOrder:add")
	public R save( CsOrderDO csOrder){
		if(csOrderService.save(csOrder)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xinzhizhu:csOrder:edit")
	public R update( CsOrderDO csOrder){
		csOrderService.update(csOrder);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:csOrder:remove")
	public R remove( Integer id){
		if(csOrderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xinzhizhu:csOrder:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		csOrderService.batchRemove(ids);
		return R.ok();
	}
	
}
