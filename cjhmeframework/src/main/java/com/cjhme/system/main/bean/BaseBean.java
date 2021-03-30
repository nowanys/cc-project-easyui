package com.cjhme.system.main.bean;

import java.io.Serializable;

import com.cjhme.system.main.util.StrUtil;

/**
 * 
 * <p>
 * Title: BaseBean.java
 * </p>
 * Description: 基础Bean，有bean继承这个Bean
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 2, 2013 3:14:00 PM
 */
public abstract class BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 状态(1正常，2锁定（根据业务而定），3删除)
	 */
	private String status;
	
	/**
	 * 创建人id
	 */
	private String createUserId;
	
	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 修改人id
	 */
	private String updateUserId;
	
	/**
	 * 修改人
	 */
	private String updateUser;

	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 修改时间
	 */
	private String updateDate;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreateDate() {
		return StrUtil.subStrNineteenth(createDate);
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return StrUtil.subStrNineteenth(updateDate);
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	
}
