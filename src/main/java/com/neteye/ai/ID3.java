package com.neteye.ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class ID3 {
	public static void main(String[] args) {
		ID3 demo = new ID3();

		//demo.idcard_verify("张洪海", "110103197310050938");
		//demo.idcard_verify("李鹏宇", "232326199611161037");
		//demo.idcard_verify("赵国海", "371428198704142074");
		//demo.idcard_verify("高金宝", "131127198603043838"); 
		//demo.idcard_verify("陈小兰", "320981198401102226"); 
		//demo.idcard_verify("董婷", "370724198504210028"); 
		//demo.idcard_verify("刘芋含", "21010519930320122X"); 
		//demo.idcard_verify("高梦瑶", "110106198705174824"); 
		//demo.idcard_verify("张毅", "511111198207042921"); 
		//demo.idcard_verify("王冬妮", "433124198511151421"); 
		 //demo.idcard_verify("刘晓海", "430523198410195831"); 
		demo.idcard_verify("张海晴", "341621199309144729"); 
	}

	private final static String mall_id = "111240";
	private final static String appkey = "266ec4d6c0b1157ef320eb77685801ed";
	private final static String url = "http://121.41.42.121:8080/v3/id-server?";

	public void idcard_verify(String realname, String idcard) {
		idcard = idcard.toLowerCase().trim();
		realname = realname.trim();
		long tm = System.currentTimeMillis();
		String md5_param = mall_id + realname + idcard + tm + appkey;
		String sign = md5(md5_param);
		String param = new StringBuffer().append("mall_id=" + mall_id)
				.append("&realname=" + realname).append("&idcard=" + idcard)
				.append("&tm=" + tm).append("&sign=" + sign).toString();

		try {
			param = param.replace(realname,
					URLEncoder.encode(realname, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String jsonString = sendPost(url, param);

		// JSONObject result=JSONObject.parseObject(jsonString);
		System.out.println(jsonString);

	}

	private String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和 URL 之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0	(compatible;	MSIE	6.0;	Windows	NT	5.1;SV1)");
			// 发送 POST 请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取 URLConnection 对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush 输出流的缓冲
			out.flush();
			// 定义 BufferedReader 输入流来读取 URL 的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送	 POST	 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用 finally 块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
