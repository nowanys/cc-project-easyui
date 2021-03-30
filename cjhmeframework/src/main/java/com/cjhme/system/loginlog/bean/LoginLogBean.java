package com.cjhme.system.loginlog.bean;

import com.cjhme.system.main.bean.BaseBean;
import com.cjhme.system.main.util.StrUtil;

/**
 * 
 * <p>
 * Title: LoginLogBean.java
 * </p>
 * Description: 登录日志
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Mar 18, 2015 9:08:38 PM
 */
public class LoginLogBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String loginId;

	/**
	 * 登录ip
	 */
	private String loginIP;

	/**
	 * 上一次登录
	 */
	private String lastLoginDate;
	
	/**
	 * 本次登录
	 */
	private String currentLoginDate;
	
	/**
	 * 登录次数
	 */
	private String loginCount;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLastLoginDate() {
		return StrUtil.subStrNineteenth(lastLoginDate);
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public String getCurrentLoginDate() {
		return StrUtil.subStrNineteenth(currentLoginDate);
	}

	public void setCurrentLoginDate(String currentLoginDate) {
		this.currentLoginDate = currentLoginDate;
	}
	
	

}
