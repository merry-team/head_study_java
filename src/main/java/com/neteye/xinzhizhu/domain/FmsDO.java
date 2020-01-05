package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * fm 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:39:35
 */
public class FmsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer fmId;
	//标题
	private String fmTitle;
	//描述
	private String content;
	//作者
	private String author;
	//课程id
	private Integer courseId;
	//课程分类
	private String categoryName;
	//文件时长
	private Integer fmTime;
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
	private Integer status;

	/**
	 * 设置：id
	 */
	public void setFmId(Integer fmId) {
		this.fmId = fmId;
	}
	/**
	 * 获取：id
	 */
	public Integer getFmId() {
		return fmId;
	}
	/**
	 * 设置：标题
	 */
	public void setFmTitle(String fmTitle) {
		this.fmTitle = fmTitle;
	}
	/**
	 * 获取：标题
	 */
	public String getFmTitle() {
		return fmTitle;
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
	public void setFmTime(Integer fmTime) {
		this.fmTime = fmTime;
	}
	/**
	 * 获取：文件时长
	 */
	public Integer getFmTime() {
		return fmTime;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
