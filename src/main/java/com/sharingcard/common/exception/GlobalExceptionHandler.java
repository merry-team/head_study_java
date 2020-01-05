package com.sharingcard.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice(basePackages ="com.neteye.xinzhizhu.api") 
@ControllerAdvice
public class GlobalExceptionHandler {
	
	  /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 400);
        map.put("msg", ex.getMessage());
        return map;
    }
    
	@ResponseBody
	@ExceptionHandler(value = RuntimeException.class)  //该注解声明异常处理方法
	public Map<String, Object> exceptionHandler(HttpServletRequest request, Exception e) {
		Map<String, Object> obj = new HashMap<String, Object>();
		// 全局异常处理逻辑
		if (e instanceof BDException) {
			obj.put("errno", ((BDException) e).getCode());
			obj.put("errmsg", ((BDException) e).getMsg());
			return obj;
		} else {
			obj.put("errno", 400);
			obj.put("errmsg", e.getMessage());
			return obj;
		}

	}
}
