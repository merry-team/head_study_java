package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:18:58
 */
public class CourseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//课程id
	private Integer courseId;
	//课程名称
	private String courseName;
	//课程分类
	private Integer categoryId;
	//课程分类
	private String categoryName;
	//课程描述
	private String content;
	//课程图片
	private String imageUrl;
	//课程价格
	private Integer coursePrice;
	//点击量
	private Integer readCount;
	//收藏量
	private Integer collectCount;
	//购买人数
	private Integer buyCount;
	//创建时间
	private Date createtime;
	//状态 0：无效：1：有效
	private Integer status;
	//类型 0：多课：1：单课
	private Integer type;

	private Integer orderSort;

	/**
	 * 设置：课程id
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：课程id
	 */
	public Integer getCourseId() {
		return courseId;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 获取：课程名称
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 设置：课程分类
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：课程分类
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：课程描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：课程描述
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：课程图片
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：课程图片
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置：课程价格
	 */
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	/**
	 * 获取：课程价格
	 */
	public Integer getCoursePrice() {
		return coursePrice;
	}
	/**
	 * 设置：点击量
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	/**
	 * 获取：点击量
	 */
	public Integer getReadCount() {
		return readCount;
	}
	/**
	 * 设置：收藏量
	 */
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	/**
	 * 获取：收藏量
	 */
	public Integer getCollectCount() {
		return collectCount;
	}
	/**
	 * 设置：购买人数
	 */
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	/**
	 * 获取：购买人数
	 */
	public Integer getBuyCount() {
		return buyCount;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(Integer orderSort) {
		this.orderSort = orderSort;
	}
}
