package com.sharingcard.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class SeqUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private static DecimalFormat df = new DecimalFormat("000000000");

	// 编号记录
	private static long code;

	// 时间戳记录
	private static String timestamp;

	public static synchronized String getSeq(String s) {
		String str = null;

		str = sdf.format(System.currentTimeMillis());
		if (null != timestamp && timestamp.trim().length() > 0) {
			if (timestamp.equals(str)) {//
				code++;
			} else {//
				timestamp = str;
				code = 1;
			}
		} else {
			timestamp = str;
			code = 1;
		}
		return str +s+ df.format(code);
	}

	public static void main(String[] args) {

		//for (int i = 0; i < 100000; i++) {
			System.out.println(SeqUtils.getSeq("2"));
		//}
	}
}
