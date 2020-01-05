package com.sharingcard.test;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.utils.ResourceUtil;

public class TestTemp {

	public static void main(String[] args) {

		String s = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
        Map map = new HashMap();
        map.put("mobile", "13888888888");
        map.put("content", "王总喜提兰博基尼");
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        System.out.println(json.toJSONString());
        System.out.println(((JSONObject) json.parse(json.toJSONString())).get("content"));
        System.out.println(((JSONObject) json.parse(json.toJSONString())).get("mobile"));
        
       String t =  MessageFormat.format(ResourceUtil.getConfigByName("wx.webAccessTokenhttps"),
                ResourceUtil.getConfigByName("wx.appId"),
                ResourceUtil.getConfigByName("wx.secret"),
                "061uhpRm10Ly5r06wFOm14A6Rm1uhpRT");
        System.out.println(t);
        String str = MessageFormat.format("我是{0},我来自{1},今年{2}岁", "中国人", "北京", "22");
        System.out.println(str);
	}
}
