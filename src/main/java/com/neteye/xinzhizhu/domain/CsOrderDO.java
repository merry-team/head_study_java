package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sharingcard.common.utils.excel.annotation.ExcelField;
import com.sharingcard.common.utils.excel.fieldtype.OrderPrice;
import com.sharingcard.common.utils.excel.fieldtype.PayStatus;



/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-27 10:43:37
 */
public class CsOrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String orderSn;
	//
	private Integer userId;
	//
	private Integer orderStatus;
	//
	private Integer shippingStatus;
	//
	private Integer payStatus;
	//
	private String consignee;
	//
	private String country;
	//
	private String province;
	//
	private String city;
	//
	private String district;
	//
	private String address;
	//
	private String mobile;
	//
	private String postscript;
	//
	private Integer shippingId;
	//
	private String shippingName;
	//
	private String payId;
	//
	private String payName;
	//
	private BigDecimal shippingFee;
	//实际需要支付的金额
	private BigDecimal actualPrice;
	//
	private Integer integral;
	//
	private BigDecimal integralMoney;
	//订单总价
	private BigDecimal orderPrice;
	//商品总价
	private BigDecimal goodsPrice;
	//
	private Date addTime;
	//
	private Date confirmTime;
	//
	private Date payTime;
	//配送费用
	private Integer freightPrice;
	//使用的优惠券id
	private Integer couponId;
	//
	private Integer parentId;
	//
	private BigDecimal couponPrice;
	//
	private Integer callbackStatus;
	//
	private String shippingNo;
	//
	private BigDecimal fullCutPrice;
	//
	private String orderType;

	private String nickname;
	
	private String userMobile;
	
	
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	/**
	 * 获取：
	 */
	@ExcelField(title="订单", align=2, sort=1)
	public String getOrderSn() {
		return orderSn;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：
	 */
	public void setShippingStatus(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getShippingStatus() {
		return shippingStatus;
	}
	/**
	 * 设置：
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：
	 */
	@ExcelField(title="付款状态", align=2, sort=2,fieldType=PayStatus.class)
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 * 获取：
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * 设置：
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * 获取：
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}
	/**
	 * 获取：
	 */
	@ExcelField(title="产品名称", align=2, sort=3)
	public String getPostscript() {
		return postscript;
	}
	/**
	 * 设置：
	 */
	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}
	/**
	 * 获取：
	 */
	public Integer getShippingId() {
		return shippingId;
	}
	/**
	 * 设置：
	 */
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	/**
	 * 获取：
	 */
	public String getShippingName() {
		return shippingName;
	}
	/**
	 * 设置：
	 */
	public void setPayId(String payId) {
		this.payId = payId;
	}
	/**
	 * 获取：
	 */
	public String getPayId() {
		return payId;
	}
	/**
	 * 设置：
	 */
	public void setPayName(String payName) {
		this.payName = payName;
	}
	/**
	 * 获取：
	 */
	public String getPayName() {
		return payName;
	}
	/**
	 * 设置：
	 */
	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getShippingFee() {
		return shippingFee;
	}
	/**
	 * 设置：实际需要支付的金额
	 */
	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}
	/**
	 * 获取：实际需要支付的金额
	 */
	@ExcelField(title="订单总价", align=2, sort=4,fieldType=OrderPrice.class)
	public BigDecimal getActualPrice() {
		return actualPrice;
	}
	/**
	 * 设置：
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**
	 * 获取：
	 */
	public Integer getIntegral() {
		return integral;
	}
	/**
	 * 设置：
	 */
	public void setIntegralMoney(BigDecimal integralMoney) {
		this.integralMoney = integralMoney;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getIntegralMoney() {
		return integralMoney;
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
	@ExcelField(title="下单时间", align=2, sort=5)
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
	@ExcelField(title="支付时间", align=2, sort=6)
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 设置：配送费用
	 */
	public void setFreightPrice(Integer freightPrice) {
		this.freightPrice = freightPrice;
	}
	/**
	 * 获取：配送费用
	 */
	public Integer getFreightPrice() {
		return freightPrice;
	}
	/**
	 * 设置：使用的优惠券id
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取：使用的优惠券id
	 */
	public Integer getCouponId() {
		return couponId;
	}
	/**
	 * 设置：
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：
	 */
	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getCouponPrice() {
		return couponPrice;
	}
	/**
	 * 设置：
	 */
	public void setCallbackStatus(Integer callbackStatus) {
		this.callbackStatus = callbackStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getCallbackStatus() {
		return callbackStatus;
	}
	/**
	 * 设置：
	 */
	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}
	/**
	 * 获取：
	 */
	public String getShippingNo() {
		return shippingNo;
	}
	/**
	 * 设置：
	 */
	public void setFullCutPrice(BigDecimal fullCutPrice) {
		this.fullCutPrice = fullCutPrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getFullCutPrice() {
		return fullCutPrice;
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
	@ExcelField(title="用户昵称", align=2, sort=7)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@ExcelField(title="用户手机号码", align=2, sort=8)
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
}
