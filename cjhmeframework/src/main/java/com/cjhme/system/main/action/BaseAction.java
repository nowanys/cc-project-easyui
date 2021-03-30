package com.cjhme.system.main.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>
 * Title: BaseAction.java
 * </p>
 * Description: 基础action
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Nov 9, 2014 8:42:19 PM
 */
public abstract class BaseAction {

	Log LOGGER = LogFactory.getLog(BaseAction.class);

	/**
	 * 获得回调函数(参数名称callBackFunc)
	 * 
	 * @param defaultFunc
	 *            默认回调函数
	 * @return
	 */
	public final String getCallBackFunc(HttpServletRequest request, String defaultFunc) {
		String callBackFunc = request.getParameter("callBackFunc");
		if (null == callBackFunc || "".equals(callBackFunc)) {
			callBackFunc = defaultFunc;
		}
		return callBackFunc;
	}

	/**
	 * 默认回调函数处理(参数名称callBackFunc)
	 * 
	 * @param request
	 * @param defaultFunc
	 */
	public final void defaultCallBackFunc(HttpServletRequest request, String defaultFunc) {
		String callBackFunc = request.getParameter("callBackFunc");
		if (null == callBackFunc || "".equals(callBackFunc)) {
			callBackFunc = defaultFunc;
		}
		request.setAttribute("callBackFunc", callBackFunc);
	}

}
