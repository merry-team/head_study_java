package com.neteye.xinzhizhu.utils;

import java.io.File;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class Test {

	public static void main(String[] args) {    

		   float sum = 0;
		   File source= new File("E:\\G备份\\MP3");
		   File file[] = new File[source.listFiles().length];
		   file = source.listFiles();
		   
		   for(int i = 0; i < file.length; i++) {
			   Encoder encoder = new Encoder();
		       try {
		          MultimediaInfo m = encoder.getInfo(file[i]);
		          long ls = m.getDuration();
		          sum += ls;
		          System.out.println(file[i]+":此视频时长为:"+ls/60000+"分"+(ls)/1000+"秒！");
		       } catch(Exception e) {
		         e.printStackTrace();
		       }
		   }
		   System.out.println("总时长：" + sum/1000 + "秒" + "    结算：" + sum / 600000 * 8  + "元");
	    }


}

