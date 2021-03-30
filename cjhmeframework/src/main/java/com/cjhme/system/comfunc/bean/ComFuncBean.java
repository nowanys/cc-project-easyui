package com.cjhme.system.comfunc.bean;


import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>
 * Title: ComFuncBean.java
 * </p>
 * Description: 常用功能
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Sep 8, 2014 9:03:59 AM
 */
public class ComFuncBean  extends BaseBean  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String comFuncId;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 菜单id
	 */
	private String menuId;
	
	/**
	 * 菜单编号
	 */
	private String menuCode;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 图标
	 */
	private String iconCls;

	/**
	 * 访问url
	 */
	private String accessUrl;
	
	
	
	
	public String getComFuncId() {
		return comFuncId;
	}



	public void setComFuncId(String comFuncId) {
		this.comFuncId = comFuncId;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getMenuId() {
		return menuId;
	}



	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}



	public String getMenuCode() {
		return menuCode;
	}



	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}



	public String getMenuName() {
		return menuName;
	}



	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getIconCls() {
		return iconCls;
	}



	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}



	public String getAccessUrl() {
		return accessUrl;
	}



	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}



	public String getRoleId() {
		return roleId;
	}



	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	

}
