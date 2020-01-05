package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户收藏
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-07-16 22:28:58
 */
public class CollectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer collecttId;
	//用户id
	private Integer userId;
	//评论类型1：文章；2：课程；3：fm；4：vod
	private Integer collectType;
	//作者
	private Integer objectId;
	//创建时间
	private Date creatTime;
	//
	private Integer status;

	/**
	 * 设置：id
	 */
	public void setCollecttId(Integer collecttId) {
		this.collecttId = collecttId;
	}
	/**
	 * 获取：id
	 */
	public Integer getCollecttId() {
		return collecttId;
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
	 * 设置：评论类型1：文章；2：课程；3：fm；4：vod
	 */
	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}
	/**
	 * 获取：评论类型1：文章；2：课程；3：fm；4：vod
	 */
	public Integer getCollectType() {
		return collectType;
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
