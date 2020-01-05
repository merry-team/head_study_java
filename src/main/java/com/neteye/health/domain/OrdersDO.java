package com.neteye.health.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sharingcard.common.utils.excel.annotation.ExcelField;
import com.sharingcard.common.utils.excel.fieldtype.OrderPrice;
import com.sharingcard.common.utils.excel.fieldtype.PayStatus;
import com.sharingcard.common.utils.excel.fieldtype.PayType;



/**
 * 订单表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-18 12:38:24
 */
public class OrdersDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//订单序列号
	private String orderSn;
	//会员Id
	private Integer userId;
	//订单状态
    /*
    订单状态
    1xx 表示订单取消和删除等状态  0订单创建成功等待付款，　101订单已取消，　102订单已删除
    2xx 表示订单支付状态　200订单已付款
    3xx 表示订单物流相关状态　300用户已经预约
    */
	private Integer orderStatus;
	//付费类型：1、企业付费；2、个人付费
	private Integer payType;
	//付款状态 支付状态;0未付款;1付款中;2已付款;4退款
	private Integer payStatus;
	//手机
	private String mobile;
	//补充说明
	private String postscript;
	//付款ID
	private String payId;
	//商品id
	private Integer goodId;
	//商品名称
	private String goodName;
	//订单总价
	private BigDecimal orderPrice;
	//商品总价
	private BigDecimal goodsPrice;
	//
	private Date addTime;
	//
	private Date confirmTime;
	//预约状态
	private Integer confirmStatus;
	//
	private Date payTime;
	//
	private String orderType;
	//预约时间
	private Date bookingDate;
	//预约状态
	private Integer bookingStatus;
	//预约体检机构
	private Integer bookingDepart;
	//分公司名称
	private String departmentName;
	//证件id
	private String idcard;
	//证件用户姓名
	private String name;
	
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
//	@ExcelField(title="ID", align=2, sort=0)
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：订单序列号
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	/**
	 * 获取：订单序列号
	 */
	@ExcelField(title="订单", align=2, sort=1)
	public String getOrderSn() {
		return orderSn;
	}
	/**
	 * 设置：会员Id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：会员Id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：订单状态
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：订单状态
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：付费类型：1、企业付费；2、个人付费
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：付费类型：1、企业付费；2、个人付费
	 */
	@ExcelField(title="付费类型", align=2, sort=4,fieldType=PayType.class)
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：付款状态 支付状态;0未付款;1付款中;2已付款;4退款
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：付款状态 支付状态;0未付款;1付款中;2已付款;4退款
	 */
	@ExcelField(title="付款状态", align=2, sort=5,fieldType=PayStatus.class)
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机
	 */
	@ExcelField(title="手机", align=2, sort=6)
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：补充说明
	 */
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	/**
	 * 获取：补充说明
	 */
	public String getPostscript() {
		return postscript;
	}
	/**
	 * 设置：付款ID
	 */
	public void setPayId(String payId) {
		this.payId = payId;
	}
	/**
	 * 获取：付款ID
	 */
	public String getPayId() {
		return payId;
	}
	/**
	 * 设置：商品id
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getGoodId() {
		return goodId;
	}
	/**
	 * 设置：商品名称
	 */
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	/**
	 * 获取：商品名称
	 */
	@ExcelField(title="商品名称", align=2, sort=7)
	public String getGoodName() {
		return goodName;
	}
	/**
	 * 设置：订单总价
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	/**
	 * 获取：订单总价
	 */
	@ExcelField(title="订单总价", align=2, sort=7,fieldType=OrderPrice.class)
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	/**
	 * 设置：商品总价
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	/**
	 * 获取：商品总价
	 */
	@ExcelField(title="商品总价", align=2, sort=8,fieldType=OrderPrice.class)
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * 设置：
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：
	 */
	@ExcelField(title="下单时间", align=2, sort=9)
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	/**
	 * 获取：
	 */
	@ExcelField(title="预约确认时间", align=2, sort=12)
	public Date getConfirmTime() {
		return confirmTime;
	}
	/**
	 * 设置：
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：
	 */
	@ExcelField(title="付款时间", align=2, sort=10)
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 设置：
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 设置：预约时间
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * 获取：预约时间
	 */
	@ExcelField(title="预约时间", align=2, sort=11)
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * 设置：预约体检机构
	 */
	public void setBookingDepart(Integer bookingDepart) {
		this.bookingDepart = bookingDepart;
	}
	/**
	 * 获取：预约体检机构
	 */
	public Integer getBookingDepart() {
		return bookingDepart;
	}
	@ExcelField(title="预约机构", align=2, sort=13)
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@ExcelField(title="身份证号码", align=2, sort=2)
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	@ExcelField(title="用户姓名", align=2, sort=3)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Integer getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}	
}
