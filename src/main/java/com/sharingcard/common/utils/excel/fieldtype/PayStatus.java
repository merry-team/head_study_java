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
public class PayStatus {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {

		if (StringUtils.trimToEmpty(val).equals("未付款")){
			return (Integer)0;
		}
		else if(StringUtils.trimToEmpty(val).equals("付款中")){
			return (Integer)1;
		}
		else if(StringUtils.trimToEmpty(val).equals("已付款")){
			return (Integer)2;
		}
		else if(StringUtils.trimToEmpty(val).equals("退款")){
			return (Integer)3;
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		String retStr = "";
		if (val != null && ((Integer)val).equals(0) ){
			retStr="未付款";
		}	
		else if (val != null && ((Integer)val).equals(1) ){
			retStr="付款中";
		}
		else if (val != null && ((Integer)val).equals(2) ){
			retStr="已付款";
		}
		else if (val != null && ((Integer)val).equals(4) ){
			retStr="退款";
		}
		return retStr;
	}
}
