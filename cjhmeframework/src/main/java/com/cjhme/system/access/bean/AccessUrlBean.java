package com.cjhme.system.access.bean;

import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>
 * Title: AccessUrlBean.java
 * </p>
 * Description: 访问地址
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 15, 2013 6:59:38 PM
 */
public class AccessUrlBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 访问地址id
	 */
	private String accessUrlId;

	/**
	 * 访问地址
	 */
	private String accessUrl;

	/**
	 * 访问地址名称
	 */
	private String accessUrlName;


	public String getAccessUrlId() {
		return accessUrlId;
	}

	public void setAccessUrlId(String accessUrlId) {
		this.accessUrlId = accessUrlId;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}


	public String getAccessUrlName() {
		return accessUrlName;
	}

	public void setAccessUrlName(String accessUrlName) {
		this.accessUrlName = accessUrlName;
	}

}
