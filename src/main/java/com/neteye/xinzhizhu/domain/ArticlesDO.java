package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 文章
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:28:15
 */
public class ArticlesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer articleId;
	//标题
	private String articleTitle;
	//分类id
	private Integer categoryId;
	//课程分类
	private String categoryName;
	//描述
	private String content;
	//图片
	private String imageUrl;
	//作者
	private String author;
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
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	/**
	 * 获取：id
	 */
	public Integer getArticleId() {
		return articleId;
	}
	/**
	 * 设置：标题
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	/**
	 * 获取：标题
	 */
	public String getArticleTitle() {
		return articleTitle;
	}
	/**
	 * 设置：分类id
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：分类id
	 */
	public Integer getCategoryId() {
		return categoryId;
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
