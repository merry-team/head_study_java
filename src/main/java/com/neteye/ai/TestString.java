package com.neteye.ai;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "1";
		String ss[] = s.split(",");
		for (int i = 0; i < ss.length; i++) {
			System.out.println(Integer.valueOf(ss[i]));
		}

		Map map = new HashMap();
		map.put("userId", 111);
		map.put("bookId", 111);
		map.put("taskId", 111);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println("key=" + key + " value=" + value);
		}
		map.remove("Usss");
		it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println("key=" + key + " value=" + value);
		}
		map.remove("userId");
		it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println("key=" + key + " value=" + value);
		}
	}

}
