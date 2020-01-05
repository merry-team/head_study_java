package com.neteye.health.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-12 20:29:04
 */
public class GoodDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//商品名称
	private String goodName;
	//简介
	private String goodsBrief;
	//商品描述
	private String goodsDesc;
	//套餐类型：0、男女都可以；1、男，2、女（已婚），3、女（未婚）
	private Integer type;
	//商品排序
	private Integer sortOrder;
	//是否上架 1：上架
	private Integer isOnSale;
	//加入时间
	private Date addTime;
	//零售价格
	private BigDecimal counterPrice;
	//实际价格
	private BigDecimal retailPrice;
	//商品主图
	private String primaryPicUrl;
	//商品列表图
	private String listPicUrl;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
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
	public String getGoodName() {
		return goodName;
	}
	/**
	 * 设置：简介
	 */
	public void setGoodsBrief(String goodsBrief) {
		this.goodsBrief = goodsBrief;
	}
	/**
	 * 获取：简介
	 */
	public String getGoodsBrief() {
		return goodsBrief;
	}
	/**
	 * 设置：商品描述
	 */
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	/**
	 * 获取：商品描述
	 */
	public String getGoodsDesc() {
		return goodsDesc;
	}
	/**
	 * 设置：套餐类型：0、男女都可以；1、男，2、女（已婚），3、女（未婚）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：套餐类型：0、男女都可以；1、男，2、女（已婚），3、女（未婚）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：商品排序
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：商品排序
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	/**
	 * 设置：是否上架 1：上架
	 */
	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}
	/**
	 * 获取：是否上架 1：上架
	 */
	public Integer getIsOnSale() {
		return isOnSale;
	}
	/**
	 * 设置：加入时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：加入时间
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：零售价格
	 */
	public void setCounterPrice(BigDecimal counterPrice) {
		this.counterPrice = counterPrice;
	}
	/**
	 * 获取：零售价格
	 */
	public BigDecimal getCounterPrice() {
		return counterPrice;
	}
	/**
	 * 设置：实际价格
	 */
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * 获取：实际价格
	 */
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	/**
	 * 设置：商品主图
	 */
	public void setPrimaryPicUrl(String primaryPicUrl) {
		this.primaryPicUrl = primaryPicUrl;
	}
	/**
	 * 获取：商品主图
	 */
	public String getPrimaryPicUrl() {
		return primaryPicUrl;
	}
	/**
	 * 设置：商品列表图
	 */
	public void setListPicUrl(String listPicUrl) {
		this.listPicUrl = listPicUrl;
	}
	/**
	 * 获取：商品列表图
	 */
	public String getListPicUrl() {
		return listPicUrl;
	}
}
