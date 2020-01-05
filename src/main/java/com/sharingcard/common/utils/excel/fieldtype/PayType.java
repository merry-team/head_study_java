/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sharingcard.common.utils.excel.fieldtype;

import org.apache.commons.lang3.StringUtils;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-03-10
 */
public class PayType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {

		if (StringUtils.trimToEmpty(val).equals("企业付费")){
			return (Integer)1;
		}
		else if(StringUtils.trimToEmpty(val).equals("个人付费")){
			return (Integer)2;
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		String retStr = "";
		if (val != null && ((Integer)val).equals(1) ){
			retStr="企业付费";
		}	
		else if (val != null && ((Integer)val).equals(2) ){
			retStr="个人付费";
		}
		return retStr;
	}
}
