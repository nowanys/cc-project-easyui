package com.cjhme.system.main.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <p>
 * Title: IPUtil.java
 * </p>
 * Description: 获取客户端请求IP地址
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 20, 2013 9:45:50 AM
 */
public final class IPUtil {

	private IPUtil() {
	}

	/**
	 * 获得id地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}