package com.neteye.xinzhizhu.domain;

import com.neteye.xinzhizhu.utils.Base64;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户评论
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-17 22:41:38
 */
public class CommentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer commentId;
	//用户id
	private Integer userId;
	//订单id
	private Integer orderId;
	//评论类型1：文章；2：课程；3：fm；4：vod
	private Integer commentType;
	//作者
	private Integer objectId;
	//
	private String comment;
	//创建时间
	private Date creatTime;
	//
	private Integer status;

	/**
	 * 设置：id
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：id
	 */
	public Integer getCommentId() {
		return commentId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：评论类型1：文章；2：课程；3：fm；4：vod
	 */
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
	/**
	 * 获取：评论类型1：文章；2：课程；3：fm；4：vod
	 */
	public Integer getCommentType() {
		return commentType;
	}
	/**
	 * 设置：作者
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	/**
	 * 获取：作者
	 */
	public Integer getObjectId() {
		return objectId;
	}
	/**
	 * 设置：
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：
	 */
	public String getComment() {
		return comment;
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
}
