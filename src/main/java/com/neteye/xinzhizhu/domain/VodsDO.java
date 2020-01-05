package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * vod
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:33:25
 */
public class VodsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//vodid
	private Integer vodId;
	//标题
	private String vodTitle;
	//描述
	private String content;
	//作者
	private String author;
	//课程id
	private Integer courseId;
	//课程名称
	private String courseName;
	//文件时长
	private Integer vodTime;
	//路径路径
	private String fileUrl;
	//图片
	private String imageUrl;
	//阅读量
	private Integer readCount;
	//收藏数
	private Integer collectCount;
	//评论数
	private Integer commentCount;
	//创建时间
	private Date creatTime;
	//
	private Integer orderSort;
	
	private Integer status;

	private Integer choice;
	
	//课程价格
	private Integer coursePrice;
	
	/**
	 * 设置：vodid
	 */
	public void setVodId(Integer vodId) {
		this.vodId = vodId;
	}
	/**
	 * 获取：vodid
	 */
	public Integer getVodId() {
		return vodId;
	}
	/**
	 * 设置：标题
	 */
	public void setVodTitle(String vodTitle) {
		this.vodTitle = vodTitle;
	}
	/**
	 * 获取：标题
	 */
	public String getVodTitle() {
		return vodTitle;
	}
	/**
	 * 设置：描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：描述
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
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
	 * 设置：文件时长
	 */
	public void setVodTime(Integer vodTime) {
		this.vodTime = vodTime;
	}
	/**
	 * 获取：文件时长
	 */
	public Integer getVodTime() {
		return vodTime;
	}
	/**
	 * 设置：路径路径
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	/**
	 * 获取：路径路径
	 */
	public String getFileUrl() {
		return fileUrl;
	}
	/**
	 * 设置：阅读量
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	/**
	 * 获取：阅读量
	 */
	public Integer getReadCount() {
		return readCount;
	}
	/**
	 * 设置：收藏数
	 */
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getCollectCount() {
		return collectCount;
	}
	/**
	 * 设置：评论数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatTime() {
		return creatTime;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getChoice() {
		return choice;
	}
	public void setChoice(Integer choice) {
		this.choice = choice;
	}
	public Integer getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	public Integer getOrderSort() {
		return orderSort;
	}
	public void setOrderSort(Integer orderSort) {
		this.orderSort = orderSort;
	}
	
}
