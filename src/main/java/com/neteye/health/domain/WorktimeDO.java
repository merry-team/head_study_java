package com.neteye.health.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 营业时间
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 14:36:54
 */
public class WorktimeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer workId;
	//工作时间
	private String workTime;
	//工作时间说明
	private String remark;
	//状态：1、有效；0、无效
	private Integer status;
	//创建用户id
	private Long createUser;
	//修改用户id
	private Long modifiedUser;
	//创建时间
	private Date createTime;
	//修改时间
	private Date modifiedTime;

	/**
	 * 设置：id
	 */
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	/**
	 * 获取：id
	 */
	public Integer getWorkId() {
		return workId;
	}
	/**
	 * 设置：工作时间
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	/**
	 * 获取：工作时间
	 */
	public String getWorkTime() {
		return workTime;
	}
	/**
	 * 设置：工作时间说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：工作时间说明
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：状态：1、有效；0、无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1、有效；0、无效
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建用户id
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建用户id
	 */
	public Long getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：修改用户id
	 */
	public void setModifiedUser(Long modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * 获取：修改用户id
	 */
	public Long getModifiedUser() {
		return modifiedUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}
}
