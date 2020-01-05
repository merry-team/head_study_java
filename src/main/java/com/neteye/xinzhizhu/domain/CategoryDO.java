package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:13:35
 */
public class CategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer categoryId;
	//分类名称
	private String categoryName;
	//分类类型1：课程；2：fm：3：文章
	private Integer categoryType;
	//
	private String content;
	//
	private String imageUrl;
	//
	private Date createtime;
	//状态 0：无效：1：有效
	private Integer status;

	/**
	 * 设置：
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：分类名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 获取：分类名称
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 设置：分类类型1：课程；2：fm：3：文章
	 */
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}
	/**
	 * 获取：分类类型1：课程；2：fm：3：文章
	 */
	public Integer getCategoryType() {
		return categoryType;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：状态 0：无效：1：有效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0：无效：1：有效
	 */
	public Integer getStatus() {
		return status;
	}
}
