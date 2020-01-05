package com.sharingcard.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="billing")
public class BillingConfig {
	//话单目录
	private String cdrpath;
	//话单备份目录
	private String cdrbackpath;
	//话单前缀
	private String filepre;	
	
	public String getCdrpath() {
		return cdrpath;
	}
	public void setCdrpath(String cdrpath) {
		this.cdrpath = cdrpath;
	}
	public String getCdrbackpath() {
		return cdrbackpath;
	}
	public void setCdrbackpath(String cdrbackpath) {
		this.cdrbackpath = cdrbackpath;
	}
	public String getFilepre() {
		return filepre;
	}
	public void setFilepre(String filepre) {
		this.filepre = filepre;
	}		
	
}
