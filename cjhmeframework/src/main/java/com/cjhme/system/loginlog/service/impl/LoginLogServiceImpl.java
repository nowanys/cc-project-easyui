package com.cjhme.system.loginlog.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjhme.system.loginlog.bean.LoginLogBean;
import com.cjhme.system.loginlog.dao.LoginLogDao;
import com.cjhme.system.loginlog.service.LoginLogService;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>  
 * Title: LoginLogServiceImpl.java 
 * </p>  
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Mar 19, 2015 8:03:49 PM
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	private LoginLogDao loginLogDao;
	
	/**
	 * 获得当前用户登录信息日志
	 * @param parameter
	 * @return
	 */
	public LoginLogBean queryCurrentUserLoginLogInfo(Map<String, Object> parameter) {
		return this.loginLogDao.queryCurrentUserLoginLogInfo(parameter);
	}

	/**
	 * 添加登录日志 
	 * @param parameter
	 * @return
	 */
	@Transactional
	public int saveLoginLog(Map<String, Object> parameter){
		return this.loginLogDao.saveLoginLog(parameter);
	}
	
	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryLoginLogByConditionPaging(DataPaging<Object> pageParameter){
		return this.loginLogDao.queryLoginLogByConditionPaging(pageParameter);
	}
	
	public LoginLogDao getLoginLogDao() {
		return loginLogDao;
	}

	@Resource
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
	
	

}
