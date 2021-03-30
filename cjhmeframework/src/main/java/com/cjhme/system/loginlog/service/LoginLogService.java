package com.cjhme.system.loginlog.service;

import java.util.Map;

import com.cjhme.system.loginlog.bean.LoginLogBean;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: LoginLogService.java 
 * </p>  
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Mar 19, 2015 8:03:14 PM
 */
public interface LoginLogService {

	/**
	 * 获得当前用户登录信息日志
	 * @param parameter
	 * @return
	 */
	public LoginLogBean queryCurrentUserLoginLogInfo(Map<String, Object> parameter);
	
	/**
	 * 添加登录日志 
	 * @param parameter
	 * @return
	 */
	public int saveLoginLog(Map<String, Object> parameter);
	
	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryLoginLogByConditionPaging(DataPaging<Object> pageParameter);
}
