package com.cjhme.system.role.bean;

import java.util.List;

import com.cjhme.system.access.bean.AccessUrlBean;
import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>  
 * Title: RoleBean.java 
 * </p>  
 * Description: 角色
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Sep 8, 2014 8:42:23 AM
 */
public class RoleBean extends BaseBean {


	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String roleId;
	
	/**
	 * 角色编码
	 */
	private String roleCode;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 角色描述
	 */
	private String roleDescribe;
	
	/**
	 * 是否超级管理员(1否，2是)
	 */
	private String isSuper;
	
	/**
	 * 短信开关(1开启，2关闭)
	 */
	private String emailSwitch;
	
	/**
	 * 邮件开关(1开启，2关闭)
	 */
	private String smsSwitch;
	
	/**
	 * 访问地址列表
	 */
	private List<AccessUrlBean> accessUrls;
	
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	public String getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}


	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getEmailSwitch() {
		return emailSwitch;
	}

	public void setEmailSwitch(String emailSwitch) {
		this.emailSwitch = emailSwitch;
	}

	public String getSmsSwitch() {
		return smsSwitch;
	}

	public void setSmsSwitch(String smsSwitch) {
		this.smsSwitch = smsSwitch;
	}

	public List<AccessUrlBean> getAccessUrls() {
		return accessUrls;
	}

	public void setAccessUrls(List<AccessUrlBean> accessUrls) {
		this.accessUrls = accessUrls;
	}


	

}
