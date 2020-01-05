package com.sharingcard.wxpay.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sharingcard.activiti.domain.SalaryDO;
import com.sharingcard.activiti.service.SalaryService;
import com.sharingcard.activiti.utils.ActivitiUtils;
//import com.sharingcard.card.domain.UserProfileDO;
//import com.sharingcard.card.service.UserProfileService;
import com.sharingcard.common.config.Constant;
import com.sharingcard.common.controller.BaseController;
import com.sharingcard.common.utils.PageUtils;
import com.sharingcard.common.utils.Query;
import com.sharingcard.common.utils.R;
import com.sharingcard.common.utils.ShiroUtils;
import com.sharingcard.wxpay.utils.ConfigUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 审批流程测试表
 *
 * @author yinxj
 * @date 2019-04-09 13:33:16
 */

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController{
 
//	@Autowired
//	private UserProfileService userProfileService;
//	
	private static final String APPID = ConfigUtil.getProperty("wx.appid");
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	@GetMapping()
//	//@RequiresPermissions("wx:userProfile:userProfile")
//	String UserProfile(HttpServletRequest request, Model model) {
//		UserProfileDO userProfileDO = userProfileService.get(getUserId());
//        if (userProfileDO!=null && userProfileDO.getOpenid()!=null && !userProfileDO.getOpenid().equals("")) {
//        	model.addAttribute("code", 0);
//        	model.addAttribute("message", "您已经绑定了微信");
//        }
//        else {
//	    	model.addAttribute("code", -1);
//	    	model.addAttribute("message", "您还未绑定微信");
//        }
//    	model.addAttribute("appid", APPID);
//	    return "card/wxbind";
//	}
//
//	@ResponseBody
//	@PostMapping("/check")
//	//@RequiresPermissions("wx:userProfile:userProfile")
//	public R UserProfileCheck(){
//		UserProfileDO userProfileDO = userProfileService.get(getUserId());
//        if (userProfileDO!=null &&(userProfileDO.getOpenid()!=null || !userProfileDO.equals("")) ) {
//        	return R.error(1, "您已经绑定了微信不需要绑定");
//        }
//        return R.ok("您还未绑定微信");
//	}
	
	/**
	    * 保存
	 */
//    @GetMapping("/saveopenid")
//    //@RequiresPermissions("wx:userProfile:userProfile")
//    public String saveOrUpdate(HttpServletRequest request, Model model) {
//    	String code= request.getParameter("code");
//    	String state = request.getParameter("state");
//    	model.addAttribute("code", -2);
//    	model.addAttribute("message", "绑定微信失败");
//    	logger.debug("saveopenid:code["+code+"],state["+state+"]");
//    	if((code!=null && !code.equals("")) && (state!=null && !state.equals(""))) {
//    		try {
//    			logger.debug("saveopenid:code["+code+"],UserId["+getUserId()+"]");
//	    		if(userProfileService.BindWx(getUserId(),code)==0) {
//	    	    	model.addAttribute("code", 0);
//	    	    	model.addAttribute("message", "绑定微信成功");
//	    		}
//    		}
//    		catch(Exception e) {
//
//    		}
//    	}
//    	return "card/wxbind";
//    }  

	/**
	    * 保存
	 * @throws IOException 
	 */
 @GetMapping("/MP_verify_2FsOzpChwhTxq7nQ.txt")
 public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response ) throws IOException {
	 String data = "2FsOzpChwhTxq7nQ";
 	OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
 	response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
 	byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
 	outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
 	return;
 }  
}
