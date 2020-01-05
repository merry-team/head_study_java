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

import org.apache.commons.lang.ArrayUtils;

import net.sf.json.JSONArray;
public class Array_mery {
	public static void main(String[] args) {
	           String json = "["
	           		+ "{\"a\":\"111\",\"b\":\"222\",\"c\":\"333\"},"
	           		+ "{\"a\":\"1000\",\"b\":\"2000\",\"c\":\"000\"},"
	           		+ "{\"a\":\"999\",\"b\":\"300\",\"c\":\"700\"}"
	           		+ "]";
	           String json_b = "["
		           		+ "{\"a\":\"333\",\"b\":\"444\",\"c\":\"555\"},"
		           		+ "{\"a\":\"1000\",\"b\":\"2000\",\"c\":\"000\"},"
		           		+ "{\"a\":\"999\",\"b\":\"300\",\"c\":\"700\"}"
		           		+ "]";
	             JSONArray jsonArr = JSONArray.fromObject(json);
	             JSONArray jsonArr_b = JSONArray.fromObject(json_b);
	             jsonArr.addAll(jsonArr_b);
	             //jsonArr.add(jsonArr_b);
	             System.out.print(jsonArr+" ");
	             String a[] = new String[jsonArr.size()];
	             String b[] = new String[jsonArr.size()];
	             String c[] = new String[jsonArr.size()];
	             
	 
//	             for (int i = 0; i < jsonArr.size(); i++) {
//	                 a[i] = jsonArr.getJSONObject(i).getString("a");
//	                 b[i] = jsonArr.getJSONObject(i).getString("b");
//	                 c[i] = jsonArr.getJSONObject(i).getString("c");
//	            }
	              
//	             for (int i = 0; i < c.length; i++) {
//	                System.out.print(a[i]+" ");
//	                System.out.print(b[i]+" ");
//	                System.out.print(c[i]);
//	                System.out.println();
//	            }
		
	String authos = "\\xac\\xed\\x00\\x05t\\x00\\xdaeyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjozLCJjcmVhdGVkIjoxNTY0MDIyNzU1NzA3LCJ1c2VyTmFtZSI6bnVsbCwiZXhwIjoxNTY0MDI0NTU1LCJ1c2VySWQiOjEwMDU2fQ.zZa4fOezBcf-FnMjDiMJmNjImSTzMtdUoEBNFWuxDb57KcCH8W81CtTGNV3VGPzEiEZ8nrSliQ-KhVVNa5nYyw";	
		//demo.idcard_verify("李桃", "110104197811030448");
//		demo.idcard_verify("袁秀梅", "210202196005191249");
//		demo.idcard_verify("于洪涛", "110104197811030448");
//		demo.idcard_verify("张新治", "110104197811030448");
//		demo.idcard_verify("杨移梅", "110104197811030448");
//		demo.idcard_verify("夏君华", "110104197811030448");
//		demo.idcard_verify("张大为", "110104197811030448");
//		demo.idcard_verify("游爱华", "110104197811030448");
		//demo.idcard_verify("董婷", "370724198504210028"); 
		//demo.idcard_verify("张海晴", "341621199309144729");
		//demo.idcard_verify("徐楠", "232700199206060847");
		//demo.idcard_verify("陈旭玲", "430225197902160020");
	}



}
