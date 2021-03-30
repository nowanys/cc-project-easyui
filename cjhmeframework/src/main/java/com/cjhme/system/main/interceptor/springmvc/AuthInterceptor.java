package com.cjhme.system.main.interceptor.springmvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.main.constant.CommonConstants;
import com.cjhme.system.main.interceptor.springmvc.annotation.Auth;
import com.cjhme.system.role.bean.RoleBean;
import com.cjhme.system.user.bean.UserBean;

/**
 * 
 * <p>
 * Title: AuthInterceptor.java
 * </p>
 * Description: 权限拦截器
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Jul 14, 2015 4:12:04 PM
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	Log LOGGER = LogFactory.getLog(AuthInterceptor.class);

	/**
	 * 验证权限
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod method = (HandlerMethod) handler;
		Auth auth = method.getMethod().getAnnotation(Auth.class);

		HttpSession session = request.getSession();
		
		String requestedWith = request.getHeader("x-requested-with");

		// 对登录超时与默认验证进行验证
		if (auth == null || auth.verifyLogin()) {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			if (null == userBean || userBean.getUserId() == null) {
				
				// ajax请求处理方式
				if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
					LOGGER.info("用户未登录或登录超时——>ajax请求处理方式");
					
					response.setHeader("session-status", "timeout");
					response.getWriter().print(CommonConstants.LOING_EXPIRED_OR_NOT_LOGIN_JSON);
					return false;
					
				// 普通请求处理方式
				} else {
					LOGGER.info("用户未登录或登录超时——>普通请求处理方式");
					
					StringBuffer redirctHtmlBuffer = new StringBuffer();
					redirctHtmlBuffer.append("<!DOCTYPE HTML><html><head>");
					redirctHtmlBuffer.append("<script type=\"text/javascript\">");
					redirctHtmlBuffer.append("window.top.location.href=\"");
					redirctHtmlBuffer.append(request.getContextPath() + CommonConstants.PRE_NOT_LOGIN_PAGE);
					redirctHtmlBuffer.append("\";");
					redirctHtmlBuffer.append("</script>");
					redirctHtmlBuffer.append("</head><body></body></html>");
					response.getWriter().print(redirctHtmlBuffer);
					return false;
				}

			}
		}
		
		// 对访问url进行验证
		if (auth == null || auth.verifyUrl()) {
			UserBean userBean = (UserBean) session.getAttribute(CommonConstants.MANAGE_USER_SESSION);
			RoleBean currentRole = userBean.getCurrentRole();
			
			//判断是否分配角色
			if(null!=currentRole){
				List<AccessUrlBean> accessUrls = currentRole.getAccessUrls();
				boolean hasValid=false;
				
				//查询是否分配url
				if(null!=accessUrls && accessUrls.size()>0){
					for(AccessUrlBean accessUrlBean :accessUrls){
						
						if(-1!=request.getRequestURL().indexOf(accessUrlBean.getAccessUrl())){
							hasValid=true;
							break;
						}
					}
				}
				
				//验证未通过
				if(!hasValid){
					// ajax请求处理方式
					if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
						LOGGER.info("验证访问url未分配权限——>ajax请求处理方式");
						
						response.getWriter().print(CommonConstants.AUTHORITY_NO_AUTHORITY);
						return false;
						
					// 普通请求处理方式
					} else {
						LOGGER.info("验证访问url未分配权限——>普通请求处理方式");
						
						StringBuffer redirctHtmlBuffer = new StringBuffer();
						redirctHtmlBuffer.append("<!DOCTYPE HTML><html><head>");
						redirctHtmlBuffer.append("<script type=\"text/javascript\">");
						redirctHtmlBuffer.append("window.top.location.href=\"");
						redirctHtmlBuffer.append(request.getContextPath() + CommonConstants.PRE_AUTHORITY_NO_AUTHORITY);
						redirctHtmlBuffer.append("\";");
						redirctHtmlBuffer.append("</script>");
						redirctHtmlBuffer.append("</head><body></body></html>");
						response.getWriter().print(redirctHtmlBuffer);
						return false;
					}
				}
				
				
			}else{
				// ajax请求处理方式
				if (requestedWith != null && "XMLHttpRequest".equals(requestedWith)) {
					LOGGER.info("验证访问url未分配角色——>ajax请求处理方式");
					
					response.getWriter().print(CommonConstants.AUTHORITY_UNDISTRIBUTED_ROLE);
					return false;
					
				// 普通请求处理方式
				} else {
					LOGGER.info("验证访问url未分配角色——>普通请求处理方式");
					
					StringBuffer redirctHtmlBuffer = new StringBuffer();
					redirctHtmlBuffer.append("<!DOCTYPE HTML><html><head>");
					redirctHtmlBuffer.append("<script type=\"text/javascript\">");
					redirctHtmlBuffer.append("window.top.location.href=\"");
					redirctHtmlBuffer.append(request.getContextPath() + CommonConstants.PRE_AUTHORITY_UNDISTRIBUTED_ROLE);
					redirctHtmlBuffer.append("\";");
					redirctHtmlBuffer.append("</script>");
					redirctHtmlBuffer.append("</head><body></body></html>");
					response.getWriter().print(redirctHtmlBuffer);
					return false;
				}
			}
			
		}

		return super.preHandle(request, response, handler);

	}
}
