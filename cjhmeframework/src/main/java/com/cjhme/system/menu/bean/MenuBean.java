package com.cjhme.system.menu.bean;

import java.util.HashMap;
import java.util.Map;

import com.cjhme.system.main.bean.BaseBean;

/**
 * 
 * <p>
 * Title: MenuBean.java
 * </p>
 * Description: 菜单
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Jul 1, 2014 9:10:33 PM
 */
public class MenuBean extends BaseBean {

	private static final long serialVersionUID = 1L;

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

	/**
	 * 父菜单id
	 */
	private String parentMenuId;

	/**
	 * 排序
	 */
	private String sortNum;

	/**
	 * easy ui json属性
	 */
	private Map<String, Object> attributes = new HashMap<String, Object>();
	
	
	

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

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	
	

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * 组装json
	 * 
	 * @return
	 */
	public Map<String, Object> getAttributes() {
		if (null == this.accessUrl) {
			attributes.put("accessUrl", "");
		} else {
			attributes.put("accessUrl", this.accessUrl);
		}

		return attributes;
	}

}
