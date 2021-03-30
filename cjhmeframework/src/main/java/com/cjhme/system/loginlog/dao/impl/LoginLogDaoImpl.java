package com.cjhme.system.loginlog.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cjhme.system.loginlog.bean.LoginLogBean;
import com.cjhme.system.loginlog.dao.LoginLogDao;
import com.cjhme.system.main.dao.BaseDao;
import com.cjhme.system.main.interceptor.mybatis.bean.DataPaging;

/**
 * 
 * <p>
 * Title: LoginLogDaoImpl.java
 * </p>
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Mar 19, 2015 7:59:37 PM
 */
@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDao implements LoginLogDao {

	/**
	 * 获得当前用户登录信息日志
	 * 
	 * @param parameter
	 * @return
	 */
	public LoginLogBean queryCurrentUserLoginLogInfo(Map<String, Object> parameter) {
		return this.getSqlSessionTemplate().selectOne("com.cjhme.system.loginlog.dao.LoginLogDao.queryCurrentUserLoginLogInfo", parameter);
	}

	/**
	 * 添加登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public int saveLoginLog(Map<String, Object> parameter) {
		return this.getSqlSessionTemplate().insert("com.cjhme.system.loginlog.dao.LoginLogDao.saveLoginLog", parameter);
	}

	/**
	 * 根据条件分页查询登录日志
	 * 
	 * @param parameter
	 * @return
	 */
	public DataPaging<Object> queryLoginLogByConditionPaging(DataPaging<Object> pageParameter) {
		return this.selectPaging("com.cjhme.system.loginlog.dao.LoginLogDao.queryLoginLogByConditionPaging", pageParameter);
	}

}
