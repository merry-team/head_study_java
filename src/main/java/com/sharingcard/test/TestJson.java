package com.sharingcard.test;

import java.text.ParseException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestJson {
	public static void main(String[] args) throws ParseException {
		String sss = "{\"AnswerStrokes\":[\r\n" + 
				"{\"pageId\":1,\r\n" + 
				"\"strokesData\":[\r\n" + 
				"{\"y\":\"75.79\",\"force\":33,\"angle\":151,\"x\":\"30.78\",\"type\":0},\r\n" + 
				"{\"y\":\"75.78\",\"force\":36,\"angle\":150,\"x\":\"30.79\",\"type\":1}\r\n" + 
				"]}\r\n" + 
				"]\r\n" + 
				"}";
		 JSONObject json = (JSONObject) JSONObject.parse(sss);
		 JSONArray map = (JSONArray) JSONObject.parse(sss);
		 
		 json.getJSONArray("AnswerStrokes");
		 //json.getJSONArray("AnswerStrokes")
		 System.out.println(json.getJSONArray("AnswerStrokes").get(0));
		 System.out.println(((JSONObject)json.getJSONArray("AnswerStrokes").get(0)).getInteger("pageId"));
		 System.out.println(((JSONObject)json.getJSONArray("AnswerStrokes").get(0)).getJSONArray("strokesData").get(0));
		 System.out.println(((JSONObject)json.getJSONArray("AnswerStrokes").get(0)).getJSONArray("strokesData").get(1));
	}

}
