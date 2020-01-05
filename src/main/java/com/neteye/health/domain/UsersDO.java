package com.neteye.health.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户表
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-09-14 11:19:22
 */
public class UsersDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//账号
	private String username;
	//密码
	private String password;
	//性别
	private Integer gender;
	//是否结婚：0、未婚；1、已婚
	private Integer marry;
	//生日
	private Date birthday;
	//注册时间
	private Date registerTime;
	//最后登录时间
	private Date lastLoginTime;
	//登录ip
	private String lastLoginIp;
	//类型:1、企业付费；2、自费
	private Integer type;
	//公司ID
	private Integer companyId;
	//公司名称
	private String companyName;
	//证件id
	private String idcard;
	//证件用户姓名
	private String name;
	//用户等级
	private Integer userLevelId;
	//昵称
	private String nickname;
	//手机
	private String mobile;
	//注册ip
	private String registerIp;
	//微信id
	private String weixinOpenid;
	//企业id
	private String unionId;
	//状态:1、有效；0、删除
	private Integer status;
	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：账号
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：账号
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置：是否结婚：0、未婚；1、已婚
	 */
	public void setMarry(Integer marry) {
		this.marry = marry;
	}
	/**
	 * 获取：是否结婚：0、未婚；1、已婚
	 */
	public Integer getMarry() {
		return marry;
	}
	/**
	 * 设置：生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：生日
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * 获取：最后登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * 设置：登录ip
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * 获取：登录ip
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * 设置：类型:1、企业付费；2、自费
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型:1、企业付费；2、自费
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：公司ID
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：公司ID
	 */
	public Integer getCompanyId() {
		return companyId;
	}
	/**
	 * 设置：证件id
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * 获取：证件id
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * 设置：证件用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：证件用户姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：用户等级
	 */
	public void setUserLevelId(Integer userLevelId) {
		this.userLevelId = userLevelId;
	}
	/**
	 * 获取：用户等级
	 */
	public Integer getUserLevelId() {
		return userLevelId;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：注册ip
	 */
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	/**
	 * 获取：注册ip
	 */
	public String getRegisterIp() {
		return registerIp;
	}
	/**
	 * 设置：微信id
	 */
	public void setWeixinOpenid(String weixinOpenid) {
		this.weixinOpenid = weixinOpenid;
	}
	/**
	 * 获取：微信id
	 */
	public String getWeixinOpenid() {
		return weixinOpenid;
	}
	/**
	 * 设置：企业id
	 */
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	/**
	 * 获取：企业id
	 */
	public String getUnionId() {
		return unionId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
