package com.cjhme.system.loginlog.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjhme.system.loginlog.service.LoginLogService;
import com.cjhme.system.main.action.BaseAction;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;
import com.cjhme.system.main.util.RequestParamToObjectUtil;

/**
 * 
 * <p>
 * Title: LoginLogAction.java
 * </p>
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 28, 2014 7:29:14 PM
 */
@Controller
@Scope("prototype")
@RequestMapping("/loginLog")
public class LoginLogAction extends BaseAction {

	Log LOGGER = LogFactory.getLog(LoginLogAction.class);

	private LoginLogService loginLogService;

	/**
	 * 跳转登录日志管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/preLoginLogManage", method = RequestMethod.GET)
	public String preLoginLogManage(HttpServletRequest request) throws Exception {
		LOGGER.info("跳转登录日志管理页面成功！");
		return "forward:/pages/manage/system/loginlog/login-log-manage.jsp";
	}

	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryLoginLogByConditionPaging", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryLoginLogByConditionPaging(HttpServletRequest request) {

		Map<String, Object> map = null;

		try {
			DataPaging<Object> result = this.loginLogService.queryLoginLogByConditionPaging(RequestParamToObjectUtil.requestParamParseToPageMap(request));
			map = new HashMap<String, Object>();
			map.put("rows", result.getRows());
			map.put("total", result.getTotal());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据条件分页查询登录日志失败,错误——>" + e);
		}

		LOGGER.info("根据条件分页查询登录日志成功！");
		return map;

	}

	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	@Resource
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

}
