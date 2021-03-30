package com.cjhme.system.role.bean;

/**
 * 
 * <p>
 * Title: RoleMenuMappingBean.java
 * </p>
 * Description: 角色菜单映射
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Dec 15, 2014 4:01:59 PM
 */
public class RoleMenuMappingBean {

	/**
	 * id
	 */
	private String rmmId;

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 菜单id
	 */
	private String menuId;
	
	/**
	 * 菜单名称
	 */
	private String menuName;
	



	public String getRmmId() {
		return rmmId;
	}

	public void setRmmId(String rmmId) {
		this.rmmId = rmmId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	
}
