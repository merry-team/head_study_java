package com.neteye.xinzhizhu.test;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) {
	        String str = "%E9%BE%99%E5%82%B2%E5%A4%A9";
	        String[] s = str.split("%");
	        byte[] by = new byte[10];
	        for(int i = 1; i < s.length; i++){
	            by[i] = (byte) Integer.parseInt(s[i], 16);
	        }
	        try {
	            String name = new String(by, "utf-8");
	            System.out.println(name.trim());
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	}

}
