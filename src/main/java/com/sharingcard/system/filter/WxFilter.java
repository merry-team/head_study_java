package com.sharingcard.system.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
/** 
 * 微信过滤器
 * @author yinxiangjun 
 */  
//@WebFilter(urlPatterns = "/*", filterName = "wxfilter")
public class WxFilter implements Filter {  
	private static Logger logger = LoggerFactory.getLogger(WxFilter.class);


    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
    	if(logger.isDebugEnabled()){
  			logger.debug("wx filter is open");
  		}
  		
  		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
        long start = System.currentTimeMillis();
        if(isWechat(req) && isDownApk(req)) {
        	String data = "<!DOCTYPE html>\n" + 
        			"<html>\n" + 
        			"<head> \n" + 
        			"<title>请在浏览器中打开</title>\n" + 
        			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + 
        			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">\n" + 
        			"<style>\n" + 
        			"body{background-color:#333;}\n" + 
        			"img{width:100%;}\n" + 
        			"</style>\n" + 
        			"</head>\n" + 
        			"<body>\n" + 
        			"<img src=\"https://img.alicdn.com/imgextra/i4/668603298/TB2ZuSVgQKWBuNjy1zjXXcOypXa_!!2-martrix_bbs.png\" />\n" + 
        			"</body>\n" + 
        			"</html>";
        	OutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
        	resp.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        	/**
        	 * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
        	 * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
        	 * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
        	 * 比如： "中"在GB2312的码表上对应的数字是98
        	 *         "国"在GB2312的码表上对应的数字是99
        	 */
        	/**
        	 * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，如果是中文操作系统，那么就使用GB2312的码表
        	 */
        	byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
        	outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
        	return;
        }
        else {
        	filterChain.doFilter(req, resp);
        }
        logger.debug("wxfilter Execute cost=" + (System.currentTimeMillis() - start));

    }
    
	public static boolean isWechat(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent").toLowerCase();
		if (ua.indexOf("micromessenger") > -1) {
			return true;// 微信
		}
		return false;// 非微信手机浏览器
	}

	private static boolean isDownApk(HttpServletRequest request) {
		String uri = request.getRequestURI().toLowerCase();
		if (uri.startsWith("/files") && uri.endsWith(".apk")) {
			return true;// apk下载
		}
		return false;// 非apk下载
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(logger.isDebugEnabled()){
			logger.debug("ws filter init~~~~~~~~~~~~");
		}	
	}

	@Override
	public void destroy() {}  
  
}  
