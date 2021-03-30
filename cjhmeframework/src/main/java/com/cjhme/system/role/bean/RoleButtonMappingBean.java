package com.cjhme.system.role.bean;

/**
 * 
 * <p>  
 * Title: RoleButtonMappingBean.java 
 * </p>  
 * Description: 角色按钮映射
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Mar 4, 2015 10:59:26 AM
 */
public class RoleButtonMappingBean {
	
	/**
	 * id
	 */
	private String rbmId;
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 按钮id
	 */
	private String buttonId;

	

	public String getRbmId() {
		return rbmId;
	}

	public void setRbmId(String rbmId) {
		this.rbmId = rbmId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	
	

}
