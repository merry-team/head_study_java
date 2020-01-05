package com.sharingcard.test;

import com.aimi.smssend.sender.SelfSender;

public class TestSms {

	public static void main(String[] args) {
		SelfSender sender = new SelfSender();
		String s = "";
		try {
			s = sender.send("【分享通信】尊敬的分享通信用户，张先生：您充值的1000亿已经到账。", "17001200117", "diaodu", "b2747692745f6ae74d7626c1e134e23a", "1");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
