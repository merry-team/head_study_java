package com.neteye.health.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 企业信息表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-10 18:00:34
 */
public class CompanyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//公司id
	private Integer companyId;
	//公司名称
	private String companyName;
	//公司类型：1、体检机构；2、体检用户
	private Integer type;
	//公司状态：1、有效；0、无效
	private Integer status;
	//创建用户
	private Long createUser;
	//修改用户
	private Long modifiedUser;
	//创建时间
	private Date createTime;
	//修改时间
	private Date modifiedTime;

	/**
	 * 设置：公司id
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：公司id
	 */
	public Integer getCompanyId() {
		return companyId;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：公司类型：1、体检机构；2、体检用户
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：公司类型：1、体检机构；2、体检用户
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：公司状态：1、有效；0、无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：公司状态：1、有效；0、无效
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建用户
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建用户
	 */
	public Long getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：修改用户
	 */
	public void setModifiedUser(Long modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * 获取：修改用户
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
