package com.neteye.health.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 企业分公司
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-11 11:31:01
 */
public class DepartmentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//分公司id
	private Integer departmentId;
	//公司id
	private Integer companyId;
	//公司名称
	private String companyName;
	//分公司名称
	private String departmentName;
	//经营时间
	private Integer timeId;
	//经营时间
	private String timeRemark;
	//省
	private Integer provinceId;
	//省名称
	private String provinceName;
	//市
	private Integer cityId;
	//市名称
	private String cityName;
	//状态：1、有效；0、无效
	private Integer status;
	//地址
	private String address;
	//联系电话
	private String telephone;
	//主页
	private String homePage;
	//备注
	private String remarks;
	//创建用户id
	private Long createUser;
	//修改用户id
	private Long modifiedUser;
	//创建时间
	private Date createTime;
	//修改时间
	private Date modifiedTime;

	/**
	 * 设置：分公司id
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取：分公司id
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
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
	 * 设置：分公司名称
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * 获取：分公司名称
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置：
	 */
	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}
	/**
	 * 获取：
	 */
	public Integer getTimeId() {
		return timeId;
	}
	/**
	 * 设置：省
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：市
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市
	 */
	public Integer getCityId() {
		return cityId;
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
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：主页
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	/**
	 * 获取：主页
	 */
	public String getHomePage() {
		return homePage;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
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
	public String getTimeRemark() {
		return timeRemark;
	}
	public void setTimeRemark(String timeRemark) {
		this.timeRemark = timeRemark;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
