package com.neteye.xinzhizhu.utils;

import java.io.File;
import java.security.MessageDigest;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class MultimeLength {
    public static long getMultimeLength(String str) {
        if (str == null) {
            return 0;
        }
        try {
        	File source= new File(str);
        	Encoder encoder = new Encoder();
	        MultimediaInfo m = encoder.getInfo(source);
	        long ls = m.getDuration();
	        return (ls)/1000;
        } catch (Exception e) {
            return 0;
        }
    }
}
