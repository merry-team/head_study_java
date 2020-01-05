package com.neteye.ai;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s = "[{\"answer\":\"have\",\"index\":1,\"matchLevel\":8},{\"answer\":\"some\",\"index\":2,\"matchLevel\":8},{\"answer\":\"friends\",\"index\":3,\"matchLevel\":8}]";
		String s = "[]";
		
		JSONArray jsonArr = JSONArray.fromObject(s);
		for(int i = 0;i <jsonArr.size(); i++) {
			JSONObject jo =(JSONObject) jsonArr.get(i);
			jo.put("check", true);
			System.out.println(jsonArr.get(i)+" ");
		}
	}

}
