package com.cjhme.system.main.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <p>  
 * Title: GlobalExceptionHandler.java 
 * </p>  
 * Description: 全局异常处理
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Mar 20, 2015 8:03:29 PM
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

	Log LOGGER = LogFactory.getLog(HandlerExceptionResolver.class);
	
	/**
	 * 异常处理实现
	 * @param request current http request
	 * @param response current http response
	 * @param handler exception handler
	 * @param ex exception
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse reponse, Object handler, Exception ex) {
		LOGGER.error("系统发生异常，异常信息如下：\n"+handler.getClass().getName()+"——>"+ex);
        Map<String,Object> errorResult = new HashMap<String,Object>();  
        errorResult.put("errorMsg", ex);
		return  new ModelAndView("/pages/errorPage/error",errorResult);
	}

}
