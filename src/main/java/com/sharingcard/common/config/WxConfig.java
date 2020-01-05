package com.sharingcard.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="wxinfo")
public class WxConfig {
	//appid
	private String appid;
	//secret
	private String secret;
	//获取openid的链接
	private String openidurl;
	//商户id
	private String mch_appid;
	
	private String mchid;
	//企业付款链接
	private String company_pay_url;
	//证书地址
	private String certPath;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getOpenidurl() {
		return openidurl;
	}
	public void setOpenidurl(String openidurl) {
		this.openidurl = openidurl;
	}
	public String getMch_appid() {
		return mch_appid;
	}
	public void setMch_appid(String mch_appid) {
		this.mch_appid = mch_appid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getCompany_pay_url() {
		return company_pay_url;
	}
	public void setCompany_pay_url(String company_pay_url) {
		this.company_pay_url = company_pay_url;
	}
	public String getCertPath() {
		return certPath;
	}
	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}	
}
