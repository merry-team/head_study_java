package com.sharingcard.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpHead {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://192.168.2.20/group1/M00/00/01/wKgCFF0-v5eAc7CHAAAAjIMNeWY429.zip");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// conn.setDoOutput(true);
		conn.setRequestMethod("HEAD");

		Map<String, List<String>> headerMap = conn.getHeaderFields();
		Iterator<String> iterator = headerMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			List<String> values = headerMap.get(key);

			System.out.println(key + ":" + values.get(0).toString());
		}
	}
}
