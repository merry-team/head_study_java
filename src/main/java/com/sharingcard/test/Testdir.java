package com.sharingcard.test;

import java.util.List;

import com.sharingcard.common.utils.FileUtil;

public class Testdir {

	public static void main(String[] args) {
		List<String> list = FileUtil.getFileList("E:\\home\\tools\\jd","jd-gui");
		for(int i=0 ; i<list.size(); i++) {
			System.out.println(list.get(i));
			System.out.println(FileUtil.getfileName(list.get(i)));
		}
	}
}
