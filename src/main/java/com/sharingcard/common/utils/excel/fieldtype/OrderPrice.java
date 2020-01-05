/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sharingcard.common.utils.excel.fieldtype;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-03-10
 */
public class OrderPrice {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {

		String s = val.replace("元", "");
		BigDecimal v = new BigDecimal(s);
		return v.multiply(new BigDecimal(100));
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		String retStr = "";
		BigDecimal v = (BigDecimal) val;
		retStr = v.divide(new BigDecimal(100))+"元";
		return retStr;
	}
}
